package ssm.dao.handle.provider;

import org.apache.ibatis.jdbc.SQL;
import ssm.model.auto.DeptInfo;
import ssm.model.auto.EmployeeInfo;
import ssm.util.StringUtils;

import java.util.Map;

import static ssm.util.HrmConstants.DEPTTABLE;
import static ssm.util.HrmConstants.EMPLOYEETABLE;

/**
 * Created by 18510 on 2018/5/6.
 */
public class DeptDynaSqlProvider {

    //分页查询
    public String selectWithParam(Map<String,Object> params){

        String sql = new SQL() {
            {
                SELECT("*");
                FROM(DEPTTABLE);
                if(params.get("deptInfo")!=null){
                    DeptInfo deptInfo = (DeptInfo)params.get("deptInfo");
                    if(!StringUtils.isNullOrEmpty(deptInfo.getName())){
                        WHERE(" DEPT_ID = #{deptInfo.name} ");
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
