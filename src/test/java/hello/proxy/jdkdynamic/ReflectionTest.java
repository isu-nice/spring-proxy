package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    @DisplayName("기본 호출 로직")
    void reflection0() {
        Hello target = new Hello();

        // 공통 로직1 시작
        log.info("start");
        String result1 = target.callA(); // 호출하는 메서드가 다름
        log.info("result={}", result1);
        // 공통 로직1 종료

        // 공통 로직2 시작
        log.info("start");
        String result2 = target.callB(); // 호출하는 메서드가 다름
        log.info("result={}", result2);
        // 공통 로직2 종료
    }

    @Test
    @DisplayName("클래스, 메서드 메타 정보 확인 테스트")
    void reflection1() throws Exception {
        // 클래스 메타 정보 (내부 클래스는 구분을 위해 $ 표시를 사용함)
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        // callA 메서드 메타 정보
        Method methodCallA = classHello.getMethod("callA");
        Object result1 = methodCallA.invoke(target);
        log.info("result1={}", result1);

        // callB 메서드 메타 정보
        Method methodCallB = classHello.getMethod("callB");
        Object result2 = methodCallB.invoke(target);
        log.info("result2={}", result2);
    }

    /**
     * 리블렉션의 장단점
     * 장점: 클래스와 메서드의 메타정보를 사용해서 앱을 동적으로 만들 수 있따.
     * 단점: 리플렉션은 런타임에 동작하기 떄문에 컴파일 시점에 오류를 잡을 수 없다.
     * 프레임워크 개발이나 매우 일반적인 공통 처리가 필요할 때 등 특수한 경우에만 주의해서 사용해야 함
     */
    @Test
    @DisplayName("리플렉션 활용 테스트")
    void reflection2() throws Exception {
        // 클래스 메타 정보 (내부 클래스는 구분을 위해 $ 표시를 사용함)
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        // callA 메서드 메타 정보
        Method methodCallA = classHello.getMethod("callA");
        dynamicCall(methodCallA, target);

        // callB 메서드 메타 정보
        Method methodCallB = classHello.getMethod("callB");
        dynamicCall(methodCallB, target);
    }

    // 통합된 공통 처리 로직
    private void dynamicCall(Method method, Object target) throws Exception {
        log.info("start");
        Object result = method.invoke(target);
        log.info("result={}", result);
    }

    @Slf4j
    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }
    }
}
