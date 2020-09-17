package webapp;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.io.IOException;
import java.sql.Connection;

public class Validate {
	
	public static boolean checkUser(String username,String password) {
		boolean st =false;
        try {

            //loading drivers for mysql
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //creating connection with the database
            
            Properties properties = new Properties();
            try {
    			properties.load(FrameworkServlet.class.getClassLoader().getResourceAsStream("application.properties"));
    		} catch (IOException e2) {
    			// TODO Auto-generated catch block
    			e2.printStackTrace();
    		}
    		
            Connection connection = null;
            
            try {
    			connection = DriverManager.getConnection(properties.getProperty("url"), properties);
    			
    			

    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}

            PreparedStatement readStatement = connection.prepareStatement("SELECT * FROM Customers WHERE Username=? and Password=?");
    	    readStatement.setString(1, username);
    	    readStatement.setString(2, password);
    	    ResultSet resultSet = readStatement.executeQuery();
    	    st = resultSet.next();
   

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return st;            
	}

}
