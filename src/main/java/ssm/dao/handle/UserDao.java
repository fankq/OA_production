package ssm.dao.handle;

import org.apache.ibatis.annotations.Select;
import ssm.annotation.MyBatisDao;
import ssm.model.auto.UserInfo;

/**
 * Created by fankq on 2018/4/22.
 */
@MyBatisDao
public interface UserDao {
    @Select("select * from user_inf where id = #{id}")
    public UserInfo getUserById(int id );
}
