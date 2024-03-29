package springproject.board.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter @Setter
public class QuestionForm {
    @NotEmpty(message="제목을 적어주세요.")
    @Size(max=200)
    private String subject;

    @NotEmpty(message="내용을 적어주세요.")
    private String content;
}
