package com.shop.entity;

import com.shop.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter @ToString
@Entity
@Table(name = "item")
public class Item {

    //GeneratedValue 생성 전략 4가지
    //GenerationType.AUTO: JPA 구현체가 자동으로 생성 전략 결정 -> 데이터베이스에 의존하지 않고 아래 3가지 전략중 자동 생성
    //GenerationType.IDENTITY: 기본키 생성을 데이터 베이스에 위임
    //GenerationType.SEQUENCE: 데이터베이스 시퀀스 오브젝트를 이용한 기본키 생성 (@SequenceGenerator 사용해야함)
    //GenerationType.TABLE: 키 생성용 테이블 사용 (@TableGenerator 사용해야함)

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //상품코드

    //length의 기본값은 255
    @Column(nullable = false, length = 50)
    private String itemNm; //상품명

    @Column(name = "price", nullable = false)
    private int price;  //가격

    @Column(nullable = false)
    private int stockNumber; //재고 수량

    //Lob는 CLOB과 BLOB로 나뉘며, CLOB은 문자형 대용량 파일 저장 / BLOB은 이미지, 사운드 등 멀티미디어 데이터를 다룰때 사용
    @Lob
    @Column(nullable = false)
    private String itemDetail; //상품 상세 설명

    //Enumerated는 enum타입 매핑 시 사용
    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;  //상품 판매 상태
    
    private LocalDateTime regTime;  //등록상태

    private LocalDateTime updateTime;   //수정 시간
    
}
