package ssm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by fankq on 2018/7/18.
 */
@ResponseStatus(reason = "测试",value = HttpStatus.INTERNAL_SERVER_ERROR)
public class UserException extends Exception {
}
