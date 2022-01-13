package com.myweb.www.ctrl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.BFileVO;
import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.FileHandler;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Inject
	private BoardService bsv;

	@Inject
	private FileHandler fhd;

	@GetMapping("/register")
	public void register() {
	}

	@PostMapping("/register")
	public String register(BoardVO bvo, RedirectAttributes reAttr,
			@RequestParam(name = "files", required = false) MultipartFile[] files) {
		BoardDTO bdto = new BoardDTO();
		List<BFileVO> bfList = new ArrayList<BFileVO>();

		if (files[0].getSize() > 0) {
			bfList = fhd.uploadFiles(files, "board");
			bvo.setHasFile(bfList.size());
		}
		bdto.setBvo(bvo);
		bdto.setBfList(bfList);
		log.debug("board register bvo : {}", bvo);
		reAttr.addFlashAttribute("isReg", bsv.register(bdto) > 0 ? "1" : "0");
		return "redirect:/board/list";
	}

//	@GetMapping("/list")
//	public void list(Model model) {
//		model.addAttribute("list", bsv.getList());
//		
//	}
	// paging list
	@GetMapping("/list")
	public void list(Model model, PagingVO pagingVO) {
		model.addAttribute("list", bsv.getList(pagingVO));
		int totalCount = bsv.getTotalCount(pagingVO);
		model.addAttribute("pgn", new PagingHandler(pagingVO, totalCount));

	}

	@GetMapping({ "/detail", "/modify" })
	public void detail(@RequestParam("bno") Long bno, @ModelAttribute("pgvo") PagingVO pgvo, Model model) {
		model.addAttribute("bdto", bsv.getDetail(bno));

//		model.addAttribute("pgvo", pgvo);
	}

	@PostMapping("/modify")
	public String modify(BoardVO bvo, RedirectAttributes reAttr, @ModelAttribute("pgvo") PagingVO pgvo,
			@RequestParam(name = "files", required = false) MultipartFile[] files) {
		List<BFileVO> bfList = new ArrayList<BFileVO>();
		BoardDTO bdto = new BoardDTO();
		if (files[0].getSize() > 0) {
			bfList = fhd.uploadFiles(files, "board");
		} 
		bdto.setBvo(bvo);
		bdto.setBfList(bfList);
		reAttr.addFlashAttribute("isUp", bsv.modify(bdto));
		reAttr.addFlashAttribute("pgvo", pgvo);
		return "redirect:/board/detail?bno=" + bvo.getBno();
	}

	@PostMapping("/remove")
	public String remove(@RequestParam Long bno, RedirectAttributes reAttr, @ModelAttribute("pgvo") PagingVO pgvo) {
		reAttr.addFlashAttribute("isDel", bsv.remove(bno));
		reAttr.addFlashAttribute("pgvo", pgvo);
		return "redirect:/board/list";
	}

	@DeleteMapping(value = "/file/{uuid}", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> removeFile(@PathVariable("uuid") String uuid) {
		
		return bsv.removeFile(uuid) > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
