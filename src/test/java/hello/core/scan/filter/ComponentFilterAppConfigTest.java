package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String name : beanDefinitionNames){
            BeanDefinition beanDefinition = context.getBeanDefinition(name);

            System.out.println("beanName = " + name+ " beanDefinition = " + beanDefinition);
        }



        BeanA bean = context.getBean("beanA", BeanA.class);
        assertThat(bean).isNotNull();

        assertThrows(NoSuchBeanDefinitionException.class,
                () -> context.getBean("beanB", BeanB.class));

    }


    @Configuration
    @ComponentScan(
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes =  MyIncludeComponent.class),
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes =  MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig{

    }

}
