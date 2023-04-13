package JB.JBspring.service;

import JB.JBspring.domain.Member;
import JB.JBspring.repository.MemberRepository;
import JB.JBspring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService {


    private final MemberRepository memberRepository;
    //member Repository를 외부에서 넣어주도록 설계.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }



    /**
     * 회원 가입
     */
    public Long join(Member member){
        //같은이름이 있는 중복회원X (이미 optional이기 때문의 optional interface의 )

        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member){
        memberRepository.findByName(member.getName())
                .ifPresent(m->{
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
