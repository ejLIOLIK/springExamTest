package hello.hellospring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;

@Controller
public class MemberController {
	
	private final MemberService memberService;
	
	// 필드 생성 이용한 방법
	//@Autowired private final MemberService memberService;
	
	// setter 사용한 방법
	/*
	@Autowired
	public setMemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	*/
	
	// 매번 객체 생성하지 않고 Controller 생성 시 연결
	// 생성자 이용한 방법 < 권장!
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService; 
	} 
	
	@GetMapping("/members/new")
	public String createForm() {
		return "members/createMemberForm";
	}
	
	@PostMapping("/members/new")
	public String create(MemberForm form) {
		Member member = new Member();
		member.setName(form.getName());
		
		memberService.join(member);
		
		return "redirect:/";
	}
	
	@GetMapping("members")
	public String list(Model model) {
		List<Member> members = memberService.findMembers();
		model.addAttribute("members", members);
		return "members/memberList";
	}
	

	
}
