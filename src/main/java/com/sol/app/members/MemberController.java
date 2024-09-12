package com.sol.app.members;

import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
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
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		model.addAttribute("memberVO", memberVO);
		return "member/update";
	}

	@PostMapping("update")
	public String update(@Validated(MemberUpdateGroup.class) MemberVO memberVO, BindingResult bindingResult)
			throws Exception {
		if (bindingResult.hasErrors()) {
			return "member/update";
		}
		return "redirect:/member/mypage";
	}

	@GetMapping("mypage")
	public void mypage(HttpSession session) throws Exception {
		Enumeration<String> en = session.getAttributeNames();
		while (en.hasMoreElements()) {
			log.info(en.nextElement());
		}
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
		log.info("SecurityContextImpl: {}", securityContextImpl);
		SecurityContext securityContext = SecurityContextHolder.getContext();
		log.info("SecurityContext: {}", securityContext);

		log.info("Authentication: {}", securityContext.getAuthentication());
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) securityContext
				.getAuthentication();
		log.info(token.getPrincipal().toString());
		// log.info(token.getCredentials().toString());
		log.info(token.getAuthorities().toString());
		log.info(token.getDetails().toString());
		log.info(token.getName());
	}

	@GetMapping("add")
	public void add(MemberVO memberVO) throws Exception {

	}

	@PostMapping("add")
	public String add(@Validated(MemberAddGroup.class) MemberVO memberVO, BindingResult bindingResult)
			throws Exception {
		boolean check = memberService.memberValidate(memberVO, bindingResult);
		if (check) {
			return "member/add";
		}
		/*
		 * if(bindingResult.hasErrors()) { log.error("Error: {}", bindingResult); return
		 * "/member/add"; }
		 */
		Integer result = memberService.add(memberVO);

		return "redirect:../";
	}

	@GetMapping("login")
	public String login() throws Exception {
		SecurityContext context = SecurityContextHolder.getContext();

		if (context == null) {
			return "member/login";
		}

		String user = context.getAuthentication().getPrincipal().toString();

		if (user.equals("anonymousUser")) {
			return "member/login";
		}

		return "redirect:/";
	}

	// Security 기능 사용시 아래 메서드는 더이상 사요하지 않음 (스프링이 가로챔)
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
	
	@GetMapping("check")
	public void check(Model model) throws Exception {
		model.addAttribute("msg", "다른 곳에서 로그인 되었습니다.");
		model.addAttribute("path", "/member/login");
	}
}
