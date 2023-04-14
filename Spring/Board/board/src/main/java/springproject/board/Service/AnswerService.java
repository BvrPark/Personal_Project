package springproject.board.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springproject.board.domain.Answer;
import springproject.board.domain.Question;
import springproject.board.repository.AnswerRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public void create(Question question, String content){
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        this.answerRepository.save(answer);
    }
}
