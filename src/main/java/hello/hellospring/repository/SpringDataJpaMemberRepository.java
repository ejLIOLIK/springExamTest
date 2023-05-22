package hello.hellospring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.hellospring.domain.Member;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

	// 스프링 데이터 JPA 개념
	// 스프링 데이터에서 구현체를 만들어줌
	
	// select m from Member m where m.name = ?
	@Override
	Optional<Member> findByName(String name);
}
