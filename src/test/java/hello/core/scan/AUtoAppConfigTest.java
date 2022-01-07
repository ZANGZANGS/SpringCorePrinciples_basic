package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class AUtoAppConfigTest {

    @Test
    @DisplayName("자동 설정 컴포넌트 등록 테스트")
    void basicScan(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = context.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);

    }
}
