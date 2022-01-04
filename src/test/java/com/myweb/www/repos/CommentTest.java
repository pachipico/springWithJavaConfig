package com.myweb.www.repos;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myweb.www.config.RootConfig;
import com.myweb.www.domain.CommentVO;
import com.myweb.www.repository.CommentDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class CommentTest {
	@Inject 
	CommentDAO cdao;
	
	@Test
	public void register() throws Exception {
		for (int i = 0; i < 350; i++) {
			CommentVO cvo = new CommentVO();
			cvo.setBno(Double.valueOf(Math.floor(Math.random()*3)+1).longValue());
			cvo.setWriter("test"+i+"@test.com");
			cvo.setContent("this is test comment of "+i);
			cdao.insert(cvo);
		}
	}
	
	@Test 
	public void getList() throws Exception {
		cdao.selectList(3L);
	}
	
	@Test
	public void getCnt()throws Exception {
		cdao.selectCnt(3L);
	}
	
	@Test 
	public void modify() throws Exception {
		CommentVO cvo = new CommentVO();
		cvo.setCno(1L);
		cvo.setContent("this is modified test comment");
		cdao.update(cvo);
		cdao.selectList(1L);
	}
	
	@Test 
	public void delete() throws Exception {
		cdao.delete(1L);
		cdao.selectList(1L);
	}
	
	@Test
	public void deleteAll() throws Exception {
		cdao.deleteAll(2L);
		cdao.deleteAll(3L);
		cdao.selectList(2L);
		cdao.deleteAll(3L);
	}
}
