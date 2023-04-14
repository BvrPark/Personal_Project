package springproject.board.form;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter @Setter
public class MemberCreateForm {

    @Size(min = 3, max = 15)
    @NotEmpty(message = "ID를 입력해주세요.")
    private String name;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String passwordCheck;

    @NotEmpty(message = "이메일을 입력해주세요.")
    @Email
    private String email;
}
