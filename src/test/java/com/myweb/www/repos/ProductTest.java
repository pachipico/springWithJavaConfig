package com.myweb.www.repos;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myweb.www.config.RootConfig;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.domain.ProductVO;
import com.myweb.www.repository.ProductDAO;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
@Slf4j
public class ProductTest {

	@Inject
	ProductDAO pdao;

	@Test
	public void insert() throws Exception {
		ProductVO pvo = new ProductVO();
		pvo.setCategory("abc");
		pvo.setDescription("this is test description");
		pvo.setMadeBy("test");
		pvo.setPname("testName1");
		pvo.setPrice(9999);
		pvo.setWriter("123@123.com");
		pdao.insertProduct(pvo);
	}

	@Test
	public void getProduct() {
		pdao.selectOneProduct(112L);
	}

	@Test
	public void getProductCount() {
		PagingVO pgvo = new PagingVO(1,10);
		pgvo.setKeyword("this");
		pgvo.setType("d");
		pdao.selectProductCount(pgvo);    
	}

	@Test
	public void getProductList() {
		pdao.selectProductList(new PagingVO(1,10));
	}

	@Test
	public void updateProduct() {
		ProductVO pvo = new ProductVO();
		pvo.setCategory("qwer");
		pvo.setDescription("this is modified description");
		pvo.setMadeBy("test");
		pvo.setPname("testName1");
		pvo.setPrice(9999);
		pvo.setPno(113L);
		pdao.updateProduct(pvo);
	}

	@Test
	public void updateQty() {
//		pdao.updateQtyUp(113L);
//		pdao.selectOneProduct(113L);
		pdao.updateQtyDown(113L);
		pdao.selectOneProduct(113L);
	}



	@Test
	public void deleteProduct() {
		pdao.deleteProduct(113L);
	}

}
