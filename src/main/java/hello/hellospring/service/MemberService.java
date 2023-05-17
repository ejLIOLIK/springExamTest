package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

@Service
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	// 리포짓토리 의존성 주입
	// @Repository와 연결
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	/**
	 * 회원 가입
	 */
	public Long join(Member member) {
		// 중복 유효성 검사
		validateDuplicateMember(member);
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		// if ! null 처럼 데이터 존재하는지 확인하는 ifPresent
		memberRepository.findByName(member.getName())
			  .ifPresent(m -> {
				throw new IllegalStateException("이미 존재하는 회원입니다.");
			});
	}
	
	/**
	 * 전체 회원 조회
	 */
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	
	public Optional<Member> findOne(Long memberId){
		return memberRepository.findById(memberId);
	}

}
