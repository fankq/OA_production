package ssm.model.auto;

import java.io.Serializable;

public class JobInfo implements Serializable {
    private Long id;

    private String name;

    private String remark;

    private static final long serialVersionUID = 1L;

    public JobInfo(Long id, String name, String remark) {
        this.id = id;
        this.name = name;
        this.remark = remark;
    }

    public JobInfo() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}