package springproject.board.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import springproject.board.domain.Answer;
import springproject.board.domain.Member;
import springproject.board.domain.Question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AnswerRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Test
    public void 답변조회() throws Exception{
        //given
        Question question = new Question();
        question.setSubject("제목입니다.");
        question.setContent("본문입니다.");
        questionRepository.save(question);

        Answer answer = new Answer();
        answer.setContent("답변입니다.");
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(questionRepository.findById(question.getId()).get());
        answerRepository.save(answer);

        //when
        Answer findByIdAnswer = answerRepository.findById(answer.getId()).get();

        //then
        assertEquals(findByIdAnswer.getQuestion(),question);

    }

    @Test
    public void 답변전체조회() throws Exception{
        //given
        Question question = new Question();
        question.setSubject("제목입니다.");
        question.setContent("본문입니다.");
        questionRepository.save(question);

        Answer answer = new Answer();
        answer.setContent("답변입니다.");
        answer.setQuestion(question);
        answer.setCreateDate(LocalDateTime.now());
        answerRepository.save(answer);

        Question findQuestion = questionRepository.findById(question.getId()).get();

        //when
        List<Answer> answerList = findQuestion.getAnswerList();

        //then
        assertThat(answerList.get(0).getContent()).isEqualTo("답변입니다.");

    }

}