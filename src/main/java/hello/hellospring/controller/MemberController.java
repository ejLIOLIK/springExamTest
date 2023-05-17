package hello.hellospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import hello.hellospring.service.MemberService;

@Controller
public class MemberController {
	
	private final MemberService memberService;
	
	// 매번 객체 생성하지 않고 Controller 생성 시 연결
	// @Service와 연결
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
}
