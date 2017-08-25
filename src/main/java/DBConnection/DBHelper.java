package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by yujian on 2017/8/12.
 * Description:get DB connection
 */
public class DBHelper {

    private static final String URL = "jdbc:mysql://192.168.164.129:3306/huazi?characterEncoding=utf-8";
    private static final String UNAME = "root";
    private static final String PWD = "root";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    private static Connection conn = null;

    static
    {
        try
        {
            // 1.加载驱动程序
            Class.forName(DRIVER);
            // 2.获得数据库的连接
            conn = DriverManager.getConnection(URL, UNAME, PWD);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static Connection getConnection()
    {
        return conn;
    }

    public static void close(Connection cnn, PreparedStatement preparedStatement) {
        try {
            cnn.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
