package com.shop.config;

import com.shop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //WebSecurityConfigureAdapter를 상속받는 클래스에 @EnableWebSecurity 어노테이션을 선언하면
    //SpringSecurityFilterChain이 자동으로 포함됨
    //WebSecurityConfigurerAdapter를 상속받아서 메소드 오버라이딩을 통해 보안설정을 커스터마이징

    @Autowired
    MemberService memberService;


    //http 요청에 대한 보안을 설정함. (페이지권한 설정, 로그인페이지 설정, 로그아웃메소드 등)
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.formLogin()
                .loginPage("/members/login")
                .defaultSuccessUrl("/")
                .usernameParameter("email")
                .failureUrl("/members/login/error")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                .logoutSuccessUrl("/");
    }

    //데이터베이스 해킹시에 회원정보가 그대로 노출 될 수 있으므로 BCryptPasswordEncoder의 해시함수를 이용하여 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        //스프링에서 인증은 AuthenticationManager를 통해 이루어짐
        //AuthenticationManagerBuilder가 AuthenticationManager를 생성함
        //userDetailService를 구현하고 있는 객체로 memberService를 지정해주고 비밀번호 암호화
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }
}
