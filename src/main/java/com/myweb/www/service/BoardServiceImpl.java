package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	@Override
	public int register(BoardDTO bdto) {
		int isUp = bdao.insertBoard(bdto.getBvo());
		if (bdto.getBfList() != null) {
			if (isUp > 0 && bdto.getBfList().size() > 0) {
				int cnt = bdto.getBfList().size();
				Long bno = bdao.selectOneBno();
				bdao.updateBoardFileCount(bno, cnt);
				for (BFileVO bfvo : bdto.getBfList()) {
					bfvo.setBno(bno);
					isUp *= bfdao.insertBFile(bfvo);
				}
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
		log.debug("service pagination : {}", pagination.toString());
		return bdao.selectListBoardPaging(pagination);
	}

	@Transactional
	@Override
	public BoardDTO getDetail(Long bno) {
		BoardDTO bdto = new BoardDTO();
		BoardVO bvo = bdao.selectOneBoard(bno);

		if (bvo != null) {
			bdao.updateReadCntBoard(bno,1);
		}
		bdto.setBvo(bdao.selectOneBoard(bno));
		bdto.setBfList(bfdao.selectListBFile(bno));
		return bdto;
	}

	@Override
	public int getTotalCount(PagingVO pagingVO) {

		return bdao.selectOneTotalCount(pagingVO);
	}

	@Override
	public int modify(BoardDTO bdto) {
		int isUp = bdao.updateBoard(bdto.getBvo());
		Long bno = bdto.getBvo().getBno();
		if (bdto.getBfList() != null) {
			if (isUp > 0 && bdto.getBfList().size() > 0) {
				for (BFileVO bfvo : bdto.getBfList()) {
					bfvo.setBno(bno);
					isUp *= bfdao.insertBFile(bfvo);
				}
			}
		}
		if (isUp > 0) {
			int cnt = bfdao.selectOneFileCount(bno);
			bdao.updateBoardFileCount(bno, cnt);
			bdao.updateReadCntBoard(bno, -2);
		}
		return isUp;
	}

	@Override
	public int remove(Long bno) {
		bfdao.deleteAllBFile(bno);
		return bdao.deleteBoard(bno);
	}

	@Transactional
	@Override
	public int removeFile(String uuid) {
		Long bno = bfdao.selectOneBno(uuid);
		int isDel = bfdao.deleteBFile(uuid);
		int cnt = bfdao.selectOneFileCount(bno);
		bdao.updateBoardFileCount(bno, cnt);
		return isDel;
	}

}
