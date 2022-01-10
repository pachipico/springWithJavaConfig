package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardDAO {
	int insertBoard(BoardVO bvo);
	BoardVO selectOneBoard(Long bno);
	List<BoardVO> selectListBoard();
	List<BoardVO> selectListBoardPaging(PagingVO pagination);
	int updateBoard(BoardVO bvo);
	int updateReadCntBoard(Long bno);
	int deleteBoard(Long bno);
	int selectOneTotalCount(PagingVO pagingVO);
	Long selectOneBno();
}
