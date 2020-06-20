package wac.jdbc;

import org.junit.Test;
import wac.utils.JDBCUtils;

import java.sql.*;

public class JDBCTest {

    @Test
    public  void SelectallbyUtils(){
        try {
            Connection connection=JDBCUtils.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select *from user ");
            while (resultSet.next()){
                //columnIndex 列索引
                System.out.println(resultSet.getInt(1)+","+resultSet.getString(2)+","+resultSet.getString(3)+","+resultSet.getInt(4));
            }
            JDBCUtils.close1(resultSet,statement,connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insert(String username,String password){
        try {
            Connection connection=JDBCUtils.getConnection();
            String sql="insert into user(username,password) values(?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,password);
            int result=statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(int id){
        try {
            Connection connection=JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from user where id =?");
            preparedStatement.setInt(1,id);
            int result=preparedStatement.executeUpdate();
            if (result>0){
                System.out.println("删除成功");
            }else {
                System.out.println("删除失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(int id,String password){
        try {
            Connection connection=JDBCUtils.getConnection();
            //connection.setAutoCommit(false);//开启事务
            PreparedStatement preparedStatement = connection.prepareStatement("update user set password=? where id=?");
            preparedStatement.setString(1,password);
            preparedStatement.setInt(2,id);
            int result=preparedStatement.executeUpdate();//返回值代表受到影响的行数
            if (result>0){
                System.out.println("修改成功");
            }else {
                System.out.println("修改失败");
            }
            //connection.commit();//提交事务
            //JDBC.SelectallbyUtils();
            JDBCUtils.close2(preparedStatement,connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        insert("泉泉","123456" );
    }
}