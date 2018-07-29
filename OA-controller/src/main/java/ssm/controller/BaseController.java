package ssm.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created by fankq on 2018/7/3.
 */
public abstract class BaseController {
   /* *//**
     * 统一的异常处理逻辑
     * @param request
     * @param e
     * @return
     *//*
    @ExceptionHandler
    public ModelAndView exception(HttpServletRequest request, Exception e) {
       return null;
    }*/
}
