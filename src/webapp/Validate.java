package webapp;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
    		
            Connection connection = null;
            
            try {
    			connection = DriverManager.getConnection("jdbc:sqlserver://iotdbserver01.database.windows.net:1433;database=IOTData;user=sysAdmin@iotdbserver01;password=fhxghjk,157.;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
    			
    			

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
	
	public static String getUserID (String username) {
		String UserID = null;
		try {

            //loading drivers for mysql
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		
            Connection connection = null;
            
            try {
    			connection = DriverManager.getConnection("jdbc:sqlserver://iotdbserver01.database.windows.net:1433;database=IOTData;user=sysAdmin@iotdbserver01;password=fhxghjk,157.;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
    			
    			

    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}

            PreparedStatement readStatement = connection.prepareStatement("SELECT CustomerID FROM Customers WHERE Username=?;");
    	    readStatement.setString(1, username);
    	    ResultSet UserData = readStatement.executeQuery();
    	    while (UserData.next()) {
    	    	UserID = UserData.getString("CustomerID");	
    	    }
    	    
    	        	    
        }
        catch(Exception e) {
            e.printStackTrace();
        }
		return UserID;
	}

}
