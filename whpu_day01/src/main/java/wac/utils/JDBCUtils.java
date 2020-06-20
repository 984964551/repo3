package wac.utils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUtils {
    private static final String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
    private static final String username = "root";
    private static final String password = "root";
    private static List<Connection> connections=new ArrayList<>();
        static{
            for (int i=0;i<5;i++){
                Connection conn=creatConnection();
                connections.add(conn);
            }
        }
    public static Connection getConnection(){
            if (connections.isEmpty()==false){
                Connection conn=connections.get(0);
                connections.remove(conn);
                return conn;
            }else {
                return creatConnection();
            }
    }
    private static Connection creatConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void close1(ResultSet resultSet, Statement statement,Connection connection){
        if (resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close2(Statement statement,Connection connection){
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
    }
}
