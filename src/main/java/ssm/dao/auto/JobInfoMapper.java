package ssm.dao.auto;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import ssm.dao.BaseDao;
import ssm.model.auto.JobInfo;
import ssm.model.auto.JobInfoExample;

public interface JobInfoMapper extends BaseDao {
    int countByExample(JobInfoExample example);

    int deleteByExample(JobInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(JobInfo record);

    int insertSelective(JobInfo record);

    List<JobInfo> selectByExample(JobInfoExample example);

    JobInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") JobInfo record, @Param("example") JobInfoExample example);

    int updateByExample(@Param("record") JobInfo record, @Param("example") JobInfoExample example);

    int updateByPrimaryKeySelective(JobInfo record);

    int updateByPrimaryKey(JobInfo record);
}