package tk.mybatis.springboot.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.mybatis.springboot.model.DeptInf;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by fankq on 2018/5/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/spring-*.xml")
public class DeptInfMapperTest {
    @Autowired
    private DeptInfMapper mapper;
    @Test
    public void test(){
        DeptInf f = new DeptInf();
        f.setName("11111");
        f.setRemark("1111111111");
        mapper.insert(f);
        DeptInf f2 = new DeptInf();
        f2.setName("111112");
        f2.setRemark("1111111112");
        mapper.insert(f);
        List<DeptInf> deptInfs =  mapper.selectAll();

        System.out.println(deptInfs.size());
    }
}