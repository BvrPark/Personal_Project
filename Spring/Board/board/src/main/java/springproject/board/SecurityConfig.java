package springproject.board;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration  // 스프링의 환경설정 파일임을 나타내줌
@EnableWebSecurity  //모든 URL요청이 SpringSecurity의 제어를 받도록 만드는 애너테이션
public class SecurityConfig {

    @Bean   //모든 인증되지 않는 요청을 허락한다는 의미
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests().requestMatchers(
                        new AntPathRequestMatcher("/**")).permitAll()
                .and()  //httpSecurity 객체의 설정을 이어서 한다.
                    .csrf().ignoringRequestMatchers(    //h2-console로 시작하는 url은 csrf토큰의 검증을 하지 않는다는 의미
                            new AntPathRequestMatcher("/h2-console/**"))
                .and()
                .headers()
                .addHeaderWriter(new XFrameOptionsHeaderWriter(
                        XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
                .and()  //로그인 설정
                .formLogin()
                .loginPage("/member/login")   //로그인 url
                .defaultSuccessUrl("/");    //로그인 성공시 이동하는 디폴트 페이지

        return httpSecurity.build();
    }

    //암호화 시키기 위한 메서드
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
