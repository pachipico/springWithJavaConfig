package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.BFileVO;
import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.repository.BFileDAO;
import com.myweb.www.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	BoardDAO bdao;
	
	@Inject
	BFileDAO bfdao;
	
	@Override
	public int register(BoardDTO bdto) {
		int isUp = bdao.insertBoard(bdto.getBvo());
		if(isUp > 0 && bdto.getBfList().size() > 0) {
			Long bno = bdao.selectOneBno();
//			bdto.getBfList().forEach((file) -> {
//				
//				bfdao.insertBFile(file);
//			});
			for(BFileVO bfvo: bdto.getBfList()) {
				bfvo.setBno(bno);
				isUp *= bfdao.insertBFile(bfvo);
			}
		}
		return isUp;
	}

	@Override
	public List<BoardVO> getList() {

		return bdao.selectListBoard();
	}

	@Override
	public List<BoardVO> getList(PagingVO pagination) {
		log.debug("service pagination : {}",pagination.toString());
		return bdao.selectListBoardPaging(pagination);
	}

	@Override
	public BoardVO getDetail(Long bno) {
		BoardVO bvo = bdao.selectOneBoard(bno);
		if(bvo != null) {
			bdao.updateReadCntBoard(bno);
		}
		return bdao.selectOneBoard(bno);
	}

	@Override
	public int getTotalCount(PagingVO pagingVO) {

		return bdao.selectOneTotalCount(pagingVO);
	}

	@Override
	public int modify(BoardVO bvo) {
		
		return bdao.updateBoard(bvo);
	}

	@Override
	public int remove(Long bno) {

		return bdao.deleteBoard(bno);
	}


}
