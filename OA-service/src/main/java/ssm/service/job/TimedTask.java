package ssm.service.job;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 18510 on 2018/9/29.
 */
public class TimedTask {
    private String getCurrentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  sdf.format(new Date());
    }
    public void execute1() {

        System.out.println(getCurrentTime()+"定时任务执行1111111111111");
    }
    public void execute2() {
        System.out.println(getCurrentTime()+"定时任务执行22222222222222");

    }
}
