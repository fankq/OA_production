package ssm.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
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
import ssm.utils.HrmConstants;
import ssm.utils.PageModel;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 18510 on 2018/5/10.
 */
@Controller
public class UserController {
    /**
     * 自动注入服务
     */
    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

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
        ResponseDto dto = new ResponseDto();
        //调用业务逻辑组件判断用户是否可以登录
        UserInfo user = hrmService.login(loginname,password);
        if(user!=null){
            //将用户保存到session
            session.setAttribute(HrmConstants.USER_SESSION,user);
            //客户端跳转到main页面
            dto.setFlag("success");
            dto.setMessage("登陆成功！");
        }else{
            dto.setMessage("登陆名和密码错误！请重新输入");
            dto.setFlag("error");
        }
        return dto;
    }

    /**
     *处理查询用户请求
     * @param pageIndex
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value="/user/selectUser")
    public String selectUser(Integer pageIndex, @ModelAttribute UserInfo user, Model model){
        PageModel pageModel = new PageModel();
        if(pageIndex!=null){
            pageModel.setPageIndex(pageIndex);
        }
        List<UserInfo> userInfos = hrmService.findUser(user,pageModel);
        model.addAttribute("userInfos",userInfos);
        model.addAttribute("pageModel",pageModel);
        return "/user/user";
    }

    /**
     * 处理用户删除请求
     */
    @RequestMapping(value="/user/removeUser")
    public ModelAndView removeUser(String ids ,ModelAndView mv){
        String[] idArray = ids.split(",");
        for(String id : idArray){
            hrmService.removeUserById(Integer.parseInt(id)  );
        }
        //跳转查询请求
        mv.setViewName("redirect:/user/selectUser");
        return mv;
    }

    /**
     *
     * 处理用户修改请求
     * @param flag  1 跳转修改页面   2 执行修改操作
     * @param user
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/user/updateUser")
    public ModelAndView updateUser(
            String flag,
            @ModelAttribute UserInfo user,
            ModelAndView modelAndView
    ){
        if(flag.equals("1")){
            //根据id查询
            UserInfo target = hrmService.findUserInfoById(user.getId().intValue());
            //设置MOdel
            modelAndView.addObject("user",target);
            modelAndView.setViewName("user/showUpdateUser");
        }else{
            hrmService.modifyUser(user);
            modelAndView.setViewName("redirect:/user/selectUser");
        }
        return modelAndView;
    }

    /**
     * 执行添加用户请求
     * @param flag   1 跳转添加用户页面   2 执行添加用户请求操作
     * @param user
     * @param mv
     * @return
     */
    public ModelAndView adduser(String flag, @ModelAttribute UserInfo user, ModelAndView mv){
        if(flag.equals("1")){
            mv.setViewName("user/showAddUser");
        }else{
            hrmService.addUser(user);
            mv.setViewName("redirect:/user/selectUser");
        }
        return mv;
    }


}
