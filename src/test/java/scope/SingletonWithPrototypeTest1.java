package scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProtoTypeBean.class);

        ProtoTypeBean bean1 = ac.getBean(ProtoTypeBean.class);
        bean1.addCount();
        Assertions.assertThat(bean1.getCount()).isEqualTo(1);

        ProtoTypeBean bean2 = ac.getBean(ProtoTypeBean.class);
        bean2.addCount();
        Assertions.assertThat(bean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProtoTypeBean.class, ClientBean.class);

        ClientBean bean1 = ac.getBean(ClientBean.class);
        int count1 = bean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean bean2 = ac.getBean(ClientBean.class);
        int count2 = bean2.logic();
        Assertions.assertThat(count2).isEqualTo(1);
    }

    @Scope
    static class ClientBean{
//        private ProtoTypeBean protoTypeBean; //생성 시점에 주입된다.

        @Autowired
        private Provider<ProtoTypeBean> protoTypeBeanProvider;
//        private ObjectProvider<ProtoTypeBean> protoTypeBeanProvider;

        public int logic(){
            ProtoTypeBean protoTypeBean = protoTypeBeanProvider.get();
            protoTypeBean.addCount();
            int count = protoTypeBean.getCount();
            return count;

        }
    }


    @Scope("prototype")
    static class ProtoTypeBean{
        private int count = 0;

        public void addCount(){
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("ProtoTypeBean.init " + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("ProtoTypeBean.destroy " + this);
        }
    }
}
