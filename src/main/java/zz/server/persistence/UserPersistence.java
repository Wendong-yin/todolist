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
            user.setPassword(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    public User getByUsername (User user){

        String query = "SELECT * FROM \"user\" where username = '" + user.getUsername() +"';";

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
    }

}
