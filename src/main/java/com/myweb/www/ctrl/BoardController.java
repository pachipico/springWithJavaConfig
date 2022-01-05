package com.myweb.www.ctrl;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Inject
	BoardService bsv;
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(BoardVO bvo, RedirectAttributes reAttr) {
		log.debug("board register bvo : {}", bvo);
		reAttr.addFlashAttribute("isReg", bsv.register(bvo));
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
