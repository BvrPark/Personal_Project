package springproject.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import springproject.board.domain.Answer;
import springproject.board.domain.Question;

import java.time.LocalDateTime;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question findBySubject(String subject);
    Question findByContent(String content);
    List<Question> findBySubjectLike(String subject);

    Page<Question> findAll(Pageable pageable);

}
