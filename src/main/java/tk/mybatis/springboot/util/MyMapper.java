package tk.mybatis.springboot.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by fankq on 2018/5/7.
 */
public interface MyMapper<T> extends Mapper<T>,MySqlMapper<T> {
}
