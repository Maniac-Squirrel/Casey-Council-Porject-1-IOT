package webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserPrefs
 */
@WebServlet("/UserPrefs")
public class UserPrefs extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String UserID, Username, Password;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserPrefs() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserID = EncryptionUtil.decode(request.getParameter("id"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		RequestDispatcher rs = request.getRequestDispatcher("Profile.jsp");
    	rs.include(request, response);
		if (request.getParameter("Update") == null && request.getParameter("password") == null && request.getParameter("username") == null) {
		} else if (request.getParameter("Update").equalsIgnoreCase("true")) {
			Username = request.getParameter("Username");
			Password = request.getParameter("Password");
			updateData(request, response);
		}
	}
	
	private void updateData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		if (Username.equals("") || Password.equals("")) {
			out.println("<html><font color=red>Please ensure both fields are filled in!</font></html>");
		} else{
			
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
			            
			            PreparedStatement readStatement = connection.prepareStatement("SELECT Username FROM Customers WHERE Username=?;");
			            readStatement.setString(1, Username);
			            ResultSet rs = readStatement.executeQuery();
			            if (rs.next()) {
			            	out.println("<html><font color=red>Username already taken!</font></html>");
			            } else {
				            PreparedStatement updateStatement = connection.prepareStatement("UPDATE Customers SET Username=?, Password=? WHERE CustomerID=?;");
				            updateStatement.setString(1, Username);
				            updateStatement.setString(2, Password);
				    	    updateStatement.setString(3, UserID);
				    	    updateStatement.executeUpdate();
			            	out.println("<html><font color=green>Username and Password updated successfully!</font></html>");
			            }
			    	        	    
			        }
			        catch(Exception e) {
			            e.printStackTrace();
			        }
		}
		
	}

}
