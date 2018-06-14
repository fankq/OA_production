package java;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Created by fankq on 2018/4/22.
 * 测试数据库连接是否正常
 */
public class JDBCTest {
    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {
        //加载驱动程序
        String jdbcUrl = "oracle.jdbc.driver.OracleDriver";
        Class.forName(jdbcUrl);
        //获取数据库连接
        Properties properties = new Properties();
        properties = PropertiesLoaderUtils.loadAllProperties("jdbc.properties");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");
        String url = properties.getProperty("jdbc.url");
        Connection con = DriverManager.getConnection(url,username,password);
        String sql = "SELECT 1 from user_inf";
        PreparedStatement ps= con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            System.out.println(rs.getString(1)+"connection is successful");
        }
        con.close();
    }
}
