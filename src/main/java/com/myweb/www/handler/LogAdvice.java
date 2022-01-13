package com.myweb.www.handler;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.myweb.www.domain.CommentVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class LogAdvice {
	
	@Before("execution(* com.myweb.www.service.BCommentService*.*(..))")
	public void commentLogBefore() {
		System.out.println();
		log.info(">>>>>>>>>>>>comment log start>>>>>>>>>>>>>"); 
	}
	
	@Before("execution(* com.myweb.www.service.BCommentService*.register(com.myweb.www.domain.CommentVO)) && args(cvo)")
	public void commentLogBeforeRegister(CommentVO cvo) {
		log.info("<<<<<<<<<<<<<< Register cvo : {} >>>>>>>>>>>>>>", cvo);
	}
	
	@After("execution(* com.myweb.www.service.BCommentService*.*(..))")
	public void commentLogAfter() {
		log.info(">>>>>>>>>>>>comment log end>>>>>>>>>>>>>"); 
	}
}
