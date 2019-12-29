package sample;

import javafx.scene.control.Alert;

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
    private static Statement myStatement;


    public static void connectWithSongDatebas(){
        try {
            myConnection = DriverManager.getConnection(url, user, password);
        }
        catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Datebase error.");
            alert.setContentText("Impossible to connect with datebase!");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    public static ResultSet executeMyQuery(String sqlQuery){
        ResultSet resultSet = null;
        try {
            myStatement = myConnection.createStatement();
            resultSet = myStatement.executeQuery(sqlQuery);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public static void closeConnectionWithSongDatabase(){
        try {
            myStatement.close();
            myConnection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
