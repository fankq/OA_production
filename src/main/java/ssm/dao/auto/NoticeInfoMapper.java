package ssm.dao.auto;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import ssm.dao.BaseDao;
import ssm.model.auto.NoticeInfo;
import ssm.model.auto.NoticeInfoExample;

public interface NoticeInfoMapper extends BaseDao {
    int countByExample(NoticeInfoExample example);

    int deleteByExample(NoticeInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(NoticeInfo record);

    int insertSelective(NoticeInfo record);

    List<NoticeInfo> selectByExampleWithBLOBs(NoticeInfoExample example);

    List<NoticeInfo> selectByExample(NoticeInfoExample example);

    NoticeInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") NoticeInfo record, @Param("example") NoticeInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") NoticeInfo record, @Param("example") NoticeInfoExample example);

    int updateByExample(@Param("record") NoticeInfo record, @Param("example") NoticeInfoExample example);

    int updateByPrimaryKeySelective(NoticeInfo record);

    int updateByPrimaryKeyWithBLOBs(NoticeInfo record);

    int updateByPrimaryKey(NoticeInfo record);
}