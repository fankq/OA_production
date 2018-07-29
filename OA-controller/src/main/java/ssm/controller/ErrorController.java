package ssm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import ssm.exception.UserException;

/**
 * Created by fankq on 2018/7/18.
 */
@Controller
@RequestMapping(value="/error")
public class ErrorController {
    @RequestMapping(value="/501")
    public String deal500() throws UserException {
        throw new UserException();
    }

    @RequestMapping(value="/500")
    public String deal501(){
        return "/error/error_500";
    }

    @RequestMapping(value="/404")
    public String deal404(){
        return "/error/404";
    }
}
