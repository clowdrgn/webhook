package com.blueware.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author qinheng
 *
 * Date 2015Äê6ÔÂ7ÈÕ
 */
public class DBManager {
	
	private static String driver;
    private static String url;
    private static String username;
    private static String password;
    
    public static void init(String driver1, String url1, String username1,
            String password1) {
        driver = driver1;
        url = url1;
        username = username1;
        password = password1;

    }
    
    public static Connection getConncection() throws SQLException, Exception {
    	 driver = "com.mysql.jdbc.Driver";
//         url = "jdbc:mysql://100.98.70.86:3306/managerdb";
//         username = "yunying";
//         password = "uiRc1Ho2w5i0Ois";
    	url = "jdbc:mysql://localhost:3306/webhook";
    
    	 username = "root";
    	 password = "";
        Class.forName(driver);
        return DriverManager.getConnection(url,username,password);
    }

}
