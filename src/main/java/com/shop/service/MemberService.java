package com.shop.service;

import com.shop.entity.Member;
import com.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
// @Transactional 로직을 처리하다가 에러가 발생하면 변경된 로직을 수행하기 전으로 롤백
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    //스프링부트에서 제공하는 로그인 클래스를 사용하기 위해 UserDetailService 구현
    
    //@RequiredArgsConstructor은 final이나 @NonNull이 붙은 필드 생성자를 생성해줌
    //빈에 생성자가 1개이고 생성자 파라미터 타입으로 빈이 등록가능하다면 @Autowired 어노테이션 없이 의존성 주입 가능
    private final MemberRepository memberRepository;

    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member){
        Member findMember = memberRepository.findByEmail(member.getEmail());
        //이미 가입된 회원의 경우 IllegalStateException예외를 발생시킴
        if (findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);

        if(member == null){
            throw new UsernameNotFoundException(email);
        }

        return User.builder() //UserDetail을 구현하고있는 User 객체 반환 / User를 객체로 생성하기 위해 파라미터 전달
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }
}
