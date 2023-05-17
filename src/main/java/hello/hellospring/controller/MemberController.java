package hello.hellospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
	
}
