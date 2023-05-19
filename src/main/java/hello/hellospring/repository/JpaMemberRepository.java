package hello.hellospring.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.transaction.annotation.Transactional;

import hello.hellospring.domain.Member;

//Jpa는 데이터 저장, 변경할 때 항상 @Transactional 어노테이션 있어야 함
@Transactional
public class JpaMemberRepository implements MemberRepository {

	//Jpa쓰려면 필요
	private final EntityManager em;
	
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Member save(Member member) {
		em.persist(member);
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		Member member = em.find(Member.class, id);
		return Optional.ofNullable(member);
	}

	@Override
	public Optional<Member> findByName(String name) {
		List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
			.setParameter("name", name)
			.getResultList();
		
		return result.stream().findAny();
	}

	@Override
	public List<Member> findAll() {
		// 객체 대상으로 쿼리
		return em.createQuery("select m from Member m", Member.class)
				.getResultList();
	}
	 

}
