package ssm.dao.handle.provider;

import org.apache.ibatis.jdbc.SQL;
import ssm.model.auto.UserInfo;
import ssm.util.StringUtils;

import static ssm.util.HrmConstants.USERTABLE;
import java.util.Map;

/**
 * Created by fankq on 2018/4/22.
 */
public class UserDynaSqlProvider {
    //分页动态查询
    public String selectWithParam(Map<String,Object> params){
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(USERTABLE);
                if(params.get("userInfo")!=null){
                    UserInfo user = (UserInfo)params.get("userInfo");
                    if(!StringUtils.isNullOrEmpty(user.getUsername())){
                        WHERE(" USERNAME LIKE CONCAT('%',#{userInfo.username},'%') ");
                    }
                    if(!StringUtils.isNullOrEmpty(user.getStatus())){
                        WHERE(" status LIKE CONCAT('%',#{userInfo.status},'%') ");
                    }
                }

            }
        }.toString();
        if(params.get("pageModel")!=null){
            sql+="limit #{pageModel.firstLimitParam},#{pageModel.pageSize}";
        }
        return  sql;
    }

    //动态查询总数
    public String count(Map<String,Object> params){
        String sql = new SQL() {
            {
                SELECT("count(*)");
                FROM(USERTABLE);
                if(params.get("userInfo")!=null){
                    UserInfo user = (UserInfo)params.get("userInfo");
                    if(!StringUtils.isNullOrEmpty(user.getUsername())){
                        WHERE(" USERNAME LIKE CONCAT('%',#{userInfo.username},'%') ");
                    }
                    if(!StringUtils.isNullOrEmpty(user.getStatus())){
                        WHERE(" status LIKE CONCAT('%',#{userInfo.status},'%') ");
                    }
                }

            }
        }.toString();
        return  sql;
    }
//动态插入
    public String insertUser(UserInfo user){
        String sql = new SQL() {
            {
               INSERT_INTO(USERTABLE);
                if(!StringUtils.isNullOrEmpty(user.getUsername())){
                    VALUES("username","#{username}");
                }
                if(!StringUtils.isNullOrEmpty(user.getStatus())){
                    VALUES("status","#{status}");
                }
                if(!StringUtils.isNullOrEmpty(user.getLoginname())){
                    VALUES("loginname","#{loginname}");
                }
                if(!StringUtils.isNullOrEmpty(user.getPassword())){
                    VALUES("password","#{password}");
                }
            }
        }.toString();
        return  sql;
    }
    //动态更新操作
    public String updateUser(UserInfo user){
        return new SQL(){
            {
                UPDATE(USERTABLE);
                if(!StringUtils.isNullOrEmpty(user.getUsername())){
                    SET("username=#{username}");
                }
                if(!StringUtils.isNullOrEmpty(user.getStatus())){
                    SET("status=#{status}");
                }
                if(!StringUtils.isNullOrEmpty(user.getLoginname())){
                    SET("loginname=#{loginname}");
                }
                if(!StringUtils.isNullOrEmpty(user.getPassword())){
                    SET("password=#{password}");
                }
            }
        }.toString();
    }
}