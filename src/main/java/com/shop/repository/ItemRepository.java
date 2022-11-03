package com.shop.repository;

import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>,
QuerydslPredicateExecutor<Item> {
//QuerydslPredicateExecutor는 조건이 맞다고 판단되는 것을 반환해줌
    
    List<Item> findByItemNm(String itemNm);
    
    //상품을 상품명과 상품 상세 설명을 OR 조건을 이용하여 조회하는 쿼리
    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);
    
    //파라미터로 넘어온 price 변수보다 값이 작은 상품 데이터를 조회하는 쿼리
    List<Item> findByPriceLessThan(Integer price);

    //상품 가격이 높은 순으로 조회
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);
    
    //상세설명을 파라미터로 받아 상품 상세설명에 포함하고 있는 데이터 조회, 정렬순서 가격 높은 순
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);

    //@Param 어노테이션은 파라미터로 넘어온 값이 JPQL에 들어갈 변수로 지정
    //itemDetail 변수를 like % % 사이에 :itemDetail 값으로 들어감
    //기존 데이터베이스에서 사용하던 쿼리를 사용해야하는 경우 nativeQuery를 이용할 수 있음 (...desc", nativeQuery = ture)
    
}
