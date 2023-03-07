package springproject.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springproject.board.Service.MemberService;
import springproject.board.form.MemberCreateForm;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String signup(MemberCreateForm memberCreateForm){
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid MemberCreateForm memberCreateForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "signup_form";
        }

        if(!memberCreateForm.getPassword().equals(memberCreateForm.getPasswordCheck())){
            //(필드명, 오류코드(맘대로 지정), 에러메시지)
            bindingResult.rejectValue("passwordCheck","passwordNotEqual","비밀번호가 일치하지 않습니다.");
            return "signup_form";
        }

        try {
            memberService.create(memberCreateForm.getName(),
                    memberCreateForm.getEmail(), memberCreateForm.getPassword());
        }catch(DataIntegrityViolationException e) {
            e.printStackTrace();
            //reject(오류코드, 오류메시지)
            bindingResult.reject("signupFailed", "이미 등록된 정보입니다.");
            return "signup_form";
        }catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup_form";
        }

        return "redirect:/";
    }

    //실제 로그인을 진행하는 Post 방식은 스프링 시큐리티가 대신 해준다.
    @GetMapping("/login")
    public String login() {
        return "login_form";
    }
}
