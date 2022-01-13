package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.BFileVO;
import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.domain.ProductVO;
import com.myweb.www.repository.BFileDAO;
import com.myweb.www.repository.FileDAO;
import com.myweb.www.repository.ProductDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

	@Inject
	private ProductDAO pdao;

	@Inject
	private FileDAO fdao;

	@Override
	public int register(BoardDTO dto) {
		int isUp = pdao.insertProduct(dto.getPvo());
		Long pno = pdao.selectOnePno();
		if(dto.getBfList() != null) {
			int cnt = dto.getBfList().size();
			if (isUp > 0 && cnt > 0) {
				for (BFileVO file : dto.getBfList()) {
					file.setPno(pno);
					isUp *= fdao.insertFile(file);
				}
				if(isUp > 0) {
					pdao.updateProductFileCount(pno, cnt);
				}
			}
		}
		return isUp;
	}

	@Transactional
	@Override
	public BoardDTO getDetail(Long pno) {
		BoardDTO bdto = new BoardDTO();
		if (pdao.selectOneProduct(pno) != null) {
			pdao.updateReadCount(pno, 1);
		}
		bdto.setPvo(pdao.selectOneProduct(pno));
		bdto.setBfList(fdao.selectListFile(pno));
		return bdto;
	}

	@Override
	public int getCount(PagingVO pgvo) {

		return pdao.selectProductCount(pgvo);
	}

	@Override
	public List<ProductVO> getList(PagingVO pgvo) {

		return pdao.selectProductList(pgvo);
	}

	@Transactional
	@Override
	public int modify(BoardDTO bdto) {
		int isUp = pdao.updateProduct(bdto.getPvo());
		
		Long pno = bdto.getPvo().getPno();
		if(bdto.getBfList() != null) {
		if (isUp > 0 && bdto.getBfList().size() > 0) {
			for (BFileVO file : bdto.getBfList()) {
				file.setPno(bdto.getPvo().getPno());
				isUp *= fdao.insertFile(file);
			}
		}
	}
		if(isUp > 0) {
			int cnt = fdao.selectFileCount(pno);
			log.debug("{} 의 파일 수: {}", pno, cnt);
			pdao.updateProductFileCount(pno, cnt);
			pdao.updateReadCount(pno, -2);
		}
		return isUp;
	}

	@Override
	public int remove(Long pno) {

		return pdao.deleteProduct(pno);
	}

	@Transactional
	@Override
	public int removeFile(String uuid) {
		Long pno = fdao.selectOnePno(uuid);
		int isDel = fdao.deleteFile(uuid);
		if(isDel > 0) {
			int cnt = fdao.selectFileCount(pno);
			pdao.updateProductFileCount(pno, cnt);
			pdao.updateReadCount(pno, -2);
		}
		return isDel;
	}

}
