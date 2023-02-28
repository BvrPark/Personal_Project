package springproject.board.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springproject.board.domain.Member;
import springproject.board.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    
    //회원가입
    public Member create(String name, String password, String email){
        
        Member member = new Member();
        member.setName(name);
        member.setPassword(passwordEncoder.encode(password));   //비밀번호를 암호화해서 저장
        member.setEmail(email);
        memberRepository.save(member);

        return member;
    }
}
