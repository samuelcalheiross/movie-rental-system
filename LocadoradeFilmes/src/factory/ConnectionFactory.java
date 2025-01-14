/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author samuel
 */
public class ConnectionFactory {
    
    private static final String USERNAME = "root";
    private static final String PASSWORD = "db123";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/locadorabd";
    
    public static Connection createConnection() throws Exception{
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        
        return connection;
    }
    
    public static void main(String[] args) throws Exception {
        Connection con = createConnection();
        
        if(con!=null) {
            System.out.println("Conexão obtida com sucesso!");
            con.close();
        }
    }
}
