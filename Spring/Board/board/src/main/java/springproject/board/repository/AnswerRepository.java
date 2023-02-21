package springproject.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springproject.board.domain.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
