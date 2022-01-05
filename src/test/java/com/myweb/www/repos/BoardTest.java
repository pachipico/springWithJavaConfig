package com.myweb.www.repos;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myweb.www.config.RootConfig;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
@Slf4j
public class BoardTest {

	@Inject
	private BoardDAO bdao;

	@Test
	public void insert() {
		for (int i = 0; i < 512000; i++) {
			BoardVO bvo = new BoardVO();
			bvo.setTitle("testTitle" + i);
			bvo.setContent("test content" + i);
			bvo.setWriter("test" + ((int)Math.random() * 100) + 1 + "@test.com");
			bdao.insertBoard(bvo);
		}
	}

	@Test
	public void selectOne() throws Exception {
		bdao.selectOneBoard(1L);
	}

	@Test
	public void selectList() throws Exception {
		bdao.selectListBoard();
	}

	@Test
	public void update() throws Exception {
		BoardVO bvo = new BoardVO();
		bvo.setBno(1);
		bvo.setTitle("modifiedTitle");
		bvo.setContent("this content has been modified");
		bdao.updateBoard(bvo);
		bdao.selectOneBoard(1L);
	}

	@Test
	public void updateReadCnt() throws Exception {
		bdao.updateReadCntBoard(3L);
		bdao.selectOneBoard(3L);
	}

	@Test
	public void delete() throws Exception {
		bdao.deleteBoard(1L);
	}
}