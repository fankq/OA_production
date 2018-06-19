package ssm.dao.handle.provider;

import org.apache.ibatis.jdbc.SQL;
import ssm.model.auto.EmployeeInfo;
import ssm.model.auto.UserInfo;
import ssm.util.StringUtils;

import java.util.Map;

import static ssm.util.HrmConstants.EMPLOYEETABLE;
import static ssm.util.HrmConstants.USERTABLE;

/**
 * Created by 18510 on 2018/5/6.
 */
public class EmployeeDynaSqlProvider {

    //分页查询
    public String selectWithParam(Map<String,Object> params){


        String sql = new SQL() {
            {
                SELECT("*");
                FROM(EMPLOYEETABLE);
                if(params.get("employeeInfo")!=null){
                    EmployeeInfo employeeInfo = (EmployeeInfo)params.get("employeeInfo");
                    if(!StringUtils.isNullOrEmpty(employeeInfo.getDeptId())&&0L!=employeeInfo.getDeptId()){
                        WHERE(" DEPT_ID = #{employeeInfo.deptId} ");
                    }
                    if(!StringUtils.isNullOrEmpty(employeeInfo.getJobId())&&0L!=employeeInfo.getJobId()){
                        WHERE(" JOB_ID = #{employeeInfo.jobId} ");
                    }
                    if(!StringUtils.isNullOrEmpty(employeeInfo.getName())){
                        WHERE(" NAME LIKE CONCAT('%',#{employeeInfo.name},'%') ");
                    }
                    if(!StringUtils.isNullOrEmpty(employeeInfo.getPhone())){
                        WHERE(" PHONE LIKE CONCAT('%',#{employeeInfo.phone},'%') ");
                    }
                    if(!StringUtils.isNullOrEmpty(employeeInfo.getCardId())){
                        WHERE(" card_id like CONCAT('%',#{employeeInfo.cardId},'%') ");
                    }
                    if(!StringUtils.isNullOrEmpty(employeeInfo.getSex())){
                        WHERE(" SEX = #{employeeInfo.sex} ");
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
