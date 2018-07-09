package ssm.controller;

import fr.opensagres.xdocreport.document.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ssm.model.ResponseDto;
import ssm.model.auto.UserInfo;
import ssm.service.HrmService;
import ssm.util.HrmConstants;
import ssm.util.PageModel;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 18510 on 2018/5/10.
 */
@Controller
public class UserController {
    private static Logger logger = Logger.getLogger(UserController.class);
    /**
     * 自动注入服务
     */
    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;


    @RequestMapping(value="/default")
    public String  defaultTemplate(){
        return "loginForm";
    }


    @RequestMapping(value="/loginForm")
    public String  loginForm(){
        return "loginForm";
    }


    @RequestMapping(value="/admin")
    public String  admin(){
        return "admin";
    }
    /**
     *处理登陆请求
     * @param loginname  登陆名
     * @param password    密码
     * @param session
     * @return  跳转的视图
     */
    @RequestMapping(value="/login")
    @ResponseBody
    public ResponseDto login(String loginname, String password , HttpSession session){
        logger.info(loginname+"用户尝试登录！");
        ResponseDto dto = new ResponseDto();
        //调用业务逻辑组件判断用户是否可以登录
        UserInfo user = hrmService.login(loginname,password);
        if(user!=null){
            //将用户保存到session
            session.setAttribute(HrmConstants.USER_SESSION,user);
            //客户端跳转到main页面
            dto.setFlag("success");
            dto.setMessage("登陆成功！");
            logger.info(loginname+"用户登录成功！");
        }else{
            dto.setMessage("登陆名和密码错误！请重新输入");
            dto.setFlag("error");
        }
        return dto;
    }
    /**
     * 执行添加用户请求
     * @param user
     * @return
     */
    @RequestMapping(value = "/user/adduser")
    @ResponseBody
    public ResponseDto adduser(String flag, @ModelAttribute UserInfo user){
        ResponseDto dto = new ResponseDto();
        dto.setFlag("false");
        boolean result;
        try {
            result= hrmService.addUser(user);
        }catch(Exception e ){
            dto.setMessage(e.getMessage());
            return dto ;
        }
        dto.setFlag(""+result);
        return dto;
    }
    /**
     *处理查询用户请求
     * @param pageIndex
     * @param user
     * @return
     */
    @RequestMapping(value="/user/selectUser")
    @ResponseBody
    public Map selectUser(Integer pageIndex, @ModelAttribute UserInfo user){
        PageModel pageModel = new PageModel();
        if(pageIndex!=null){
            pageModel.setPageIndex(pageIndex);
        }else{
            pageModel.setPageIndex(1);
        }
        List<UserInfo> userInfos = hrmService.findUser(user,pageModel);
        Map map = new HashMap();
        map.put("userInfos",userInfos);
        map.put("pageModel",pageModel);

        return map;
    }
    /**
     *
     * 处理用户修改请求
     * @param flag  1 跳转修改页面   2 执行修改操作
     * @param user
     * @return
     */
    @RequestMapping(value = "/user/updateUser")
    @ResponseBody
    public ResponseDto updateUser(String flag,  UserInfo user){
        ResponseDto dto = new ResponseDto();
        dto.setFlag("false");

        try {
             hrmService.modifyUser(user);
             dto.setFlag("true");
        }catch(Exception e ){
            dto.setMessage(e.getMessage());
            return dto ;
        }

        return dto;
    }

    /**
     * 处理用户删除请求
     */
    @RequestMapping(value="/user/removeUser")
    @ResponseBody
    public ResponseDto removeUser(UserInfo user){
        ResponseDto dto = new ResponseDto();
        dto.setFlag("false");
        try {
            hrmService.removeUserById(user.getId()  );
            dto.setFlag("true");
        }catch(Exception e ){
            dto.setMessage(e.getMessage());
            return dto ;
        }
        return dto;
    }





}
