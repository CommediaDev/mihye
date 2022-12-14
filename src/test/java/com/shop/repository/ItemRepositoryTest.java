package com.shop.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import com.shop.entity.QItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

//import static org.junit.jupiter.api.ions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    //영속성 컨텍스트를 사용하기위해 @PersistenceContext 어노테이션을 이용하여 EntityManager 빈 객체 주입
    @PersistenceContext
    EntityManager em;
    
    /*@Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest(){
        Item item = new Item();
        item.setItemNm("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());
        Item savedItem = itemRepository.save(item);
        System.out.println(savedItem.toString());
    }*/

    /*public void createItemList() {
        for (int i = 1; i <= 10; i++) {
            Item item = new Item();
            item.setItemNm("테스트 상품" + i);
            item.setPrice(10000 + i);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item savedItem = itemRepository.save(item);
        }
    }*/
    /*@Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNmTest(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemNm("테스트 상품5");
        for (Item item : itemList){
            System.out.println(item.toString());
        }
    }*/
   /* @Test
    @DisplayName("상품명, 상품상세설명 or 테스트")
    public void findByItemNmOrItemDetailTest(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트  상품1", "테스트 상품 상세 설명5");
        for (Item item : itemList){
            System.out.println(item.toString());
        }
    }*/
    /*@Test
    @DisplayName("가격 LessThan 테스트")
    public void findByPriceLessThanTest(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThan(10005);
        for (Item item : itemList){
            System.out.println(item.toString());
        }
    }*/
    /*@Test
    @DisplayName("가격 내림차순 조회 테스트")
    public void findByPriceLessThanOrderByPriceDesc(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
        for (Item item : itemList){
            System.out.println(item.toString());
        }
    }*/
    /*@Test
    @DisplayName("@Query를 이용한 상품 조회 테스트")
    public void findByItemDetailTest(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemDetail("테스트 상품 상세 설명");
        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }*/
    /*@Test
    @DisplayName("Querydsl 조회 테스트1")
    public void queryDslTest(){
        this.createItemList();
        //JPAQueryFactory를 이용하여 동적으로 쿼리 생성 / 생성자 파라미터는 EntityManager 객체를 넣어줌
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        //Querydsl을 통해 쿼리를 생성하기 위해 플러그인을 통해 자동으로 생성된 QItem 객체 이용
        QItem qItem = QItem.item;
        //자바 소스코드이지만 SQL과 비슷하게 작성가능
        JPAQuery<Item> query = queryFactory.selectFrom(qItem)
                .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(qItem.itemDetail.like("%" + "테스트 상품 상세 설명" + "%"))
                .orderBy(qItem.price.desc());

        //fetch를 이용하여 쿼리 결과를 리스트로 반환
        List<Item> itemList = query.fetch();

        for (Item item : itemList){
            System.out.println(item.toString());
        }
    }*/
    /*public void createItemList2(){
        for (int i=1; i<=5; i++){
            Item item = new Item();
            item.setItemNm("테스트 상품" + i);
            item.setPrice(10000 + i);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            itemRepository.save(item);
        }

        for (int i=6; i<=10; i++) {
            Item item = new Item();
            item.setItemNm("테스트 상품" + i);
            item.setPrice(10000 + i);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SOLD_OUT);
            item.setStockNumber(0);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            itemRepository.save(item);
        }
    }
    @Test
    @DisplayName("상품 Querydsl 조회 테스트 2")
    public void queryDslTest2(){
        this.createItemList2();
        //BooleanBuilder는 쿼리에 들어갈 조건을 만들어주는 빌더, predicate를 구현하고 있으며 메소드체인 형식으로 사용
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QItem item = QItem.item;
        String itemDetail = "테스트 상품 상세 설명";
        int price = 10003;
        String itemSellStat = "SELL";

        booleanBuilder.and(item.itemDetail.like("%" + itemDetail + "%"));
        booleanBuilder.and(item.price.gt(price));

        //상품의 판매상태가 SELL일때만 booleanBuilder에 판매상태 조건을 동적으로 추가함
        if (StringUtils.equals(itemSellStat, ItemSellStatus.SELL)){
            booleanBuilder.and(item.itemSellStatus.eq(ItemSellStatus.SELL));
        }

        //페이징을 조회하도록 PageRequest.of 메소드를 이용해 Pageable 객체 생성
        //첫번째 인자는 조회할 페이지 번호, 두번째 인자는 한페이지당 조회할 데이터의 갯수
        Pageable pageable = PageRequest.of(0, 5);
        //QueryDslPredicateExecutor 인터페이스에서 정의한 findAll() 메소드를 이용해 데이터를 Page 객체로 받음
        //조건에 맞는 페이지 데이터 반환
        Page<Item> itemPageResult = itemRepository.findAll(booleanBuilder, pageable);
        System.out.println("total elements : " + itemPageResult.getTotalElements());

        List<Item> resultItemList = itemPageResult.getContent();
        for (Item resultItem : resultItemList){
            System.out.println(resultItem.toString());
        }
    }*/
}