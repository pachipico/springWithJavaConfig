package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

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
		if (isUp > 0 && dto.getBfList().size() > 0) {
			for (BFileVO file : dto.getBfList()) {
				file.setPno(pno);
				isUp *= fdao.insertFile(file);
			}
		}
		return isUp;
	}

	@Override
	public BoardDTO getDetail(Long pno) {
		BoardDTO bdto = new BoardDTO();
		if (pdao.selectOneProduct(pno) != null) {
			pdao.updateReadCount(pno);
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

	@Override
	public int modify(BoardDTO bdto) {
		int isUp = pdao.updateProduct(bdto.getPvo());
		if (isUp > 0 && bdto.getBfList().size() > 0) {
			for (BFileVO file : bdto.getBfList()) {
				file.setPno(bdto.getPvo().getPno());
				isUp *= fdao.insertFile(file);
			}
		}
		return isUp;
	}

	@Override
	public int remove(Long pno) {

		return pdao.deleteProduct(pno);
	}

	@Override
	public int removeFile(String uuid) {

		return fdao.deleteFile(uuid);
	}

}
