package com.myweb.www.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.myweb.www.domain.BFileVO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@AllArgsConstructor
@Component
public class FileHandler {

	private final String UP_DIR = "/Users/jhs/Desktop/ezenSpring/fileUpload";

	public List<BFileVO> uploadFiles(MultipartFile[] files, String vo) {
		String UP_DIR = vo.equals("board") ? "/Users/jhs/Desktop/ezenSpring/fileUpload" : "/Users/jhs/Desktop/ezenSpring/pfileUpload";
		LocalDate date = LocalDate.now();
		String today = date.toString();
		today = today.replace("-", File.separator);
		log.debug(">>> 재결합된 폴더: {}",today);

		File folders = new File(UP_DIR ,today);
		if(!folders.exists()) {
			folders.mkdirs();
		}
		List<BFileVO> bfList = new ArrayList<BFileVO>();
		for(MultipartFile file: files) {
			BFileVO fvo = new BFileVO();
			fvo.setSaveDir(today);
			fvo.setFileSize(file.getSize());
			
			
			String originalFileName = file.getOriginalFilename();
			log.debug("originalFileName: {}", originalFileName);
			String onlyFileName = originalFileName.substring(originalFileName.lastIndexOf(File.separator)+ 1);
			log.debug("onlyFileName: {}", onlyFileName);
			
			fvo.setFileName(onlyFileName);
			
			UUID uuid = UUID.randomUUID();
			fvo.setUuid(uuid.toString());
			
			String fullFileName = uuid.toString() + "_" + onlyFileName;
			File storeFile = new File(folders,fullFileName);
			try {
				file.transferTo(storeFile);
				if(isImgFile(storeFile)) {
					fvo.setFileType(1);
					File thumbnail = new File(folders, uuid.toString() + "_th_" + onlyFileName);
					Thumbnails.of(storeFile).size(100, 100).toFile(thumbnail);
				}
			} catch (IllegalStateException e) {
				log.debug(">>> file 생성 오류");
				e.printStackTrace();
			} catch (IOException e) {
				log.debug(">>> file 생성 오류");
				e.printStackTrace();
			}
			bfList.add(fvo);
		}
		return bfList;
	}

	private boolean isImgFile(File storeFile) throws IOException {
		String mimeType = new Tika().detect(storeFile);
		return mimeType.startsWith("image");
	}

	
}
