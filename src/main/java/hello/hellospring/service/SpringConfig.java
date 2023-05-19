package hello.hellospring.service;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;

//컴포넌트 스캔 아닌 자바코드로 스프링빈 설정
@Configuration
public class SpringConfig {

	/*
	private DataSource dataSource;

	@Autowired
	public SpringConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	*/	
	
	private EntityManager em;
	
	@Autowired
	public SpringConfig(EntityManager em) {
		this.em = em;
	}
	
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}
	
	@Bean MemberRepository memberRepository() {
		//구현체만 바꿔 끼워서 DB 연동하기
		//return new MemoryMemberRepository();
		//return new JdbcMemberRepository(dataSource);
		return new JpaMemberRepository(em);
	}

}
