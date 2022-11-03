package com.shop.entity;

import com.shop.constant.Role;
import com.shop.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter @Setter
@ToString
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    //동일한 값이 데이터베이스에 들어올 수 없도록 unique 속성 지정
    @Column(unique = true)
    private String email;

    private String password;

    private String address;

    //Enum Type 자동순번 생성 시 문제 발생 할 수 있으므로 String으로 저장
    @Enumerated(EnumType.STRING)
    private Role role;

    //member 엔티티 생성하는 메서드, member eneity에 회원을 생성하는 경우 변경사항 발생시 한군데만 수정하기 때문에 편리함
    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getAddress());
        //스프링시큐리티 설정 클래스에 등록한 BCryptPasswordEncoder Bean을 파라미터로 넘겨서 비밀번호 암호화
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.USER);
        return member;
    }
}
