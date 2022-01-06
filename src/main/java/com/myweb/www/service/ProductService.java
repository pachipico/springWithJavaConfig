package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.PagingVO;
import com.myweb.www.domain.ProductVO;


public interface ProductService {
	int register(ProductVO pvo);
	ProductVO getDetail(Long pno);
	int getCount(PagingVO pgvo);
	List<ProductVO> getList(PagingVO pgvo);
	int modify(ProductVO pvo);
	int remove(Long pno);
}
