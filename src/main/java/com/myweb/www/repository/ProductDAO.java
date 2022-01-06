package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.PagingVO;
import com.myweb.www.domain.ProductVO;


public interface ProductDAO {
	int insertProduct(ProductVO pvo);
	ProductVO selectOneProduct(Long pno);
	int selectProductCount(PagingVO pgvo);
	List<ProductVO> selectProductList(PagingVO pgvo);
	int updateProduct(ProductVO pvo);
	int updateQtyUp(Long pno);
	int updateQtyDown(Long pno);
	int updateReadCount(Long pno);
	int deleteProduct(Long pno);
}
