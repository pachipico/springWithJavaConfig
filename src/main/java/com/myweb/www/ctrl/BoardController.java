package com.myweb.www.ctrl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public void register() {}
	
	@PostMapping("/register")
	public String register(BoardVO bvo, RedirectAttributes reAttr, @RequestParam(name = "files", required = false) MultipartFile[] files ) {
		
		List<BFileVO> bfList = new ArrayList<BFileVO>();
		
		if(files[0].getSize() > 0) {
			bfList = fhd.uploadFiles(files);
			
		}
		
		log.debug("board register bvo : {}", bvo);
		reAttr.addFlashAttribute("isReg",bsv.register(new BoardDTO(bvo, bfList)) > 0 ? "1" : "0");
		return "redirect:/board/list";
	}
	
//	@GetMapping("/list")
//	public void list(Model model) {
//		model.addAttribute("list", bsv.getList());
//		
//	}
	//paging list
	@GetMapping("/list")
	public void list(Model model, PagingVO pagingVO) {
		model.addAttribute("list", bsv.getList(pagingVO));
		int totalCount = bsv.getTotalCount(pagingVO);
		model.addAttribute("pgn", new PagingHandler(pagingVO, totalCount));
		
	}
	
	@GetMapping({"/detail", "/modify"})
	public void detail(@RequestParam("bno") Long bno,@ModelAttribute("pgvo") PagingVO pgvo, Model model) {
		model.addAttribute("bvo", bsv.getDetail(bno));
//		model.addAttribute("pgvo", pgvo);
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO bvo, RedirectAttributes reAttr,@ModelAttribute("pgvo") PagingVO pgvo) {
		reAttr.addFlashAttribute("isUp", bsv.modify(bvo));
		reAttr.addFlashAttribute("pgvo",pgvo);
		return "redirect:/board/detail?bno=" + bvo.getBno();
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam Long bno, RedirectAttributes reAttr,@ModelAttribute("pgvo") PagingVO pgvo ) {
		reAttr.addFlashAttribute("isDel", bsv.remove(bno));
		reAttr.addFlashAttribute("pgvo",pgvo);
		return "redirect:/board/list";
	}
}
