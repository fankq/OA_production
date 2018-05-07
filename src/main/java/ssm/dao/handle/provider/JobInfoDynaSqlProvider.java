package ssm.dao.handle.provider;

import org.apache.ibatis.jdbc.SQL;
import ssm.model.auto.EmployeeInfo;
import ssm.model.auto.JobInfo;
import ssm.utils.StringUtils;

import java.util.Map;

import static ssm.utils.HrmConstants.EMPLOYEETABLE;
import static ssm.utils.HrmConstants.JOBTABLE;

/**
 * Created by 18510 on 2018/5/6.
 */
public class JobInfoDynaSqlProvider {

    //分页查询
    public String selectWithParam(Map<String,Object> params){
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(JOBTABLE);
                if(params.get("jobInfo")!=null){
                    JobInfo jobInfo = (JobInfo)params.get("jobInfo");
                    if(!StringUtils.isNullOrEmpty(jobInfo.getName())){
                        WHERE(" NAME LIKE CONCAT('%',#{jobInfo.name},'%') ");
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
