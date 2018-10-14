package ssm.service.job;

import org.apache.poi.ss.usermodel.DateUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.QuartzJobBean;
import ssm.util.MyDateUtil;

/**
 * Created by 18510 on 2018/9/29.
 */
public class CrontriggerTsak extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(MyDateUtil.getCurrentTime()+"定时任务执行2222222222222222");
    }
}
