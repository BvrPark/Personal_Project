package springproject.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springproject.board.domain.Question;

import java.time.LocalDateTime;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question findBySubject(String subject);
    Question findByContent(String content);
    List<Question> findBySubjectLike(String subject);

}
