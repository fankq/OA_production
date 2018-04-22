package ssm.dao.auto;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import ssm.dao.BaseDao;
import ssm.model.auto.DocumentInfo;
import ssm.model.auto.DocumentInfoExample;

public interface DocumentInfoMapper extends BaseDao {
    int countByExample(DocumentInfoExample example);

    int deleteByExample(DocumentInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DocumentInfo record);

    int insertSelective(DocumentInfo record);

    List<DocumentInfo> selectByExample(DocumentInfoExample example);

    DocumentInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DocumentInfo record, @Param("example") DocumentInfoExample example);

    int updateByExample(@Param("record") DocumentInfo record, @Param("example") DocumentInfoExample example);

    int updateByPrimaryKeySelective(DocumentInfo record);

    int updateByPrimaryKey(DocumentInfo record);
}