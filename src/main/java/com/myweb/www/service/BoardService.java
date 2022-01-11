package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardService {
	int register(BoardDTO bdto);
	List<BoardVO> getList();
	List<BoardVO> getList(PagingVO pagination);
	BoardDTO getDetail(Long bno);
	int modify(BoardDTO bdto);
	int remove(Long bno);
	int getTotalCount(PagingVO pagingVO);
	int removeFile(String uuid);
}
