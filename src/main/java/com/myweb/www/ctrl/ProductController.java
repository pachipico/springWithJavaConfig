package com.myweb.www.ctrl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.BFileVO;
import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.domain.ProductVO;
import com.myweb.www.handler.FileHandler;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/product/*")
public class ProductController {

	@Inject 
	private ProductService psv;
	
	@Inject
	private FileHandler fhd;
	
	@GetMapping("/register")
	public void register() {};
	
	@GetMapping("/list")
 	public void list(Model model, PagingVO pgvo ) {
		
		model.addAttribute("list", psv.getList(pgvo));
		int totalCount = psv.getCount(pgvo);
		model.addAttribute("pgn", new PagingHandler(pgvo, totalCount));
		
	}
	
	@GetMapping({"/detail", "/modify"})
	public void detail(Model model,@ModelAttribute("pgvo") PagingVO pgvo ,@RequestParam("pno") Long pno, RedirectAttributes reAttr) {
		log.debug("detail/modify pno : {}", pno);
		
		model.addAttribute("bdto", psv.getDetail(pno));
	}
	
	@PostMapping("/register")
	public String register(ProductVO pvo, @RequestParam(value = "files", required = false) MultipartFile[] files, RedirectAttributes reAttr){
		BoardDTO dto = new BoardDTO();
		List<BFileVO> fList = new ArrayList<BFileVO>();
		dto.setPvo(pvo);
		
		if(files[0].getSize() > 0) {
			fList = fhd.uploadFiles(files, "product");
			dto.setBfList(fList);
		}
		psv.register(dto);
		return "redirect:/product/list";
	}
	
	@PostMapping("/modify")
	public String modify(ProductVO pvo, @ModelAttribute("pgvo") PagingVO pgvo , 
			RedirectAttributes reAttr, @RequestParam(value = "files", required = false) MultipartFile[] files) {
		List<BFileVO> fList = new ArrayList<BFileVO>();
		BoardDTO dto = new BoardDTO();
		
		if(files[0].getSize() > 0) {
			fList = fhd.uploadFiles(files, "product");
		}
		
		dto.setPvo(pvo);
		dto.setBfList(fList);
		reAttr.addFlashAttribute("isUp", psv.modify(dto));
		reAttr.addFlashAttribute("pgvo", pgvo);
		return "redirect:/product/detail?pno="+ pvo.getPno();
	}
	
	@PostMapping("/remove")
	public String remove (@RequestParam("pno") Long pno, @ModelAttribute("pgvo") PagingVO pgvo, RedirectAttributes reAttr) {
		
		reAttr.addFlashAttribute("isDel", psv.remove(pno));
		reAttr.addFlashAttribute("pgvo", pgvo);
		return "redirect:/product/list";
	}
	
	
	@DeleteMapping("/file/{uuid}")
	public ResponseEntity<String> remove(@PathVariable("uuid") String uuid){
		return psv.removeFile(uuid) > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
