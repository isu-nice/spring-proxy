package hello.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor; // 패키지 이름에 주의!
import org.aopalliance.intercept.MethodInvocation;

/**
 * 프록시가 제공하는 부가 기능 로직을 Advice 라고 한다
 */
@Slf4j
public class TimeAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();

        /*
         * target 클래스의 정보가 MethodInvocation에 모두 포함되어있다.
         * 따라서 target을 파라미터로 전달받지 않아도 된다.
         */
        Object result = invocation.proceed();

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("TimeProxy 종료, resultTime={}ms", resultTime);
        return result;
    }
}
