package hello.core.scan.filter;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

@Target(ElementType.TYPE) // Class에 붙는 애노테이션
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {

}
