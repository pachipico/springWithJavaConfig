package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.BoardVO;

public interface BoardDAO {
	int insert(BoardVO bvo);
	BoardVO selectOne(Long bno);
	List<BoardVO> selectList();
	int update(BoardVO bvo);
	int updateReadCnt(Long bno);
	int delete(Long bno);
}
