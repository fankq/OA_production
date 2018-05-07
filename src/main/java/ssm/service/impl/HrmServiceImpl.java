package ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssm.dao.auto.DeptInfoMapper;
import ssm.dao.auto.EmployeeInfoMapper;
import ssm.dao.auto.JobInfoMapper;
import ssm.dao.auto.UserInfoMapper;
import ssm.dao.handle.DeptDao;
import ssm.dao.handle.EmployeeInfoDao;
import ssm.dao.handle.JobInfoDao;
import ssm.dao.handle.UserDao;
import ssm.model.auto.*;
import ssm.service.HrmService;
import ssm.utils.PageModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 人事管理系统服务层接口实现类
 * Created by fankq on 2018/4/22.
 */
@Service
public class HrmServiceImpl implements HrmService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private EmployeeInfoMapper employeeInfoDao;

    @Autowired
    private EmployeeInfoDao employeeInfoDaoH;

    @Autowired
    private DeptDao deptInfoDaoH;

    @Autowired
    private DeptInfoMapper deptInfoDao;

    @Autowired
    private JobInfoMapper jobInfoDao;

    private JobInfoDao jobInfoDaoH;
    /**
     * @see {HrmService}
     * @param loginname
     * @param password
     * @return
     */
    @Override
    public UserInfo login(String loginname, String password) {

        UserInfo userInfo = userDao.selectByLoginnameAndPassword(loginname,password);
        return userInfo;

    }

    /**
     * @see {HrmService}
     * @param id
     * @return
     */
    @Override
    public UserInfo findUserInfoById(Integer id) {
        UserInfo userInfo = userDao.getUserById(id);
        return userInfo;
    }

    /**
     * @see {HrmService}
     * @return
     */
    @Override
    public List<UserInfo> findAllUser() {
        Map<String,Object> params = new HashMap<String,Object>();
        List<UserInfo> infos = userDao.selectByPage(params);
        return infos;
    }

    /**
     * @see {HrmService}
     * @param user
     */
    @Override
    public void addUser(UserInfo user) {
        userDao.save(user);
    }

    /**
     * @see {HrmService}
     * @param user
     */
    @Override
    public void modifyUser(UserInfo user) {
        userDao.update(user);
    }

    /**
     *@see {HrmService}
     * @param employee
     * @param pageModel
     * @return
     */
    @Override
    public List<EmployeeInfo> findEmployee(EmployeeInfo employee, PageModel pageModel)
    {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("employeeInfo",employee);
        map.put("pageModel",pageModel);
        List<EmployeeInfo> infos = employeeInfoDaoH.selectByPage(map);
        return infos;
    }

    /**
     * @see {HrmService}
     * @param id
     */
    @Override
    public void removeEmployeeById(Integer id) {
        Long eId = id.longValue();
        employeeInfoDao.deleteByPrimaryKey(eId);
    }

    /**
     * @see {HrmService}
     * @param id
     * @return
     */
    @Override
    public EmployeeInfo findEmployeeById(Integer id) {
        return employeeInfoDao.selectByPrimaryKey(id.longValue());
    }

    /**
     * @see {HrmService}
     * @param employeeInfo
     */
    @Override
    public void addEmployee(EmployeeInfo employeeInfo) {
         employeeInfoDao.insert(employeeInfo);
    }

    /**
     * @see {hrmService}
     * @param info
     */
    @Override
    public void modifyEmployeeInfo(EmployeeInfo info) {
        employeeInfoDao.updateByPrimaryKeySelective(info);
    }

    @Override
    public List<DeptInfo> findDept(DeptInfo dept, PageModel pageModel) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("deptInfo",dept);
        map.put("pageModel",pageModel);
        return deptInfoDaoH.selectByPage(map);
    }

    @Override
    public List<DeptInfo> findAllDept() {
        DeptInfoExample example = new DeptInfoExample();
        return deptInfoDao.selectByExample(example);
    }

    @Override
    public void removeDeptById(Integer id) {
        deptInfoDao.deleteByPrimaryKey(id.longValue());
    }

    @Override
    public void addDept(DeptInfo dept) {
        deptInfoDao.insert(dept);
    }

    @Override
    public DeptInfo findDeptById(Integer id) {
        return deptInfoDao.selectByPrimaryKey(id.longValue());
    }

    /**
     * @see {HrmService}
     * @param dept
     */
    @Override
    public void modifyDept(DeptInfo dept) {
        deptInfoDao.updateByPrimaryKey(dept);
    }

    /**
     * @see {HrmService}
     * @return
     */
    @Override
    public List<JobInfo> findAllJob() {
       return  jobInfoDao.selectByExample(new JobInfoExample());
    }

    /**
     * @see{HrmService}

     * @param jobInfo
     * @param pageModel
     * @return
     */
    @Override
    public List<JobInfo> findJob(JobInfo jobInfo, PageModel pageModel) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("jobInfo",jobInfo);
        map.put("pageModel",pageModel);
        return jobInfoDaoH.selectByPage(map);
    }

    /**
     * @see {HrmService}
     * @param job
     */
    @Override
    public void modifyJob(JobInfo job) {
        jobInfoDao.updateByPrimaryKey(job);
    }

    /**
     * @see {hrmService}
     * @param job
     */
    @Override
    public void addJob(JobInfo job) {
        jobInfoDao.insert(job);
    }

    /**
     * @see {HrmService}
     * @param id
     * @return
     */
    @Override
    public JobInfo findJobById(Integer id) {
        return jobInfoDao.selectByPrimaryKey(id.longValue());
    }

    /**
     * @see{HrmService}
     * @param id
     */
    @Override
    public void removeJobById(Integer id) {
        jobInfoDao.deleteByPrimaryKey(id.longValue());
    }

    /**
     * @see {HrmService}
     * @param notice
     * @param page
     * @return
     */
    @Override
    public List<NoticeInfo> findNotice(NoticeInfo notice, PageModel page) {

        return null;
    }
}
