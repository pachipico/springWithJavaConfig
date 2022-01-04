package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.BoardVO;

public interface BoardDAO {
	int insertBoard(BoardVO bvo);
	BoardVO selectOneBoard(Long bno);
	List<BoardVO> selectListBoard();
	int updateBoard(BoardVO bvo);
	int updateReadCntBoard(Long bno);
	int deleteBoard(Long bno);
}
