package ssm.controller;

import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.JsonViewResponseBodyAdvice;
import ssm.model.ResponseDto;
import ssm.model.auto.DocumentInfo;
import ssm.model.auto.UserInfo;
import ssm.service.HrmService;
import ssm.util.HrmConstants;
import ssm.util.PageModel;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fankq on 2018/7/12.
 */
@Controller
@RequestMapping(value="/document")
public class DocumentController {


    /**
     *
     */
    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    /**
     *处理用户上传文件请求
     * @return
     */
    @RequestMapping(value="/upload")
    @ResponseBody
    public String  upload(DocumentInfo documentInfo,HttpSession session) throws IOException {
        //获取当前用户信息
        UserInfo user = (UserInfo) session.getAttribute(HrmConstants.USER_SESSION);
        //上传文件信息收集
        String fileName = documentInfo.getFile().getOriginalFilename();
        //上传文件保存路径
        String path = session.getServletContext().getRealPath("/WEB-INF/statics/upload/");
        File file = new File(path+File.pathSeparator+fileName);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdir();
        }
        documentInfo.setFilename(fileName);
        documentInfo.setUserId(user.getId());
        documentInfo.getFile().transferTo(file);
        documentInfo.setCreateDate(new Date());
        hrmService.addDocumentInfo(documentInfo);
        Map<String,Object> dto = new HashMap<String,Object>();
        dto.put("flag","success");
        dto.put("documentInfo",documentInfo);
        /*JSONObject json = JSONObject.fromObject(obj);
        return dto;*/
        return "";
    }
    /**
     *处理用户删除文件请求
     * @return
     */
    @RequestMapping(value="/delete")
    @ResponseBody
    public String  delete(DocumentInfo documentInfo,HttpSession session) throws IOException {


        return null;
    }
}
