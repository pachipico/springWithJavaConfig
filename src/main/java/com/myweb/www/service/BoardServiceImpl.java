package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	BoardDAO bdao;
	
	@Override
	public int register(BoardVO bvo) {
		
		return bdao.insert(bvo);
	}

	@Override
	public List<BoardVO> getList() {

		return bdao.selectList();
	}

	@Override
	public BoardVO getDetail(Long bno) {
//		BoardVO bvo = bdao.selectOne(bno);
//		if(bvo != null) {
//			bdao.updateReadCnt(bno);
//		}
		return bdao.selectOne(bno);
	}

	@Override
	public int modify(BoardVO bvo) {
		
		return bdao.update(bvo);
	}

	@Override
	public int remove(Long bno) {

		return bdao.delete(bno);
	}

}
