package ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileNotFoundException;

/**
 * Created by fankq on 2018/7/18.
 */
@Controller
@RequestMapping(value="/test")
public class TestErrorHandlerController {
    @RequestMapping("/500")
    public void test5OO() throws ClassNotFoundException, FileNotFoundException {
        throw new ClassNotFoundException("500错误");
    }
}
