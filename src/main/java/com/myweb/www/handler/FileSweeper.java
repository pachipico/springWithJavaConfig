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
import com.myweb.www.repository.BFileDAO;
import com.myweb.www.repository.FileDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileSweeper {
	// 반복문으로 pfileUpload 도 추가
	private final String[] BASE_PATH = { "/Users/jhs/Desktop/ezenSpring/fileUpload/",
			"/Users/jhs/Desktop/ezenSpring/pfileUpload/" };

	@Inject
	BFileDAO bfdao;
	
	@Inject
	FileDAO fdao;

	@Scheduled(cron = "30 16 21 * * *")
	public void fileSweeper() throws Exception {
		for (String path : BASE_PATH) {

			log.info(">>> file sweeper started... {} --> {}", path, LocalDateTime.now());

			// DB에 등록된 파일 목록 가져오기
			List<BFileVO> dbFiles = path.equals("/Users/jhs/Desktop/ezenSpring/fileUpload/") ? bfdao.selectListAllBFiles() : fdao.selectListAllFiles();

			// 저장소를 검색할 파일 path 리스트
			
				List<String> currFiles = new ArrayList<String>();				
			

			for (BFileVO fvo : dbFiles) {
				String filePath = fvo.getSaveDir() + File.separator + fvo.getUuid() + "_" + fvo.getFileName();
				currFiles.add(path + filePath);

				if (fvo.getFileType() > 0) {
					currFiles
							.add(path + fvo.getSaveDir() + File.separator + fvo.getUuid() + "_th_" + fvo.getFileName());
				}
			}
			// .minusDays(1L) 로 어제자 날짜로 설정 가능
			LocalDate _today = LocalDate.now().minusDays(1L);
			String today = _today.toString();
			today = today.replace("-", File.separator);

			// 상위의폴더를 객체화 해서 내용물을 listFiles()로 가져옴
			File dir = Paths.get(path + today).toFile();
			File[] allFileObjs = dir.listFiles();

			// db에 해당 이름의 파일이없으면 삭제
			for (File file : allFileObjs) {
				String storedFileName = file.toPath().toString();
				if (!currFiles.contains(storedFileName)) {
					file.delete();
					log.info(">>> {} 삭제...", storedFileName);
				}
			}
			log.info(">>> file sweeper finished... {}", LocalDateTime.now());
		}
	}
}
