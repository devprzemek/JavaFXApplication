package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {
    private static final String url = "jdbc:mysql://localhost:3306/gamesongs";
    private static final String user = "root";
    private static final String password = "";

    private static Connection myConnection;


    public static void connectWithSongDatebas(){
        try {
            myConnection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet executeMyQuery(String sqlQuery){
        ResultSet resultSet = null;
        try {
            Statement myStatement = myConnection.createStatement();
            resultSet = myStatement.executeQuery(sqlQuery);
            while(resultSet.next()){
                System.out.println(resultSet.getString("songName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }
}
