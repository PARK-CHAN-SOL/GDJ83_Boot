package com.sol.app.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.sol.app.util.Pager;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/qna/*")
@Slf4j
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@Value("${board.qna}")
	private String board;
	
	@ModelAttribute("board")
	public String getBoard() {
		return this.board;
	}
	
	@GetMapping("list")
	public String getList(Pager pager, Model model) throws Exception { 
		List<QnaVO> list = qnaService.getList(pager);
		model.addAttribute("list", list);
		model.addAttribute("pager", pager);
		log.info("Pager: {} : {}", pager, pager.getKind());
		return "qna/list";
	}
	
	@GetMapping("add")
	public void add(QnaVO qnaVO) throws Exception {
		
	}
	
	@PostMapping("add")
	public String add(@Valid QnaVO qnaVO, BindingResult bindingResult, MultipartFile[] attaches) throws Exception {
		if(bindingResult.hasErrors()) {
			log.info("BindingResult: {}", bindingResult);
			return "qna/add";
		}
		Integer result = qnaService.add(qnaVO ,attaches);
		return "redirect:./list";
	}
	
	@GetMapping("detail")
	public void getDetail(QnaVO qnaVO, Model model) throws Exception {
		qnaVO = qnaService.getDetail(qnaVO);
		model.addAttribute("qnaVO", qnaVO);
		log.info("QnaVO: {}", qnaVO);
	}
	
	@GetMapping("fileDown")
	public String fileDown(QnaFileVO qnaFileVO, Model model) throws Exception {
		 qnaFileVO = qnaService.getFileDetail(qnaFileVO);
		 model.addAttribute("qnaFileVO", qnaFileVO);
		 
		 return "fileDownView";
	}
	
}
