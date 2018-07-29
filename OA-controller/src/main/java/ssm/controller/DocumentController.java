package ssm.controller;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fankq on 2018/7/12.
 */
@Controller
@RequestMapping(value="/document")
public class DocumentController extends BaseController {


    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    /**
     * 处理用户下载文件请求
     */
    @RequestMapping(value="/download")
    public  ResponseEntity<byte[]> download(DocumentInfo documentInfo,HttpSession session) throws IOException {
        DocumentInfo info = hrmService.findDocumentInfoById(documentInfo.getId());
        String fileName = info.getFilename();
        String path = session.getServletContext().getRealPath("/WEB-INF/statics/upload/");
        File file = new File(path+File.separator+fileName);
        HttpHeaders headers = new HttpHeaders();
        String downloadFileName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
        headers.setContentDispositionFormData("attachment",downloadFileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
    }    /**
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
        File file = new File(path+File.separator+fileName);
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
        dto.put("docId",documentInfo.getId());
        JSONObject json = JSONObject.fromObject(dto);
        return json.toString();
    }
    /**
     *处理用户上传文件请求
     * @return
     */
    @RequestMapping(value="/query")
    @ResponseBody
    public Map<String,Object>  query(DocumentInfo documentInfo,HttpSession session) throws IOException {
        //获取当前用户信息
        UserInfo user = (UserInfo) session.getAttribute(HrmConstants.USER_SESSION);
        documentInfo.setUserId(user.getId());
        List<DocumentInfo> documentInfos = hrmService.findDocument(documentInfo);


        Map<String,Object> dto = new HashMap<String,Object>();
        dto.put("flag","success");
        dto.put("documentInfos",documentInfos);


        return dto;
    }

    /**
     *处理用户删除文件请求
     * @return
     */
    @RequestMapping(value="/delete")
    @ResponseBody
    public String  delete(DocumentInfo documentInfo,HttpSession session) throws IOException {
        Map<String,Object> dto = new HashMap<String,Object>();

        DocumentInfo info = hrmService.findDocumentInfoById(documentInfo.getId());
        UserInfo user = (UserInfo) session.getAttribute(HrmConstants.USER_SESSION);

        if(info.getUserId()==user.getId()){
            hrmService.removeDocumentInfoById(documentInfo.getId());
            dto.put("flag","success");
            dto.put("message","删除成功");
        }else{
            dto.put("flag","false");
            dto.put("message","您无权操作此文件！");
        }

        return JSONObject.fromObject(dto).toString();
    }



}
