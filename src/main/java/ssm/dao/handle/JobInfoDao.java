package ssm.dao.handle;

import org.apache.ibatis.annotations.SelectProvider;
import ssm.annotation.MyBatisDao;
import ssm.dao.handle.provider.EmployeeDynaSqlProvider;
import ssm.dao.handle.provider.JobInfoDynaSqlProvider;
import ssm.model.auto.JobInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by 18510 on 2018/5/6.
 */
@MyBatisDao
public interface JobInfoDao {
    @SelectProvider(type=JobInfoDynaSqlProvider.class,method="selectWithParam")
    List<JobInfo> selectByPage(Map<String,Object > params);
}
