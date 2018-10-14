package ssm.util;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 18510 on 2018/9/29.
 */
public class MyDateUtil {
    public static String getCurrentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  sdf.format(new Date());
    }
}
