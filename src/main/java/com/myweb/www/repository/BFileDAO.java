package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.BFileVO;


public interface BFileDAO {
	int insertBFile(BFileVO bfvo);
	List<BFileVO> selectListBFile(Long bno);
	int deleteBFile (String uuid);
	int deleteAllBFile(Long bno);
	Long selectOneBno(String uuid);
	int selectOneFileCount(Long bno);
	List<BFileVO> selectListAllBFiles();
}
