package hello.core.order;

import hello.core.config.AppConfig;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder(){
        // given
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        // when
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        // then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

    @Test
    @DisplayName("필드 주입시 생기는 문제점")
    void fieldInjectionTest(){
//        OrderServiceImpl orderService = new OrderServiceImpl(); // 스프링 없이 생성자로 객체를 만들경우, OrderServiceImpl가 의존하는 필드에 객체를 넣을 방법이 없다.
//        orderService.createOrder(1L, "itemA", 10000); // NullPointException 발생

        // 결국에 setter를 통해 주입해야한다 -> 스프링의 setter 주입 기능을 이용하자
//        orderService.setDiscountPolicy(new RateDiscountPolicy());
//        orderService.setMemberRepository(new MemoryMemberRepository());
    }
}
