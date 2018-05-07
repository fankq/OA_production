package ssm.dao.handle;

import org.apache.ibatis.annotations.SelectProvider;
import ssm.annotation.MyBatisDao;
import ssm.dao.handle.provider.NoticeInfoDynaSqlProvider;
import ssm.model.auto.NoticeInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by 18510 on 2018/5/6.
 */
@MyBatisDao
public interface NoticeInfoDao {
    //分页查询信息
    @SelectProvider(type= NoticeInfoDynaSqlProvider.class,method="selectWithParam")
    List<NoticeInfo> selectByPage(Map<String,Object > params);
}
