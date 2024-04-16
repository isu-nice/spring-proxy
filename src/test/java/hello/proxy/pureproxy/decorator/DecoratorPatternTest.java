package hello.proxy.pureproxy.decorator;

import hello.proxy.pureproxy.decorator.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class DecoratorPatternTest {

    @Test
    void noDecorator() {
        Component realComponent = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(realComponent);
        client.execute();
    }

    @DisplayName("메시지 데코레이터 테스트")
    @Test
    void decorator1() {
        RealComponent realComponent = new RealComponent();
        MessageDecorator messageDecorator = new MessageDecorator(realComponent);
        DecoratorPatternClient client = new DecoratorPatternClient(messageDecorator);
        client.execute();
    }

    @DisplayName("메시지 데코레이터 + 타임 데코레이터 (체인)테스트")
    @Test
    void decorator2() {
        RealComponent realComponent = new RealComponent();

        // 메시지 데코레이터는 실제 데이터를 호출
        MessageDecorator messageDecorator = new MessageDecorator(realComponent);

        // 타임 데코레이터는 메시지 데코레이터를 의존, 호출
        TimeDecorator timeDecorator = new TimeDecorator(messageDecorator);

        // 클라이언트는 타임 데코레이터를 호출
        DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);

        client.execute();
    }
}
