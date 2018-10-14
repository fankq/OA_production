package ssm.service;

import fr.opensagres.xdocreport.document.json.JSONObject;
import net.sf.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ssm.demo.InnerClass;
import ssm.demo.SuperClass;
import ssm.model.auto.UserInfo;
import ssm.util.SerializeUtil;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

/**
 * Created by fankq on 2018/7/31.
 */

/**
 * Created by fankq on 2018/7/13.
 */
/*@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("src/main/resources")
@ContextConfiguration(locations = {"classpath:spring/spring-*.xml"})*/
public class CacheServiceTest {
    @Autowired
    @Qualifier("cacheService")
    CacheService service;

    @Test
    public void testAfterPropertiesSet() throws Exception {
        service.afterPropertiesSet();
    }
    @Test
    public void testflushAll() throws Exception {
/*
        service.flashCache();
*/

    }
    @Test
    public void testListIterator(){
        List list = new ArrayList();
        int i= 0;
        while(true){
            i++;
            list.add(i);
            if(i>10)
                break;
        }        ListIterator iterator = list.listIterator();

        iterator.add(111);
        iterator.next();
        iterator.previous();
        iterator.previous();
        iterator.set(222);

/*
        iterator.set(2222);
*/
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println(list.get(1));
    }
    @Test
    public void test() throws UnsupportedEncodingException {
        int b = 12345;
        double a =(double) b/1000;
        String str = new String("hhhh ty智障%shfu摸淑芬十分uif内服NSF黑");
        System.out.println(System.getProperty("file.encoding"));//获取系统默认编码格式
        System.out.println("系统默认字符编码：" + Charset.defaultCharset()); //查询结果UTF-8
        System.out.println("系统默认语言：" + System.getProperty("user.language")); //查询结果zh
        System.out.println(new String(str.getBytes("GBK"),"GBK"));//转化字符串编码格式为GBK
        String str1 = "11";
        String str2 ="11";
        System.out.println("11"+(str1==str2));
    }
}