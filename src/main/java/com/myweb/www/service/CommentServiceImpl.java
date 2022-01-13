package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.repository.CommentDAO;
import com.myweb.www.repository.ProductDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

	@Inject
	CommentDAO cdao;
	@Inject
	ProductDAO pdao;

	@Transactional
	@Override
	public int registerComment(CommentVO cvo) {
		int isUp = 0;
		if (cvo != null) {
			isUp = cdao.insertComment(cvo);
			Long pno = cvo.getPno();
			if (isUp > 0) {
				int cnt = cdao.selectCntComment(pno);
				pdao.updateProductCommentCount(pno, cnt);
			}
		}
		return isUp;
	}

	@Override
	public PagingHandler getList(Long pno, PagingVO pgvo) {
		int totalCount = cdao.selectCntComment(pno);
		List<CommentVO> list = cdao.selectListComment(pno, pgvo);
		PagingHandler phd = new PagingHandler(pgvo, list, totalCount);
		return phd;
	}

	@Override
	public int getCount(Long pno) {

		return cdao.selectCntComment(pno);
	}

	@Override
	public int modify(CommentVO cvo) {

		return cdao.updateComment(cvo);
	}

	@Transactional
	@Override
	public int deleteOne(Long cno, Long pno) {
		int isDel = cdao.deleteComment(cno);
		if(isDel > 0) {
			int cnt = cdao.selectCntComment(pno);
			pdao.updateProductCommentCount(pno, cnt);
		}
		return isDel;
	}

	@Override
	public int deleteAll(Long pno) {
		
		return cdao.deleteAllComment(pno);
	}

}
