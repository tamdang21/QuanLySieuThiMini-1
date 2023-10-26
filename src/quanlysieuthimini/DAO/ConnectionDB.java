/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ConnectionDB {
    
    private static String DB_Name="quanlysieuthimini";
    private static String USERNAME = "root";
    private static String PASSWORD = "";
    private static String URL = "";

    private static String ipAddress = "localhost:3306";

    // Kết nối tới DB
    public static Connection openConnection() {
        checkDriver();
        Connection connect = null;
        try {
            URL = "jdbc:mysql://" + ipAddress + "/" + DB_Name + "?useUnicode=true&characterEncoding=UTF-8";
            connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Success! Da ket noi toi '" + DB_Name + "'");

        } catch (SQLException e) {
            System.err.println("-- ERROR! Khong the ket noi toi '" + DB_Name + "'");
            JOptionPane.showMessageDialog(null, "-- ERROR! Khong the ket noi toi '" + DB_Name + "'");
        }
        
        return connect;
    }

    // đăng nhập
    public void logIn(String user_Name, String pass) {
        this.USERNAME = user_Name;
        this.PASSWORD = pass;
        openConnection();
    }

    // đóng connection
    public static void closeConnection(Connection connect) {
        try {
            if (connect != null) {
                connect.close();
            }
            System.out.println("Success! Dong ket noi toi '" + DB_Name + "' thang cong.\n**");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Khong the dong ket noi toi " + DB_Name + "\n" + ex.getLocalizedMessage());
        }
    }

    // check driver
    private static void checkDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Không tìm thấy Driver mySql");
        }
    }

}
