package ssm.service.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by 18510 on 2018/9/30.
 */
@Component
public class AnnotationTask {
    @Scheduled(cron="*/5 * * * * ?")
    public void test(){
        System.out.print("111111111111111111111111111");
    }
}
