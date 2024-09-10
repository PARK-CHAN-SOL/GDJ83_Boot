package com.sol.app.members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sol.app.validate.MemberAddGroup;
import com.sol.app.validate.MemberUpdateGroup;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member/*")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping("update")
	public String update(HttpSession session, Model model) throws Exception {
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		model.addAttribute("memberVO", memberVO);
		return "/member/update";
	}
	
	@PostMapping("update")
	public String update(@Validated(MemberUpdateGroup.class) MemberVO memberVO, BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()) {
			return "/member/update";
		}
		return "redirect:/member/mypage";
	}
	
	@GetMapping("mypage")
	public void mypage() throws Exception {
		
	}
	
	@GetMapping("add")
	public void add(MemberVO memberVO) throws Exception {
		
	}
	
	@PostMapping("add")
	public String add(@Validated(MemberAddGroup.class) MemberVO memberVO, BindingResult bindingResult) throws Exception {
		boolean check = memberService.memberValidate(memberVO, bindingResult);
		if(check) {
			return "/member/add";
		}
		/*
		 * if(bindingResult.hasErrors()) { log.error("Error: {}", bindingResult); return
		 * "/member/add"; }
		 */
		Integer result = memberService.add(memberVO);
		
		return "redirect:../";
	}
	
	@GetMapping("login")
	public void login() throws Exception {
		
	}
	
	//Security 기능 사용시 아래 메서드는 더이상 사요하지 않음 (스프링이 가로챔)
	/*
	 * @PostMapping("login") public String login(MemberVO memberVO, HttpSession
	 * session) throws Exception { memberVO = memberService.detail(memberVO);
	 * 
	 * if(memberVO != null) { session.setAttribute("member", memberVO); }
	 * 
	 * return "redirect:../"; }
	 */
	
	@GetMapping("logout")
	public String logout(HttpSession session) throws Exception {
		session.invalidate();
		return "redirect:../";
	}
}
