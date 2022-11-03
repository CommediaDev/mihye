package com.shop.repository;

import com.shop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //회원가입 시 중복된 회원이 있는지 검사하기 위한 이메일로 회원을 검사하는 쿼리 메서드
    Member findByEmail(String email);
}
