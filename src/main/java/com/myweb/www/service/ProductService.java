package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.domain.ProductVO;


public interface ProductService {
	int register(BoardDTO dto);
	BoardDTO getDetail(Long pno);
	int getCount(PagingVO pgvo);
	List<ProductVO> getList(PagingVO pgvo);
	int modify(BoardDTO dto);
	int remove(Long pno);
	int removeFile(String uuid);
}
