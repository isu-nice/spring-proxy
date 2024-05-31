package hello.proxy.config.v4_postprocessor.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/*
 * 적용 결과
 * v1: JDK 동적 프록시 -> 인터페이스가 있으므로 JDK 동적 프록시가 적용됨
 * v2, v3: CGLIB -> 구체 클래스만 있으므로 CGLIB 프록시 적용
 *
 * 중요!!
 * 빈 후처리기 덕분에 v1, v2와 같이 수동으로 등록한 빈 뿐만 아니라
 * 컴포넌트 스캔을 통해 등록한 v3 빈들도 프록시를 적용할 수 있다!!
 */
@Slf4j
public class PackageLogTracePostProcessor implements BeanPostProcessor {

    private final String basePackage;
    private final Advisor advisor;

    public PackageLogTracePostProcessor(String basePackage, Advisor advisor) {
        this.basePackage = basePackage;
        this.advisor = advisor;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("param beanName={} bean={}", beanName, bean.getClass());

        // 프록시 적용 대상 여부 체크
        // 프록시 적용 대상이 아니면 원본을 그대로 진행
        String packageName = bean.getClass().getPackageName();
        if (!packageName.startsWith(basePackage)) {
            return bean;
        }


    }
}
