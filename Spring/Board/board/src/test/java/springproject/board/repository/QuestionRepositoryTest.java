package springproject.board.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import springproject.board.domain.Question;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    public void 데이터조회() throws Exception{
        //given
        Question q1 = new Question();
        q1.setSubject("제목1입니다.");
        q1.setContent("내용1입니다.");
        Question saved1 = questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("제목2입니다.");
        q2.setContent("내용2입니다.");
        Question saved2 = questionRepository.save(q2);

        //when
        Question findById1 = questionRepository.findById(saved1.getId()).get();
        Question findBySubject1 = questionRepository.findBySubject(saved1.getSubject());
        Question findByContent1 = questionRepository.findByContent(saved1.getContent());

        Question findById2 = questionRepository.findById(saved2.getId()).get();
        Question findBySubject2 = questionRepository.findBySubject(saved2.getSubject());
        Question findByContent2 = questionRepository.findByContent(saved2.getContent());

        List<Question> findSubjectLike = questionRepository.findBySubjectLike("%제목%");


        //then
        assertThat(findById1.getId()).isEqualTo(q1.getId());
        assertThat(findById2.getId()).isEqualTo(q2.getId());

        assertThat(findBySubject1.getSubject()).isEqualTo(q1.getSubject());
        assertThat(findBySubject2.getSubject()).isEqualTo(q2.getSubject());

        assertThat(findByContent1.getContent()).isEqualTo(q1.getContent());
        assertThat(findByContent2.getContent()).isEqualTo(q2.getContent());

        assertEquals(q1.getSubject(),findSubjectLike.get(0).getSubject());
    }

    @Test
    public void 데이터수정() throws Exception{
        //given
        Question question = new Question();
        question.setSubject("제목1입니다.");
        question.setContent("내용1입니다.");
        questionRepository.save(question);

        //when
        Question find = questionRepository.findById(question.getId()).get();
        find.setSubject("제목을 수정했습니다.");
        questionRepository.save(find);

        //then
        assertEquals(find.getSubject(),"제목을 수정했습니다.");

    }

    @Test
    public void 데이터삭제() throws Exception{
        //given
        Question q1 = new Question();
        q1.setSubject("제목입니다.1");
        Question save1 = questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("제목입니다.2");
        Question save2 = questionRepository.save(q2);

        assertEquals(2, questionRepository.count());
        //when
        Question findById = questionRepository.findById(save1.getId()).get();
        questionRepository.delete(findById);

        //then
        assertEquals(1,questionRepository.count());

    }

}