package personalProject.shoppingmall.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import personalProject.shoppingmall.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    //회원 가입
    public void save(Member member){
        em.persist(member);
    }

    //회원 1명 찾기
    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    //회원 전체 찾기
    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    //이름으로 회원 찾기(여러명)
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
