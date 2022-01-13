package com.myweb.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myweb.www.domain.PagingVO;
import com.myweb.www.domain.ProductVO;


public interface ProductDAO {
	int insertProduct(ProductVO pvo);
	ProductVO selectOneProduct(Long pno);
	int selectProductCount(PagingVO pgvo);
	List<ProductVO> selectProductList(PagingVO pgvo);
	Long selectOnePno();
	int updateProduct(ProductVO pvo);
	int updateProductCommentCount(@Param(value = "pno")Long pno,@Param(value = "cnt") int cnt);
	int updateReadCount(@Param("pno")Long pno,@Param("cnt") int cnt);
	int deleteProduct(Long pno);
	int updateProductFileCount(@Param(value = "pno")Long pno,@Param(value = "cnt") int cnt);
}
