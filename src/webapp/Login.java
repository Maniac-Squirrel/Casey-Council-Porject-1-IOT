package webapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		//doGet(request, response);
		
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if(Validate.checkUser(username, password))
        {
        	response.sendRedirect(request.getContextPath() + "/FrameworkServlet");
//            RequestDispatcher rs = request.getRequestDispatcher("FrameworkServlet");
//            rs.forward(request, response);
        }
        else
        {
        	RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
        	rs.include(request, response);
        	out.println("<html><font color=red>Username or Password Incorrect!</font></html>");
        }
		
	}
	

	 
}
