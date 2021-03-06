package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.BFileVO;

public interface FileDAO {
	int insertFile(BFileVO fvo);
	List<BFileVO> selectListFile(Long pno);
	Long selectOnePno(String uuid);
	int selectFileCount(Long pno);
	int deleteFile (String uuid);
	int deleteAllFile(Long pno);
	List<BFileVO> selectListAllFiles();
}
