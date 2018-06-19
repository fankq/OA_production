package ssm.model.auto;

import java.io.Serializable;
import java.util.Date;

public class NoticeInfo implements Serializable {
    private Long id;

    private String title;

    private Date createDate;

    private Long userId;

    private String context;

    private static final long serialVersionUID = 1L;

    public NoticeInfo(Long id, String title, Date createDate, Long userId, String context) {
        this.id = id;
        this.title = title;
        this.createDate = createDate;
        this.userId = userId;
        this.context = context;
    }

    public NoticeInfo() {
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

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }
}