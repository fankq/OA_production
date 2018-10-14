package ssm.service;

import net.sf.ehcache.util.NamedThreadFactory;
import net.sf.json.JSONArray;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import ssm.dao.auto.UserInfoMapper;
import ssm.model.auto.UserInfo;
import ssm.model.auto.UserInfoExample;
import ssm.util.SerializeUtil;

import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by fankq on 2018/7/31.
 */
@Service("cacheService")
public class CacheService {
    private static Logger log = Logger.getLogger(CacheService.class);

    @Autowired
    StringRedisTemplate redisCache;
    @Autowired
    RedisTemplate<String,byte[]> redisCache1;

    @Autowired
    UserInfoMapper userDao;

    private ScheduledThreadPoolExecutor executor = null;
    protected static final int CORE_SIZE = Runtime.getRuntime().availableProcessors() * 2;



    @Value("5000")
    private long cacheExpire;

    public void afterPropertiesSet() throws Exception {

        executor = new ScheduledThreadPoolExecutor(CORE_SIZE, new NamedThreadFactory("static-info-loader"));
        RefreshCache refreshCache = new RefreshCache();
        refreshCache.run();
        executor.scheduleWithFixedDelay(refreshCache, cacheExpire, cacheExpire, TimeUnit.SECONDS);

    }

    private class RefreshCache implements Runnable {
        @Override
        public void run() {
            log.info("---开始刷新用户缓存-----");
            UserInfoExample example = new UserInfoExample();
            List<UserInfo> users=  userDao.selectByExample(example);

            if (CollectionUtils.isNotEmpty(users)) {
                for(UserInfo user:users) {
                    try {
                        System.out.println("ceshi:1"+JSONArray.fromObject(SerializeUtil.serialize("ceshi:1")).toString());
                        System.out.println("ceshi:1"+ JSONArray.fromObject(SerializeUtil.serialize("ceshi:"+user.getId())).toString());
                        redisCache1.opsForValue().set("ceshi" + ":" + user.getId(), SerializeUtil.serialize(user));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}
