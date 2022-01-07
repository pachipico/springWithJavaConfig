package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.BCommentVO;
import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.repository.BCommentDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BCommentServiceImpl implements BCommentService {
	
	@Inject
	private BCommentDAO cdao;
	
	@Override
	public int register(CommentVO cvo) {
		int isUp = cdao.insertBComment(cvo);
		return isUp;
	}

	@Override
	public PagingHandler getList(long bno, PagingVO pgvo) {
		int totalCount = cdao.selectOneBCommentTotalCount(bno);
		List<CommentVO> list = cdao.selectListBComment(bno, pgvo);
		PagingHandler phd = new PagingHandler(pgvo, list, totalCount);
		
		return phd;
	}

	@Override
	public int modify(CommentVO cvo) {
		return cdao.updateBComment(cvo);
	}

	@Override
	public int remove(long cno) {
		int isUp = cdao.deleteOneBComment(cno);
		return isUp;
	}
}
