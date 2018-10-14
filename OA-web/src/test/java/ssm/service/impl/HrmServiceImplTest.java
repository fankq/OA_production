package ssm.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ssm.model.auto.DocumentInfo;
import ssm.service.HrmService;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by fankq on 2018/7/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("src/main/resources")
@ContextConfiguration(locations = {"classpath:spring/spring-*.xml"})
public class HrmServiceImplTest {
    @Autowired
    @Qualifier("hrmService")
    HrmService service;
    @Test
    public void testFindDocumentInfos(){
       DocumentInfo info = new DocumentInfo();
        info.setUserId(11L);
        info.setTitle("文档");

        List<DocumentInfo> infos = service.findDocument(info);
        System.out.println("fankq-------------"+infos.size());
    }
}