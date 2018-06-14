package ssm.dao.handle;

import org.apache.ibatis.annotations.SelectProvider;
import ssm.annotation.MyBatisDao;
import ssm.dao.handle.provider.EmployeeDynaSqlProvider;
import ssm.dao.handle.provider.UserDynaSqlProvider;
import ssm.model.auto.EmployeeInfo;
import ssm.model.auto.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by 18510 on 2018/5/6.
 */
@MyBatisDao
public interface EmployeeInfoDao {
    //分页查询职工信息
    @SelectProvider(type=EmployeeDynaSqlProvider.class,method="selectWithParam")
    List<EmployeeInfo> selectByPage(Map<String, Object> params);
}
