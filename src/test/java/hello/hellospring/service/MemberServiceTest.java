package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

class MemberServiceTest {

	MemberService memberService;
	MemoryMemberRepository memberRepository;
	
	@BeforeEach
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}
	
	@AfterEach
	public void afterEach() {
		memberRepository.clearStore();
	}
	
	@Test
	void Join() {
		//given
		Member member = new Member();
		member.setName("first");
		
		//when
		Long saveId = memberService.join(member);
		
		//then
		Member findMember = memberService.findOne(saveId).get();
		assertThat(member.getName()).isEqualTo(findMember.getName());
	}

	/*
	 * 중복 케이스 예외처리 정상여부 테스트
	 */
	@Test
	public void JoinValid() {
		//given
		Member member1 = new Member();
		member1.setName("hana");

		Member member2 = new Member();
		member2.setName("hana");

		//when
		memberService.join(member1);
		IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
		
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
		
		/*		
 		try {
			memberService.join(member2);
			fail();
		} catch (IllegalStateException e) {
			//정상적인 예외처리
			//기존에 입력한 에러 메시지로 출력되는지 확인하는 작업
			assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
		}
		*/		
	}

	@Test
	void FindMembers() {
		//given
		
		//when
		
		//then
	}

	@Test
	void FindOne() {
		//given
		
		//when
		
		//then
	}

}
