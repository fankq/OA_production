package ssm.model;

import java.io.Serializable;

/**
 * Created by 18510 on 2018/5/20.
 */
public class ResponseDto implements Serializable {
    private String flag;
    private String message;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
