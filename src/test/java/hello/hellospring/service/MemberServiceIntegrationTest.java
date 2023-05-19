package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

//스프링통합테스트를 위한 어노테이션.
@SpringBootTest
//테스트를 위해 DB에 넣은 데이터를 롤백 해주는 어노테이션. 반복적인 테스트가 가능.
@Transactional
class MemberServiceIntegrationTest {

	@Autowired MemberService memberService;
	//JdbcMemberRepository로 선언 하지 않는 이유 
	//SpringConfig에서 이미 JdbcMemberRepository로 구현체를 설정 해줬기 때문에
	//MemberRepository로 주입하면 JdbcMemberRepository가 된다.
	@Autowired MemberRepository memberRepository ;
	
	@Test
	void Join() {
		Member member = new Member();
		member.setName("newMemberData");
		
		Long saveId = memberService.join(member);
		
		Member findMember = memberService.findOne(saveId).get();
		assertThat(member.getName()).isEqualTo(findMember.getName());
	}

	/*
	 * 중복 케이스 예외처리 정상여부 테스트
	 */
	@Test
	public void JoinValid() {
		Member member1 = new Member();
		member1.setName("newMemberData");

		Member member2 = new Member();
		member2.setName("newMemberData");

		memberService.join(member1);
		IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
		
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");	
	}
}
