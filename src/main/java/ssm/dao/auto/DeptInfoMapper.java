package ssm.dao.auto;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import ssm.dao.BaseDao;
import ssm.model.auto.DeptInfo;
import ssm.model.auto.DeptInfoExample;

public interface DeptInfoMapper extends BaseDao {
    int countByExample(DeptInfoExample example);

    int deleteByExample(DeptInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DeptInfo record);

    int insertSelective(DeptInfo record);

    List<DeptInfo> selectByExample(DeptInfoExample example);

    DeptInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DeptInfo record, @Param("example") DeptInfoExample example);

    int updateByExample(@Param("record") DeptInfo record, @Param("example") DeptInfoExample example);

    int updateByPrimaryKeySelective(DeptInfo record);

    int updateByPrimaryKey(DeptInfo record);
}