package personalProject.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personalProject.shoppingmall.domain.Member;
import personalProject.shoppingmall.repository.MemberRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    //회원 가입
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        List<Member> findMembersEmail = memberRepository.findByEmail(member.getEmail());
        //EXCEPTION
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }else if(!findMembersEmail.isEmpty()){
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }
    }

    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //회원 1명 조회
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
