package ssm.service.impl;

import ssm.model.auto.*;
import ssm.service.HrmService;
import ssm.utils.PageModel;

import java.util.List;

/**
 * 人事管理系统服务层接口实现类
 * Created by fankq on 2018/4/22.
 */
public class HrmServiceImpl implements HrmService {
    @Override
    public UserInfo login(String loginname, String password) {
        return null;
    }

    @Override
    public UserInfo findUserInfoById(Integer id) {
        return null;
    }

    @Override
    public List<UserInfo> findAllUser() {
        return null;
    }

    @Override
    public void addUser(UserInfo user) {

    }

    @Override
    public void modifyUser(UserInfo user) {

    }

    @Override
    public List<EmployeeInfo> findEmployee(EmployeeInfo employee, PageModel pageModel) {
        return null;
    }

    @Override
    public void removeEmployeeById(Integer id) {

    }

    @Override
    public EmployeeInfo findEmployeeById(Integer id) {
        return null;
    }

    @Override
    public void addEmployee(EmployeeInfo employeeInfo) {

    }

    @Override
    public void modifyEmployeeInfo(EmployeeInfo info) {

    }

    @Override
    public List<DeptInfo> findDept(DeptInfo dept, PageModel pageModel) {
        return null;
    }

    @Override
    public List<DeptInfo> findAllDept() {
        return null;
    }

    @Override
    public void removeDeptById(Integer id) {

    }

    @Override
    public void addDept(DeptInfo dept) {

    }

    @Override
    public DeptInfo findDeptById(Integer id) {
        return null;
    }

    @Override
    public void modifyDept(DeptInfo dept) {

    }

    @Override
    public List<JobInfo> findAllJob() {
        return null;
    }

    @Override
    public List<JobInfo> findJob(JobInfo jobInfo, PageModel pageModel) {
        return null;
    }

    @Override
    public void modifyJob(JobInfo job) {

    }

    @Override
    public void addJob(JobInfo job) {

    }

    @Override
    public JobInfo findJobById(Integer id) {
        return null;
    }

    @Override
    public void removeJobById(Integer id) {

    }

    @Override
    public List<NoticeInfo> findNotice(NoticeInfo notice, PageModel page) {
        return null;
    }
}
