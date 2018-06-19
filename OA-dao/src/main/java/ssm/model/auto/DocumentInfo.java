package ssm.model.auto;

import java.io.Serializable;
import java.util.Date;

public class DocumentInfo implements Serializable {
    private Long id;

    private String title;

    private String filename;

    private String remark;

    private Date createDate;

    private Long userId;

    private static final long serialVersionUID = 1L;

    public DocumentInfo(Long id, String title, String filename, String remark, Date createDate, Long userId) {
        this.id = id;
        this.title = title;
        this.filename = filename;
        this.remark = remark;
        this.createDate = createDate;
        this.userId = userId;
    }

    public DocumentInfo() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}