package DBConnection;

import GetKeyMessage.Traversal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by yujian on 2017/8/12.
 */
public class KeyMessageToDB {

    private Connection cnn;
    private PreparedStatement preparedStatement;

    public boolean toDB(List<List<String>> entrys) {

        boolean flag = true;
        cnn = DBHelper.getConnection();
        String sql = "insert into acc_record" +
                "(time,location,person,pro_dec,reason_dec,so_method,document) values(?,?,?,?,?,?,?)";
        try {
            preparedStatement = cnn.prepareStatement(sql);
            String empryTable = "truncate table acc_record";
            preparedStatement.execute(empryTable);
            for (List<String > entry:entrys){
                for (int i=0;i<entry.size();i++)
                    preparedStatement.setString(i+1,entry.get(i));
                preparedStatement.execute();

            }
            DBHelper.close(cnn,preparedStatement);
        }catch (Exception e){
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

}
