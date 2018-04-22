package ssm.dao.auto;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import ssm.dao.BaseDao;
import ssm.model.auto.EmployeeInfo;
import ssm.model.auto.EmployeeInfoExample;

public interface EmployeeInfoMapper extends BaseDao {
    int countByExample(EmployeeInfoExample example);

    int deleteByExample(EmployeeInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EmployeeInfo record);

    int insertSelective(EmployeeInfo record);

    List<EmployeeInfo> selectByExample(EmployeeInfoExample example);

    EmployeeInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EmployeeInfo record, @Param("example") EmployeeInfoExample example);

    int updateByExample(@Param("record") EmployeeInfo record, @Param("example") EmployeeInfoExample example);

    int updateByPrimaryKeySelective(EmployeeInfo record);

    int updateByPrimaryKey(EmployeeInfo record);
}