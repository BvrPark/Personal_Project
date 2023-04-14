package springproject.board.form;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class AnswerForm {

    @NotEmpty(message = "답변을 달아주세요.")
    private String content;
}
