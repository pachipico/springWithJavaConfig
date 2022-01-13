package com.myweb.www.handler;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.myweb.www.domain.BFileVO;
import com.myweb.www.repository.FileDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProductFileSweeper {

	private final String BASE_PATH = "/Users/jhs/Desktop/ezenSpring/pfileUpload/";
	
	@Inject
	private FileDAO fdao;
	
	@Scheduled(cron = "00 00 23 * * *")
	public void sweeper () throws Exception {
		log.info(">>> file sweeper started... {} --> {}", BASE_PATH, LocalDateTime.now());
		
		List<BFileVO> dbFiles = fdao.selectListAllFiles();
		
		List<String> currFiles = new ArrayList<String>();
		
		for (BFileVO fvo : dbFiles) {
			String filePath = BASE_PATH + fvo.getSaveDir() + File.separator + fvo.getUuid() + "_" + fvo.getFileName();
			currFiles.add(filePath);
			
			if(fvo.getFileType() > 0) {
				currFiles.add(BASE_PATH + fvo.getSaveDir() + File.separator + fvo.getUuid() + "_th_" + fvo.getFileName());
			}
		}
		LocalDate _today = LocalDate.now();
		String today = _today.toString();
		today = today.replace("-", File.separator);
		File folder = Paths.get(BASE_PATH+today).toFile();
		File[] files = folder.listFiles();
		
		for (File file : files) {
			String path = file.toPath().toString();
			if(!currFiles.contains(path)) {
				file.delete();
				log.info(">>> {} 삭제...",path);
			}
		}
		log.info(">>> file sweeper finished... {}", LocalDateTime.now());
		
	}
}
