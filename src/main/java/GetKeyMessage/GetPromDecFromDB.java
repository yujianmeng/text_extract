package GetKeyMessage;

import DBConnection.DBHelper;
import org.apache.hadoop.mapred.FileOutputCommitter;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 * Created by yujian on 2017/8/15.
 */
public class GetPromDecFromDB {

    public void getMessageFromDB(){
        Connection connection = DBHelper.getConnection();
        String sql = "select pro_dec from acc_record";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            StringBuilder promDEC = new StringBuilder();
            while (resultSet.next()){
                promDEC.append(resultSet.getString("pro_dec")+"\r");
            }
            FileOutputStream fos = new FileOutputStream("E:\\程序分类\\ProblemDec.txt");
            fos.write(promDEC.toString().getBytes("utf-8"));
            fos.flush();
            fos.close();
            connection.close();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }



    }
}
