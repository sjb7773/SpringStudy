package JB.JBspring.repository;

import JB.JBspring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository{

    //저장공간 ->key:회원id/value:Member static으로 공유변수일때는 동시성 문제때문에 concurrnetHashmap써야함
   private static Map<Long,Member> store = new HashMap<>();
   //키값을 생성해주는얘, 애도 동시성 문제때문에 atom long등을 해주어야함
   private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }


    public void clearStore(){
        store.clear();
    }
}
