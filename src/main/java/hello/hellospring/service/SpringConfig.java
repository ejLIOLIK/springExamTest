package hello.hellospring.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

//컴포넌트 스캔 아닌 자바코드로 스프링빈 설정
@Configuration
public class SpringConfig {

	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}
	
	@Bean MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}	
}
