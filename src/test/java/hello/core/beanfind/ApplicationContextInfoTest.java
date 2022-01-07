package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈을 찾아 출력하기")
    void findAllBean(){
        String[] beanDefinitionNames = context.getBeanDefinitionNames();

        for (String name : beanDefinitionNames){
            Object bean = context.getBean(name);
            System.out.println("name = "+name + " object = " + bean);
        }
    }

    @Test
    @DisplayName("어플리케이션을 찾아 출력하기")
    void findApplicationBean(){
        String[] beanDefinitionNames = context.getBeanDefinitionNames();

        for (String name : beanDefinitionNames){
            BeanDefinition beanDefinition = context.getBeanDefinition(name);

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = context.getBean(name);
                System.out.println("name = "+name + " object = " + bean);
            }

        }
    }
}
