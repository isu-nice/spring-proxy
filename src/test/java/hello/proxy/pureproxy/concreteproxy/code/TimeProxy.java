package hello.proxy.pureproxy.concreteproxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeProxy extends ConcreteLogic{ // 인터페이스가 아니라 클래스인 ConcreteLogic을 상속받음

    private ConcreteLogic concreteLogic;

    public TimeProxy(ConcreteLogic concreteLogic) {
        this.concreteLogic = concreteLogic;
    }

    @Override
    public String operation() {
      log.info("TimeDecorator 실행");

        long startTime = System.currentTimeMillis();

        String result = concreteLogic.operation(); // 실행

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("TimeDecorator 종료 resultTime => {}ms", resultTime);
        return result;
    }
}