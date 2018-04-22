package ssm.dao.handle;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import ssm.annotation.MyBatisDao;
import ssm.dao.handle.provider.UserDynaSqlProvider;
import ssm.model.auto.UserInfo;

import java.util.List;
import java.util.Map;

import static ssm.utils.HrmConstants.USERTABLE;
/**
 * Created by fankq on 2018/4/22.
 */
@MyBatisDao
public interface UserDao {
    //根据登录名和密码查询员工
    @Select("SELECT * FROM " +USERTABLE +" WHERE LOGINNAME=#{loginname}" +
            "AND PASSWORD = #{password}")
    UserInfo selectByLoginnameAndPassword(@Param("loginname") String loginname,@Param("password")String password);
    //根据id查询员工信息
    @Select("select * from "+USERTABLE+" where id = #{id}")
    UserInfo getUserById(int id );
    //根据ID删除用户
    @Delete("delete from "+USERTABLE+"WHERE ID = #{id}")
    void deleteById(Integer id);
    //动态修改用户
    @SelectProvider(type=UserDynaSqlProvider.class,method = "updateUser")
    void update(UserInfo user);
    //动态插入用户
    @SelectProvider(type=UserDynaSqlProvider.class,method="insertUser")
    void save(UserInfo user);
    //根据参数查询用户总数
    @SelectProvider(type=UserDynaSqlProvider.class,method="cont")
    Integer count(Map<String,Object > params);
    //动态查询
    @SelectProvider(type=UserDynaSqlProvider.class,method="selectWithParam")
    List<UserInfo> selectByPage(Map<String,Object > params);
}
