package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.PagingVO;
import com.myweb.www.domain.ProductVO;
import com.myweb.www.repository.ProductDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
	
	@Inject
	private ProductDAO pdao;
	
	@Override
	public int register(ProductVO pvo) {

		return pdao.insertProduct(pvo);
	}

	@Override
	public ProductVO getDetail(Long pno) {
		if(pdao.selectOneProduct(pno) != null) {
			pdao.updateReadCount(pno);
		}
		return pdao.selectOneProduct(pno);
	}

	@Override
	public int getCount(PagingVO pgvo) {

		return pdao.selectProductCount(pgvo);
	}

	@Override
	public List<ProductVO> getList(PagingVO pgvo) {

		return pdao.selectProductList(pgvo);
	}

	@Override
	public int modify(ProductVO pvo) {

		return pdao.updateProduct(pvo);
	}

	@Override
	public int remove(Long pno) {

		return pdao.deleteProduct(pno);
	}

}
