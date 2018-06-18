package zz.server.persistence;

import java.sql.*;

public class Database {
    public static Connection newConnection(){
        String host = "zzdb.ce71fiwyeo85.us-east-2.rds.amazonaws.com";
        String databaseName = "zzdb";
        String url = "jdbc:postgresql://" + host + "/" + databaseName;
        String username = "wendong";
        String password = "Pingguo890";

        try{
            return DriverManager.getConnection(url,username,password);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    public static void main(String[] args) {
        Connection connection = newConnection();
        String query = "SELECT * FROM \"user\";";

        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                System.out.println(resultSet.getString("username") + ": " +
                        resultSet.getString("password"));
            }

            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }

    }
}
