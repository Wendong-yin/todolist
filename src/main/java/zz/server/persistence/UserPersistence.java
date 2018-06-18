package zz.server.persistence;

import zz.common.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserPersistence {
    public static User fromResult(ResultSet resultSet){
        User user = new User();
        try{
            // ❤️ 我们要从 query 返回的字符串 不断提取
            user.setId(resultSet.getLong("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    public User getByUsername (User user){

        //String query = "SELECT * FROM \"user\" where username = '" + user.getUsername() +"';";

        // ❤️ 这样写搜索更加优雅
        String query = "SELECT * FROM \"user\" where username = '%s';";
        query = String.format(query,user.getUsername());

        Connection connection =  Database.newConnection();
        try {
            Statement statement = connection.createStatement();
            // ❤️ sql 返回的结果是一个 result 对象
            ResultSet resultSet = statement.executeQuery(query);

            if(!resultSet.next()){
                return null;
            }
            return UserPersistence.fromResult(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User create (User user){
        // ❤️ 这样写搜索更加优雅
        String query = "INSERT INTO \"user\" VALUES (DEFAULT ,'%s','%s') RETURNING  *;\n";
        query = String.format(query,user.getUsername(),user.getPassword());

        Connection connection =  Database.newConnection();
        try {
            Statement statement = connection.createStatement();
            // ❤️ sql 返回的结果是一个 result 对象
            ResultSet resultSet = statement.executeQuery(query);

            if(!resultSet.next()){
                return null;
            }
            return UserPersistence.fromResult(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        UserPersistence userPersistence = new UserPersistence();
        User user = new User();
        user.setUsername("wendong");
        user = userPersistence.getByUsername(user);
        System.out.println(user);

        User user1 = new User();
        user1.setUsername("cai22");
        user1.setPassword("123556");
        User user2 = userPersistence.create(user1);
        System.out.println(user2);
    }

}
