package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {

    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProtoTypeBean.class); // ProtoTypeBean.class에 @Component 안붙어도 됨

        System.out.println("find prototypeBean1");
        ProtoTypeBean prototypeBean1 = ac.getBean(ProtoTypeBean.class);

        System.out.println("find prototypeBean2");
        ProtoTypeBean prototypeBean2 = ac.getBean(ProtoTypeBean.class);

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        // 클라이언트가 직접 호출
        prototypeBean1.destory();
        prototypeBean2.destory();

        ac.close(); // 스프링 컨테이너가 종료될 때, @PreDestroy 호출X
    }

    @Scope("prototype")
    static class ProtoTypeBean{
        @PostConstruct
        public void init(){
            System.out.println("ProtoTypeBean.init");
        }

        @PreDestroy
        public void destory(){
            System.out.println("ProtoTypeBean.destory");
        }
    }
}
