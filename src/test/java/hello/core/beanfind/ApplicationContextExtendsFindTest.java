package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다")
    void findBeanByParentTypeDuplicate(){
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> context.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다")
    void findBeanByParentTypeBeanName(){
        DiscountPolicy rateDiscountPolicy = context.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 특정 하위 타입을 지정하면 된다, 별로 안좋은 방법")
    void findBeanByParentTypeBeanType(){
        DiscountPolicy rateDiscountPolicy = context.getBean(RateDiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 조회시")
    void findAllBeanByParentType(){
        Map<String, DiscountPolicy> beansOfType = context.getBeansOfType(DiscountPolicy.class);

        for (String key : beansOfType.keySet()){
            System.out.println("key = "+ key + " value =" + beansOfType.get(key));
        }

        System.out.println("beansOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("부모 타입으로 조회시 -Object")
    void findAllBeanByObjectType(){
        Map<String, Object> beansOfType = context.getBeansOfType(Object.class);

        for (String key : beansOfType.keySet()){
            System.out.println("key = "+ key + " value =" + beansOfType.get(key));
        }


    }

}
