package ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ssm.dao.auto.*;
import ssm.dao.handle.*;
import ssm.model.auto.*;
import ssm.service.HrmService;
import ssm.util.PageModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 人事管理系统服务层接口实现类
 * Created by fankq on 2018/4/22.
 */
@Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.DEFAULT)
@Service("hrmService")
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

    @Autowired
    private JobInfoDao jobInfoDaoH;

    @Autowired
    private NoticeInfoDao noticeInfoDaoH;

    @Autowired
    private NoticeInfoMapper noticeInfoDao;

    @Autowired
    private DocumentInfoMapper documentInfoDao;
    /**
     * @see {HrmService}
     * @param loginname
     * @param password
     * @return
     */
    @Transactional(readOnly =true)
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
    @Transactional(readOnly = true)
    @Override
    public UserInfo findUserInfoById(Integer id) {
        UserInfo userInfo = userDao.getUserById(id);
        return userInfo;
    }

    /**
     * @see {HrmService}
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public List<UserInfo> findAllUser() {
        Map<String,Object> params = new HashMap<String,Object>();
        List<UserInfo> infos = userDao.selectByPage(params);
        return infos;
    }

    /**
     * @see {HrmService}
     * @param user
     * @param pageModel
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public List<UserInfo> findUser(UserInfo user, PageModel pageModel) {
        Map map = new HashMap();
        map.put("userInfo",user);
        map.put("pageModel",pageModel);
        return userDao.selectByPage(map);
    }
    /**
     * @see {HrmService}
     */
    @Override
    public void removeUserById(Integer id){
        userDao.deleteById(id);
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
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
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

    /**
     * @see {HrmService}
     * @param dept
     * @param pageModel
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public List<DeptInfo> findDept(DeptInfo dept, PageModel pageModel) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("deptInfo",dept);
        map.put("pageModel",pageModel);
        return deptInfoDaoH.selectByPage(map);
    }

    /**
     * @see {HrmService}
     * @return
     */
    @Transactional(readOnly = true)
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

    /**
     *      * @see {HrmService}
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    @Override
    public List<NoticeInfo> findNotice(NoticeInfo notice, PageModel page) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("noticeInfo",notice);
        params.put("pageModel",page);
        return noticeInfoDaoH.selectByPage(params);
    }

    @Override
    public void removeNoticeById(Integer i) {
        noticeInfoDao.deleteByPrimaryKey(i.longValue());
    }
    @Transactional(readOnly = true)
    @Override
    public NoticeInfo findNoticeById(Integer i) {
        return noticeInfoDao.selectByPrimaryKey(i.longValue());
    }

    @Override
    public void addNotice(NoticeInfo notice) {
         noticeInfoDao.insert(notice);
    }

    @Override
    public void modifyNotice(NoticeInfo noticeInfo) {
        noticeInfoDao.updateByPrimaryKey(noticeInfo);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DocumentInfo> findDocument(DocumentInfo info) {
        return documentInfoDao.selectByExample(new DocumentInfoExample());
    }

    @Override
    public void addDocumentInfo(DocumentInfo info) {
        documentInfoDao.insert(info);
    }
    @Transactional(readOnly = true)
    @Override
    public DocumentInfo findDocumentInfoById(Integer id) {
        return documentInfoDao.selectByPrimaryKey(id.longValue());
    }

    @Override
    public void removeDocumentInfoById(Integer id) {
        documentInfoDao.deleteByPrimaryKey(id.longValue());
    }

    @Override
    public void modifyDocument(DocumentInfo document) {
        documentInfoDao.updateByPrimaryKey(document);
    }


}
