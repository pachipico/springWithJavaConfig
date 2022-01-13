package com.myweb.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardDAO {
	int insertBoard(BoardVO bvo);
	BoardVO selectOneBoard(Long bno);
	List<BoardVO> selectListBoard();
	List<BoardVO> selectListBoardPaging(PagingVO pagination);
	int updateBoard(BoardVO bvo);
	int updateReadCntBoard(@Param("bno")Long bno,@Param("cnt") int cnt);
	int deleteBoard(Long bno);
	int selectOneTotalCount(PagingVO pagingVO);
	Long selectOneBno();
	int updateBoardFileCount(@Param(value = "bno") Long bno,@Param(value = "cnt") int cnt);
	int updateBoardCommentCount(@Param(value = "bno")Long bno,@Param(value = "cnt") int cnt);
}
