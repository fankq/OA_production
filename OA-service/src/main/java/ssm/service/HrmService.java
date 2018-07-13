package ssm.service;

import ssm.model.auto.*;
import ssm.util.PageModel;

import java.util.List;

/**
 * Created by fankq on 2018/4/22.
 */
public interface HrmService {
    /**
     * 用户登录
     * @param loginname
     *@param password
     */
    UserInfo login(String loginname, String password);

    /**
     * @param id
     */
    UserInfo findUserInfoById(Integer id);

    /**
     * 查找所有用户信息
     */
    List<UserInfo> findAllUser();
    /**
     * 根据条件分页查询用户信息
     */
    List<UserInfo> findUser(UserInfo user, PageModel pageModel);
    /**
     *添加用户
     */
    boolean addUser(UserInfo user);

    /**
     * 修改用户
     */
    boolean modifyUser(UserInfo user);

    /**
     * 删除用户
     */
    void removeUserById(Long id);
    /**
     *获得所有员工
     */
    List<EmployeeInfo> findEmployee(EmployeeInfo employee, PageModel pageModel);

    /**
     * 删除员工
     * @param id
     */
    void removeEmployeeById(Integer id);

    /**
     * 根据id查询员工信息
     */
    EmployeeInfo findEmployeeById(Integer id);

    /**
     * 添加员工
      * @param employeeInfo
     */
    void addEmployee(EmployeeInfo employeeInfo);

    /**
     * 修改员工信息
     * @param info
     */
    void modifyEmployeeInfo(EmployeeInfo info);

    /**
     * 获得所有部门,分页查
     */
    List<DeptInfo> findDept(DeptInfo dept, PageModel pageModel);

    /**
     * 获得所有部门
     */
    List<DeptInfo> findAllDept();
    //删除部门信息
    void removeDeptById(Integer id);
    //添加部门信息
    void addDept(DeptInfo dept);
    //根据部门id查询部门信息
    DeptInfo findDeptById(Integer id);
    //修改部门信息
    void modifyDept(DeptInfo dept);

    //查找所有职位
    List<JobInfo> findAllJob();
    //分页查询
    List<JobInfo> findJob(JobInfo jobInfo, PageModel pageModel);
    //修改职位
    void modifyJob(JobInfo job);
    //添加职位
    void addJob(JobInfo job);
    //根据id查询
    JobInfo findJobById(Integer id);
    //删除职位
    void removeJobById(Integer id);

    //获得所有公告
    List<NoticeInfo> findNotice(NoticeInfo notice, PageModel page);

    //根据id删除公告
    public void removeNoticeById(Integer i);

    //根据id查询公告
    public NoticeInfo findNoticeById(Integer i);

    //添加公告
    public void addNotice(NoticeInfo notice);

    //修改公告
    public void modifyNotice(NoticeInfo noticeInfo);

    //获得所有文档
    List<DocumentInfo> findDocument(DocumentInfo info);

    //添加文档
    void addDocumentInfo(DocumentInfo info);

    //根据id查询文档
    DocumentInfo findDocumentInfoById(Long id);

    //根据id删除文档
    void removeDocumentInfoById(Long id);

    //修改文档
    void modifyDocument(DocumentInfo document);



}
