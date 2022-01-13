package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.BCommentVO;
import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.repository.BCommentDAO;
import com.myweb.www.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BCommentServiceImpl implements BCommentService {
	
	@Inject
	private BCommentDAO cdao;
	
	@Inject
	private BoardDAO bdao;
	
	@Transactional
	@Override
	public int register(CommentVO cvo) {
		int isUp = cdao.insertBComment(cvo);
		if(isUp > 0) {
			Long bno = cvo.getBno();
			int cnt = cdao.selectOneBCommentTotalCount(bno);
			bdao.updateBoardCommentCount(bno, cnt);
		}
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

	@Transactional
	@Override
	public int remove(long cno) {
		long bno = cdao.selectOneBno(cno);
		int isUp = cdao.deleteOneBComment(cno);
		if(isUp > 0) {
			int cnt = cdao.selectOneBCommentTotalCount(bno);
			bdao.updateBoardCommentCount(bno,cnt );
		}
		return isUp;
	}
}
