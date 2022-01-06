package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.myweb.www.domain.BCommentVO;
import com.myweb.www.repository.BCommentDAO;
import com.myweb.www.repository.ProductDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BCommentServiceImpl implements BCommentService {
	
	@Inject
	private BCommentDAO cdao;
	
	@Override
	public int register(BCommentVO cvo) {
		int isUp = cdao.insertBComment(cvo);
		return isUp;
	}

	@Override
	public List<BCommentVO> getList(long pno) {
		return cdao.selectListBComment(pno);
	}

	@Override
	public int modify(BCommentVO cvo) {
		return cdao.updateBComment(cvo);
	}

	@Override
	public int remove(long cno) {
		int isUp = cdao.deleteOneBComment(cno);
		return isUp;
	}
}
