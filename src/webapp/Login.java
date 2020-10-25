package webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    public boolean error = false;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println("You have logged in with username: "+ request.getParameter("username")+" and password: "+request.getParameter("password"));
		if ((request.getParameter("username").equalsIgnoreCase("abc") && (request.getParameter("password").equals("123")))) {
			out.print("Success");
		} else {
			out.print("Fail");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        String UserID = Validate.getUserID(username);
        
        if(Validate.checkUser(username, password))
        {
        	response.sendRedirect(request.getContextPath() + "/home?id="+EncryptionUtil.encode(UserID));
        }
        else
        {
        	RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
        	rs.include(request, response);
        	out.println("<html><font font-size=38px color=red style=\" margin-left: 38%; font-size: 38px; -webkit-text-stroke: 1px white; font-weight: bold;\">Incorrect username or password!</font></html>");
        }
		
	}
	

	 
}
