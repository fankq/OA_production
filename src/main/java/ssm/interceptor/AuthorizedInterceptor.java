package ssm.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import ssm.model.auto.UserInfo;
import ssm.utils.HrmConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 判断用户权限的拦截器
 * Created by 18510 on 2018/5/7.
 */
public class AuthorizedInterceptor implements HandlerInterceptor{

    /**
     * 定义不需要拦截的用户请求路径
     */
    private static final String[] IGNOR_URI = {"/loginForm","/404.html"};

    /**
     * 在请求转发controler之前调用，之后继续调用postHandle  以及afterCompletion
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        boolean flag = false;
        String servletPath = httpServletRequest.getServletPath();
        for(String s:IGNOR_URI){
            if(servletPath.contains(s)){
                flag = true;
                break;
            }
        }
        if(!flag){
            UserInfo user = (UserInfo) httpServletRequest.getSession().getAttribute(HrmConstants.USER_SESSION);
            if(user==null){
                httpServletRequest.setAttribute("message","请先登陆网站！");
                httpServletRequest.getRequestDispatcher(HrmConstants.LOGIN).forward(httpServletRequest,httpServletResponse);
                return flag;
            }else{
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
