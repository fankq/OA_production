package ssm.converter;

import org.apache.log4j.Logger;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fankq on 2018/7/6.
 */
public class StringToDateConverter implements Converter<String,Date> {
    private static Logger logger = Logger.getLogger(StringToDateConverter.class);

    @Override
    public Date convert(String source) {
        Date date=null;
        String pattern = "";
        if(source.matches("^[0-9]*$")) {
            Long time = Long.parseLong(source);
             date = new Date(time);
        }else if(source.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}$")){
            pattern = "yyyy-MM-dd HH:mm:ss";
        }else if(source.matches("^[0-9]{4}/[0-9]{2}/[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}$")){
            pattern = "yyyy/MM/dd HH:mm:ss";

        } else if(source.matches("^[0-9]{4}/[0-9]{2}/[0-9]{2}")){
            pattern = "yyyy/MM/dd";

        }else if(source.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}")){
            pattern = "yyyy-MM-dd";
        }else{
            pattern = "";
        }
        if(pattern!=""){
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            try {
                date = sdf.parse(source);
            } catch (ParseException e) {
                e.printStackTrace();
                logger.error(source+"转换为日期类型错误");
                return null;
            }
        }else {
            if(date==null) {
                logger.error(source + "转换为日期类型不符合常用格式");
                return null;
            }
        }
        return date;
    }
}
