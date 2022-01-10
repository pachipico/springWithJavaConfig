package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardService {
	int register(BoardDTO bdto);
	List<BoardVO> getList();
	List<BoardVO> getList(PagingVO pagination);
	BoardVO getDetail(Long bno);
	int modify(BoardVO bvo);
	int remove(Long bno);
	int getTotalCount(PagingVO pagingVO);
}
