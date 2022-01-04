package com.myweb.www.ctrl;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.BoardVO;
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
	
	@GetMapping("/list")
	public void list(Model model) {
		model.addAttribute("list", bsv.getList());
		
	}
	
	@GetMapping({"/detail", "/modify"})
	public void detail(@RequestParam("bno") Long bno, Model model) {
		model.addAttribute("bvo", bsv.getDetail(bno));
		
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO bvo, RedirectAttributes reAttr) {
		reAttr.addFlashAttribute("isUp", bsv.modify(bvo));
		return "redirect:/board/detail?bno=" + bvo.getBno();
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam Long bno, RedirectAttributes reAttr) {
		reAttr.addFlashAttribute("isDel", bsv.remove(bno));
		return "redirect:/board/list";
	}
}
