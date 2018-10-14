package ssm.model.auto;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

public class DocumentInfo implements Serializable {
    private Long id;

    private String title;

    private String filename;

    private String remark;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    private UserInfo user;

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    private MultipartFile file;

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