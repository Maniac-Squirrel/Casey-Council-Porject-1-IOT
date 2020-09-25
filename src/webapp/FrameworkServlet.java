package webapp;


import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrameworkServlet
 */
@WebServlet("/FrameworkServlet")
public class FrameworkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger log = null;
    private Connection connection = null;
    ArrayList<Float> resultsList = new ArrayList<Float>();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrameworkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//PrintWriter out = response.getWriter();
		//out.println("this is a test of my servlet");
		//out.println("Kailen Test");
		
		//connectDB();
		processRequest(request, response);


		
	}
	
	private void connectDB() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		Properties properties = new Properties();
        try {
			properties.load(FrameworkServlet.class.getClassLoader().getResourceAsStream("application.properties"));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
        //Connection connection = null;
        //log.info("Connecting to the database");
        
        try {
			connection = DriverManager.getConnection(properties.getProperty("url"), properties);
			
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

				try {
					//log.info("Database connection test: " + connection.getCatalog());
					System.out.println(readData(connection).toString());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			
			/*
			for (int i = 0; i < customerNames.size(); i++) {
				System.out.println(customerNames.get(i));
			}
			*/
	}
	
	
	private String readData(Connection connection) throws SQLException {
	    //log.info("Read data");
	    PreparedStatement readStatement = connection.prepareStatement("SELECT * FROM ElectricitySensorFile WHERE CustomerID ='cu1';");
	    ResultSet resultSet = readStatement.executeQuery();
	    
	    if (!resultSet.next()) {
	        log.info("There is no data in the database!");
	        return null;
	    } else {
	    		    resultsList.add(resultSet.getFloat("ElectricityUsageKw/h"));
	    while(resultSet.next()) 
	    {
	         resultsList.add(resultSet.getFloat("ElectricityUsageKw/h"));
	         
	    }
	    }
	    

	    
	    //Todo todo = new Todo();
	    //todo.setId(resultSet.getLong("id"));
	    //todo.setDescription(resultSet.getString("description"));
	    //todo.setDetails(resultSet.getString("details"));
	    //todo.setDone(resultSet.getBoolean("done"));
	    //log.info("Data read from the database: " + todo.toString());
	    return resultSet.getString(1);
	}
	
	private ArrayList<DateFloatHolder> avgElecPerDay(Connection connection) throws SQLException
	{
		ArrayList<DateFloatHolder> objectarray = new ArrayList<DateFloatHolder>();
		 PreparedStatement readStatement = connection.prepareStatement("select datename(weekday, convert(datetime, RecordDate, 103)) as Day , avg([ElectricityUsageKw/h]) as avgusage\r\n" + 
		 		"  FROM [dbo].[ElectricitySensorFile]\r\n" + 
		 		"    where CustomerID = 'cu1'\r\n" + 
		 		"  group by datename(weekday, convert(datetime, RecordDate, 103))");
		   ResultSet resultSet = readStatement.executeQuery();
		   DateFloatHolder point = null; 
		   
		if (!resultSet.next()) {
	        log.info("There is no data in the database!");
	        return null;
	    } else {
	    		point = new DateFloatHolder(resultSet.getString(1), resultSet.getFloat(2));
	    		objectarray.add(point);

	    		    
	    while(resultSet.next()) 
	    {
	    	point = new DateFloatHolder(resultSet.getString(1), resultSet.getFloat(2));
    		objectarray.add(point);

		
	    }
		return objectarray;
	   
		
	}
	}
	
	private ArrayList<DateFloatHolder> avgElecPerMonth(Connection connection) throws SQLException
	{
		ArrayList<DateFloatHolder> objectarray = new ArrayList<DateFloatHolder>();
		 PreparedStatement readStatement = connection.prepareStatement("select datename(month, convert(datetime, RecordDate, 103)) as avgusagepermonth , avg([ElectricityUsageKw/h]) as avgusage\r\n" + 
		 		"  FROM [dbo].[ElectricitySensorFile]\r\n" + 
		 		"    where CustomerID = 'cu1'\r\n" + 
		 		"  group by datename(month, convert(datetime, RecordDate, 103))");
		   ResultSet resultSet = readStatement.executeQuery();
		   DateFloatHolder point = null; 
		   
		if (!resultSet.next()) {
	        log.info("There is no data in the database!");
	        return null;
	    } else {
	    		point = new DateFloatHolder(resultSet.getString(1), resultSet.getFloat(2));
	    		objectarray.add(point);

	    		    
	    while(resultSet.next()) 
	    {
	    	point = new DateFloatHolder(resultSet.getString(1), resultSet.getFloat(2));
    		objectarray.add(point);

		
	    }
		return objectarray;
	   
		
	}
	}
	
	private ArrayList<DateFloatHolder> avgGasPerMonth(Connection connection) throws SQLException
	{
		ArrayList<DateFloatHolder> objectarray = new ArrayList<DateFloatHolder>();
		 PreparedStatement readStatement = connection.prepareStatement("select datename(month, convert(datetime, RecordDate, 103)) as avgusagepermonth , avg([GasUsageMJ/H]) as avgusage\r\n" + 
		 		"  FROM [dbo].[GasSensorFile]\r\n" + 
		 		"    where CustomerID = 'cu1'\r\n" + 
		 		"  group by datename(month, convert(datetime, RecordDate, 103))");
		   ResultSet resultSet = readStatement.executeQuery();
		   DateFloatHolder point = null; 
		   
		if (!resultSet.next()) {
	        log.info("There is no data in the database!");
	        return null;
	    } else {
	    		point = new DateFloatHolder(resultSet.getString(1), resultSet.getFloat(2));
	    		objectarray.add(point);

	    		    
	    while(resultSet.next()) 
	    {
	    	point = new DateFloatHolder(resultSet.getString(1), resultSet.getFloat(2));
    		objectarray.add(point);

		
	    }
		return objectarray;
	   
		
	}
	}
	
	private ArrayList<DateFloatHolder> avgGasPerDay(Connection connection) throws SQLException
	{
		ArrayList<DateFloatHolder> objectarray = new ArrayList<DateFloatHolder>();
		 PreparedStatement readStatement = connection.prepareStatement("select datename(weekday, convert(datetime, RecordDate, 103)) as avgusagepermonth , avg([GasUsageMJ/H]) as avgusage\r\n" + 
		 		"  FROM [dbo].[GasSensorFile]\r\n" + 
		 		"    where CustomerID = 'cu1'\r\n" + 
		 		"  group by datename(weekday, convert(datetime, RecordDate, 103))");
		   ResultSet resultSet = readStatement.executeQuery();
		   DateFloatHolder point = null; 
		   
		if (!resultSet.next()) {
	        log.info("There is no data in the database!");
	        return null;
	    } else {
	    		point = new DateFloatHolder(resultSet.getString(1), resultSet.getFloat(2));
	    		objectarray.add(point);

	    		    
	    while(resultSet.next()) 
	    {
	    	point = new DateFloatHolder(resultSet.getString(1), resultSet.getFloat(2));
    		objectarray.add(point);

		
	    }
		return objectarray;
	   
		
	}
	}

	
	private ArrayList<DateFloatHolder> avgWaterPerDay(Connection connection) throws SQLException
	{
		ArrayList<DateFloatHolder> objectarray = new ArrayList<DateFloatHolder>();
		 PreparedStatement readStatement = connection.prepareStatement("select datename(weekday, convert(datetime, RecordDate, 103)) as avgusagepermonth , avg([WaterUsageL/h]) as avgusage\r\n" + 
		 		"  FROM [dbo].[WaterSensorFile]\r\n" + 
		 		"    where CustomerID = 'cu1'\r\n" + 
		 		"  group by datename(weekday, convert(datetime, RecordDate, 103))");
		   ResultSet resultSet = readStatement.executeQuery();
		   DateFloatHolder point = null; 
		   
		if (!resultSet.next()) {
	        log.info("There is no data in the database!");
	        return null;
	    } else {
	    		point = new DateFloatHolder(resultSet.getString(1), resultSet.getFloat(2));
	    		objectarray.add(point);

	    		    
	    while(resultSet.next()) 
	    {
	    	point = new DateFloatHolder(resultSet.getString(1), resultSet.getFloat(2));
    		objectarray.add(point);

		
	    }
		return objectarray;
	   
		
	}
	}
	
	private ArrayList<DateFloatHolder> avgWaterPerMonth(Connection connection) throws SQLException
	{
		ArrayList<DateFloatHolder> objectarray = new ArrayList<DateFloatHolder>();
		 PreparedStatement readStatement = connection.prepareStatement("select datename(month, convert(datetime, RecordDate, 103)) as avgusagepermonth , avg([WaterUsageL/h]) as avgusage\r\n" + 
		 		"  FROM [dbo].[WaterSensorFile]\r\n" + 
		 		"    where CustomerID = 'cu1'\r\n" + 
		 		"  group by datename(month, convert(datetime, RecordDate, 103))");
		   ResultSet resultSet = readStatement.executeQuery();
		   DateFloatHolder point = null; 
		   
		if (!resultSet.next()) {
	        log.info("There is no data in the database!");
	        return null;
	    } else {
	    		point = new DateFloatHolder(resultSet.getString(1), resultSet.getFloat(2));
	    		objectarray.add(point);

	    		    
	    while(resultSet.next()) 
	    {
	    	point = new DateFloatHolder(resultSet.getString(1), resultSet.getFloat(2));
    		objectarray.add(point);

		
	    }
		return objectarray;
	   
		
	}
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
	}
	
	 protected void processRequest(HttpServletRequest request, 
             HttpServletResponse response) 
            		 throws ServletException, IOException

            		 
	    { 
		 connectDB();
	      //System.out.println(customerNames.toString());
	      request.setAttribute("data", resultsList);
	      try {
			System.out.println(avgElecPerDay(connection).get(1).getColumn1());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	      try {
			request.setAttribute("avgperelecday", avgElecPerDay(connection));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	      
	      try {
			request.setAttribute("avgperelecmonth", avgElecPerMonth(connection));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	      
	      try {
			request.setAttribute("avgpergasmonth", avgGasPerMonth(connection));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	      
	      try {
			request.setAttribute("avgpergasday", avgGasPerDay(connection));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	      
	      try {
			request.setAttribute("avgperwaterday", avgWaterPerDay(connection));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	      
	      try {
			request.setAttribute("avgperwatermonth", avgWaterPerMonth(connection));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	      
	      try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      
	      RequestDispatcher rd =  
	              request.getRequestDispatcher("graphTest.jsp"); 
	      
	      rd.forward(request, response);
	      
	    }

}
