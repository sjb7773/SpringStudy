package JB.JBspring;

import JB.JBspring.repository.MemberRepository;
import JB.JBspring.repository.MemoryMemberRepository;
import JB.JBspring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
