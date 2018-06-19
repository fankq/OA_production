package ssm.dao.handle;

import org.apache.ibatis.annotations.SelectProvider;
import ssm.annotation.MyBatisDao;
import ssm.dao.handle.provider.DeptDynaSqlProvider;
import ssm.model.auto.DeptInfo;
import java.util.List;
import java.util.Map;

/**
 * Created by 18510 on 2018/5/6.
 */
@MyBatisDao
public interface DeptDao {
    //分页查询信息
    @SelectProvider(type=DeptDynaSqlProvider.class,method="selectWithParam")
    List<DeptInfo> selectByPage(Map<String, Object> params);
}
