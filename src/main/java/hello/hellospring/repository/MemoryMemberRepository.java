package hello.hellospring.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import hello.hellospring.domain.Member;

public class MemoryMemberRepository implements MemberRepository {
	
	private static Map<Long, Member> store = new HashMap<>();
	private static long sequece = 0L;
	
	@Override
	public Member save(Member member) {
		member.setId(++sequece);
		store.put(member.getId(), member);
		return member; 
	}
	
	@Override
	public Optional<Member> findById(Long id){
		// null 일 때 관리해줄 수 있음
		return Optional.ofNullable(store.get(id));
	}
	
	@Override
	public Optional<Member> findByName(String name){
		// 람다식 ...?
		return store.values().stream()
			.filter(member -> member.getName().equals(name))
			.findAny();
	}
	
	@Override
	public List<Member> findAll(){
		return new ArrayList<>(store.values());
	}
	
	public void clearStore() {
		store.clear();
	}
}
