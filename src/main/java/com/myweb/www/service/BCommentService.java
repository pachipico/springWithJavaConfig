package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BCommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;

public interface BCommentService {
	int register(BCommentVO cvo);
	PagingHandler getList(long pno, PagingVO pgvo);
	int modify(BCommentVO cvo);
	int remove(long cno);
}
