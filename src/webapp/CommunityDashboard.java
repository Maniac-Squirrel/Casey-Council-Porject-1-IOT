package webapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 * Servlet implementation class CommunityDashboard
 */
@WebServlet("/CommunityDashboard")
public class CommunityDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection connection = null;
    private static final Logger log = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityDashboard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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

				//try {
					//log.info("Database connection test: " + connection.getCatalog());
					//System.out.println(readData(connection).toString());
				//} catch (SQLException e1) {
					// TODO Auto-generated catch block
				//	e1.printStackTrace();
				//}	
			
			/*
			for (int i = 0; i < customerNames.size(); i++) {
				System.out.println(customerNames.get(i));
			}
			*/
	}
	
	private ArrayList<Float> communityElecConsumption(Connection connection) throws SQLException
	{
		ArrayList<Float> objectarray = new ArrayList<Float>();
		 PreparedStatement readStatement = connection.prepareStatement("SELECT sum([ElectricityUsageKw/h])\r\n" + 
		 		"  FROM [dbo].[ElectricitySensorFile]\r\n" + 
		 		"  where CustomerID = 'cu1'");
		   ResultSet resultSet = readStatement.executeQuery();
		   
		   PreparedStatement readStatment2 = connection.prepareStatement("SELECT sum([ElectricityUsageKw/h])\r\n" + 
	    			"  FROM [dbo].[ElectricitySensorFile]");
		   
		   ResultSet resultSet2 = readStatment2.executeQuery();
		   
		   //DateFloatHolder point = null; 
		   
		if (!resultSet.next() || !resultSet2.next()) {
	        log.info("There is no data in the database!");
	        return null;
	    } else {
	    	objectarray.add(resultSet.getFloat(1));
	    	//System.out.print(objectarray.get(0));
	    	System.out.println("querey 1 " + resultSet.getFloat(1));
	    	
	    	//resultSet2= readStatement2.executeQuery();
	    	objectarray.add(resultSet2.getFloat(1));
	    	//System.out.print(objectarray.get(1));
	    	System.out.println("query 2 " + resultSet2.getFloat(1));
		
	   
		
	}
		return objectarray;
	}
	
	private ArrayList<Integer> communityWaterConsumption(Connection connection) throws SQLException
	{
		ArrayList<Integer> objectarray = new ArrayList<Integer>();
		 PreparedStatement readStatement = connection.prepareStatement("SELECT sum([WaterUsageL/h])\r\n" + 
		 		"  FROM [dbo].[WaterSensorFile]\r\n" + 
		 		"  Where CustomerID = 'cu1'");
		   ResultSet resultSet = readStatement.executeQuery();
		   
		   PreparedStatement readStatment2 = connection.prepareStatement("SELECT sum([WaterUsageL/h])\r\n" + 
		   		"  FROM [dbo].[WaterSensorFile]");
		   
		   ResultSet resultSet2 = readStatment2.executeQuery();
		   
		   //DateFloatHolder point = null; 
		   
		if (!resultSet.next() || !resultSet2.next()) {
	        log.info("There is no data in the database!");
	        return null;
	    } else {
	    	objectarray.add(resultSet.getInt(1));
	    	//System.out.print(objectarray.get(0));
	    	System.out.println("querey 1 " + resultSet.getInt(1));
	    	
	    	//resultSet2= readStatement2.executeQuery();
	    	objectarray.add(resultSet2.getInt(1));
	    	//System.out.print(objectarray.get(1));
	    	System.out.println("query 2 " + resultSet2.getInt(1));
		
	   
		
	}
		return objectarray;
	}
	
	private ArrayList<Float> communityGasConsumption(Connection connection) throws SQLException
	{
		ArrayList<Float> objectarray = new ArrayList<Float>();
		 PreparedStatement readStatement = connection.prepareStatement("SELECT sum([GasUsageMJ/H])\r\n" + 
		 		"  FROM [dbo].[GasSensorFile]\r\n" + 
		 		"  Where CustomerID = 'cu1'");
		   ResultSet resultSet = readStatement.executeQuery();
		   
		   PreparedStatement readStatment2 = connection.prepareStatement("SELECT sum([GasUsageMJ/H])\r\n" + 
		   		"  FROM [dbo].[GasSensorFile]");
		   
		   ResultSet resultSet2 = readStatment2.executeQuery();
		   
		   //DateFloatHolder point = null; 
		   
		if (!resultSet.next() || !resultSet2.next()) {
	        log.info("There is no data in the database!");
	        return null;
	    } else {
	    	objectarray.add(resultSet.getFloat(1));
	    	//System.out.print(objectarray.get(0));
	    	System.out.println("querey 1 " + resultSet.getFloat(1));
	    	
	    	//resultSet2= readStatement2.executeQuery();
	    	objectarray.add(resultSet2.getFloat(1));
	    	//System.out.print(objectarray.get(1));
	    	System.out.println("query 2 " + resultSet2.getFloat(1));
		
	   
		
	}
		return objectarray;
	}
	
	private ArrayList<DateFloatHolder> communitywaterlast7days (Connection connection) throws SQLException
	{
		ArrayList<DateFloatHolder> objectarray = new ArrayList<DateFloatHolder>();
		 PreparedStatement readStatement = connection.prepareStatement("select datename(weekday, convert(datetime, RecordDate, 103)) as totalusageperday , sum([WaterUsageL/h]) as totalusage\r\n" + 
		 		"  FROM [dbo].[WaterSensorFile]\r\n" + 
		 		"    where CustomerID = 'cu1' AND convert(datetime, RecordDate, 103) between dateadd(day,-7,getdate()) and getdate()\r\n" + 
		 		"  group by datename(weekday, convert(datetime, RecordDate, 103))");
		   ResultSet resultSet = readStatement.executeQuery();
		   DateFloatHolder point = null; 
		   
		   PreparedStatement readStatement2 = connection.prepareStatement("select datename(weekday, convert(datetime, RecordDate, 103)) as totalusageperday , sum([WaterUsageL/h]) as totalusage\r\n" + 
		   		"  FROM [dbo].[WaterSensorFile]\r\n" + 
		   		"    where convert(datetime, RecordDate, 103) between dateadd(day,-7,getdate()) and getdate()\r\n" + 
		   		"  group by datename(weekday, convert(datetime, RecordDate, 103))");
		   
		   ResultSet resultSet2 = readStatement2.executeQuery();
		   
		   
		if (!resultSet.next() || !resultSet2.next()) {
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
	    
	    	   point = new DateFloatHolder(resultSet2.getString(1), resultSet2.getFloat(2));
	    	   objectarray.add(point);
	    	   
	   	    while(resultSet2.next()) 
		    {
		    	point = new DateFloatHolder(resultSet2.getString(1), resultSet2.getFloat(2));
	    		objectarray.add(point);

			
		    }   
		return objectarray;
	   
		
	}
	}
	
	private ArrayList<DateFloatHolder> communityelectricitylast7days (Connection connection) throws SQLException
	{
		ArrayList<DateFloatHolder> objectarray = new ArrayList<DateFloatHolder>();
		 PreparedStatement readStatement = connection.prepareStatement("select datename(weekday, convert(datetime, RecordDate, 103)) as totalusageperday , sum([ElectricityUsageKw/h]) as totalusage\r\n" + 
		 		"  FROM [dbo].[ElectricitySensorFile]\r\n" + 
		 		"    where convert(datetime, RecordDate, 103) between dateadd(day,-7,getdate()) and getdate() and CustomerID = 'cu1'\r\n" + 
		 		"  group by datename(weekday, convert(datetime, RecordDate, 103))");
		   ResultSet resultSet = readStatement.executeQuery();
		   DateFloatHolder point = null; 
		   
		   PreparedStatement readStatement2 = connection.prepareStatement("select datename(weekday, convert(datetime, RecordDate, 103)) as totalusageperday , sum([ElectricityUsageKw/h]) as totalusage\r\n" + 
		   		"  FROM [dbo].[ElectricitySensorFile]\r\n" + 
		   		"    where convert(datetime, RecordDate, 103) between dateadd(day,-7,getdate()) and getdate()\r\n" + 
		   		"  group by datename(weekday, convert(datetime, RecordDate, 103))");
		   
		   ResultSet resultSet2 = readStatement2.executeQuery();
		   
		   
		if (!resultSet.next() || !resultSet2.next()) {
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
	    
	    	   point = new DateFloatHolder(resultSet2.getString(1), resultSet2.getFloat(2));
	    	   objectarray.add(point);
	    	   
	   	    while(resultSet2.next()) 
		    {
		    	point = new DateFloatHolder(resultSet2.getString(1), resultSet2.getFloat(2));
	    		objectarray.add(point);

			
		    }   
		return objectarray;
	   
		
	}
	}
	
	private ArrayList<DateFloatHolder> communitygaslast7days (Connection connection) throws SQLException
	{
		ArrayList<DateFloatHolder> objectarray = new ArrayList<DateFloatHolder>();
		 PreparedStatement readStatement = connection.prepareStatement("select datename(weekday, convert(datetime, RecordDate, 103)) as totalusageperday , sum([GasUsageMJ/H]) as totalusage\r\n" + 
		 		"  FROM [dbo].[GasSensorFile]\r\n" + 
		 		"    where convert(datetime, RecordDate, 103) between dateadd(day,-7,getdate()) and getdate() and CustomerID = 'cu1'\r\n" + 
		 		"  group by datename(weekday, convert(datetime, RecordDate, 103))");
		   ResultSet resultSet = readStatement.executeQuery();
		   DateFloatHolder point = null; 
		   
		   PreparedStatement readStatement2 = connection.prepareStatement("select datename(weekday, convert(datetime, RecordDate, 103)) as totalusageperday , sum([GasUsageMJ/H]) as totalusage\r\n" + 
		   		"  FROM [dbo].[GasSensorFile]\r\n" + 
		   		"    where convert(datetime, RecordDate, 103) between dateadd(day,-7,getdate()) and getdate()\r\n" + 
		   		"  group by datename(weekday, convert(datetime, RecordDate, 103))");
		   
		   ResultSet resultSet2 = readStatement2.executeQuery();
		   
		   
		if (!resultSet.next() || !resultSet2.next()) {
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
	    
	    	   point = new DateFloatHolder(resultSet2.getString(1), resultSet2.getFloat(2));
	    	   objectarray.add(point);
	    	   
	   	    while(resultSet2.next()) 
		    {
		    	point = new DateFloatHolder(resultSet2.getString(1), resultSet2.getFloat(2));
	    		objectarray.add(point);

			
		    }   
		return objectarray;
	   
		
	}
	}
	
	
	 protected void processRequest(HttpServletRequest request, 
             HttpServletResponse response) 
            		 throws ServletException, IOException

            		 
	    { 
		 connectDB();
	      //System.out.println(customerNames.toString());
	      try {
			request.setAttribute("totalElecCons", communityElecConsumption(connection));
			request.setAttribute("totalWaterCons", communityWaterConsumption(connection));
			request.setAttribute("totalGasCons", communityGasConsumption(connection));
			request.setAttribute("communityIndiv7daywater", communitywaterlast7days(connection));
			request.setAttribute("communityIndiv7dayelec", communityelectricitylast7days(connection));
			request.setAttribute("communityIndiv7daygas", communitygaslast7days(connection));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	      try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      
	      RequestDispatcher rd =  
	             request.getRequestDispatcher("CommunityDashboardGraphs.jsp"); 
	      
	      rd.forward(request, response);
	      
	    }
}
