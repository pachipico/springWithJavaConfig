package com.myweb.www.svc;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myweb.www.config.RootConfig;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class BoardTest {
	
	@Inject
	BoardService bsv;
	
	@Test
	public void register() {
		BoardVO bvo = new  BoardVO();
		bvo.setTitle("testTitle1");
		bvo.setContent("test Content1");
		bvo.setWriter("123@123.com");
		bsv.register(bvo);
	}
	
	@Test
	public void getDetail() {
		bsv.getDetail(2L);
	}
	
	@Test
	public void getList() {
		bsv.getList();
	}
	
	@Test 
	public void modify() {
		BoardVO bvo = new  BoardVO();
		bvo.setBno(2L);
		bvo.setTitle("modifiedTitle1");
		bvo.setContent("this is modified Content1");
		bvo.setWriter("123@123.com");
		bsv.modify(bvo);
		bsv.getDetail(2L);
	}
	
	@Test
	public void remove() {
		bsv.remove(2L);
	}
}
