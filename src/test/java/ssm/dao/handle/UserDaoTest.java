package ssm.dao.handle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ssm.dao.auto.UserInfoMapper;
import ssm.model.auto.UserInfo;
import ssm.model.auto.UserInfoExample;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by fankq on 2018/4/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/spring-*.xml")
public class UserDaoTest {
@Autowired
private UserDao dao;
    @Autowired
    private UserInfoMapper mapper;
    @Test
    public void testGetUserByid()
    {
        UserInfo user1 = new UserInfo();
        user1.setLoginname("fankq1");
        user1.setPassword("111111");
        Short status = 1;
        user1.setStatus(status);
        user1.setCreatedate(new Date());
        user1.setUsername("fankq1");
/*
        int result= mapper.insert(user1);
*/
/*
        System.out.println(result);
*/
        UserInfoExample example = new UserInfoExample();
        List<UserInfo> i= mapper.selectByExample(example);
        System.out.println(i.size());

    }
}