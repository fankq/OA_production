package ssm.dao.handle.provider;

import org.apache.ibatis.jdbc.SQL;
import ssm.model.auto.NoticeInfo;
import ssm.utils.StringUtils;

import java.util.Map;

import static ssm.utils.HrmConstants.NOTICETABLE;

/**
 * Created by 18510 on 2018/5/6.
 */
public class NoticeInfoDynaSqlProvider {
    //分页查询
    public String selectWithParam(Map<String,Object> params){

        String sql = new SQL() {
            {
                SELECT("*");
                FROM(NOTICETABLE);
                if(params.get("noticeInfo")!=null){
                    NoticeInfo noticeInfo = (NoticeInfo)params.get("noticeInfo");
                    if(!StringUtils.isNullOrEmpty(noticeInfo.getTitle())){
                        WHERE(" TITLE LIKE CONCAT( '%',#{noticeInfo.title},'%') ");
                    }
                    if(!StringUtils.isNullOrEmpty(noticeInfo.getContext())){
                        WHERE(" content LIKE CONCAT( '%',#{noticeInfo.context},'%') ");
                    }
                }
            }
        }.toString();
        if(params.get("pageModel")!=null){
            sql+="limit #{pageModel.firstLimitParam},#{pageModel.pageSize}";
        }
        return  sql;
    }

}
