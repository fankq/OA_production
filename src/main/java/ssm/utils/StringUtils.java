package ssm.utils;

/**
 * Created by fankq on 2018/4/22.
 */
public class StringUtils {

    /**
     * 判断对象是否是空或者空空字符
     * @param O
     * @return
     */
    public static boolean isNullOrEmpty(Object O){


        if(null==O){
            return true;
        }else if("".equals(0)){
            return true;
        }else{
            return false;
        }
    }
}
