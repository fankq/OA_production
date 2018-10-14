package ssm.service.registry.impl;

/*
import com.alibaba.dubbo.config.annotation.Service;
*/
import org.springframework.stereotype.Service;
import ssm.service.registry.TestRegistryService;

/**
 * Created by 18510 on 2018/8/10.
 */

@Service("testRegistryService")
public class TestRegistryServiceImpl implements TestRegistryService {
    @Override
    public String sayHello() {
        return "hello";
    }
}
