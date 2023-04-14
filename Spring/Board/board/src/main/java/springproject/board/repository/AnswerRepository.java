package springproject.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import springproject.board.domain.Answer;
import springproject.board.domain.Question;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query("select a from Answer a where a.question = :question")
    List<Answer> answerList(@Param("question") Question question);

}
