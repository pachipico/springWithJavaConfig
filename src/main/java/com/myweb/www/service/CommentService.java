package com.myweb.www.service;


import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;

public interface CommentService {
	
	int registerComment(CommentVO cvo);
	PagingHandler getList(Long pno, PagingVO pgvo);
	int getCount(Long pno);
	int modify(CommentVO cvo);
	int deleteOne(Long cno, Long pno);
	int deleteAll(Long pno);
}
