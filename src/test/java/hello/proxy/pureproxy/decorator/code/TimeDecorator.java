package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeDecorator implements Component {

    private Component component;

    public TimeDecorator(Component component) {
        this.component = component;
    }

    /**
     * 실행 시간 측정해서 로그 남기는 부가 기능
     */
    @Override
    public String operation() {
        log.info("TimeDecorator 실행");

        long startTime = System.currentTimeMillis();
        String result = component.operation(); // 실행
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("TimeDecorator 종료 resultTime => {}ms", resultTime);
        return result;
    }
}
