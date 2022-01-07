package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // basePackages = "hello.core", 디폴트: 현재 위치한 클래스의 패키지부터 모두 탐색한다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)

)
public class AutoAppConfig {
}
