package springproject.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springproject.board.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
