package POS;

import java.sql.*;
import java.util.Vector;



public class Branch {
	
	private String Name;
	private String Location;
	private int ManagerID;
	private int BranchID;
	
	public Vector messages = new Vector();
	
	public String Create() throws SQLException
	{
		DataBase db = new DataBase();
		Connection conn = db.connect();
		
		Statement stmt;
		stmt = conn.createStatement();
	      
	      String sql = "CREATE TABLE "+Name+"_Transaction_sales " +
	                   "(TransactionID INTEGER not NULL, " +
	                   " EmployeeID INTEGER, " + 
	                   " TotalAmount INTEGER, " + 
	                   " Date DATE, Description varchar(256), " + 
	                   " PRIMARY KEY ( TransactionID ));"; 

	      stmt.executeUpdate(sql);
	      
	      sql = "CREATE TABLE "+Name+"_sales " +
                  "(SalesID INTEGER not NULL, " +
                  " TransactionID INTEGER,  " + 
                  " ProductID INTEGER, " + 
                  " Quantity INTEGER, " + 
                  " TotalPrice INTEGER, " +
                  " PRIMARY KEY ( SalesID ))"; 

	      stmt.executeUpdate(sql);
	      
	      sql = "CREATE TABLE "+Name+"_storage " +
                  "(ProductID INTEGER not NULL, " +
                  " Quantity INTEGER,  " +                   
                  " PRIMARY KEY ( ProductID ));"; 

	      stmt.executeUpdate(sql);
	      
	      sql = "CREATE TABLE "+Name+"_Transaction_supply " +
                  "(SupplyID INTEGER not NULL, " +
	    		  " Description varchar(256), " + 
                  " Date DATE, " + 
                  " PRIMARY KEY ( SupplyID ))"; 

	     stmt.executeUpdate(sql);
	     
	     PreparedStatement preparedStatement = conn
					.prepareStatement("insert into branch(Name,Location,ManagerID) values (?, ?,?)");
			// Parameters start with 1
			preparedStatement.setString(1, Name);
			preparedStatement.setString(2, Location);
			preparedStatement.setInt(3, ManagerID);

			preparedStatement.executeUpdate();
			conn.close();
		
		return "success";
	}
	
	public String Update() throws SQLException
	{
		DataBase db = new DataBase();
		Connection conn = db.connect();
		
		 PreparedStatement preparedStatement = conn
					.prepareStatement("select * from branch where BranchID=?");
			// Parameters start with 1
			preparedStatement.setInt(1, BranchID);

			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				if(Name.length()==0)
				{
					Name = rs.getString(2);
				}
				if(Location.length()==0)
				{
					Location = rs.getString(3);
				}
				if(ManagerID==0)
				{
					ManagerID = rs.getInt(4);
				}
				
				PreparedStatement preparedStatement1 = conn
						.prepareStatement("update branch set Name=?, Location=?, ManagerID=? where BranchID=?");
				// Parameters start with 1
				preparedStatement1.setString(1, Name);
				preparedStatement1.setString(2, Location);
				preparedStatement1.setInt(3, ManagerID);
				preparedStatement1.setInt(4, BranchID);

			    preparedStatement1.executeUpdate();
				
			}
			conn.close();
		return "success";
	}
	
	public String Delete() throws SQLException
	{
		DataBase db = new DataBase();
		Connection conn = db.connect();
		
		Statement stmt;
		stmt = conn.createStatement();
		PreparedStatement preparedStatement = conn
				.prepareStatement("select * from branch where BranchID=?");
		// Parameters start with 1
		preparedStatement.setInt(1, BranchID);

		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next())
		{
			Name = rs.getString(2);
		}
		PreparedStatement preparedStatement1 = conn
				.prepareStatement("delete from branch where BranchID=?");
		// Parameters start with 1
		preparedStatement1.setInt(1, BranchID);

		preparedStatement1.executeUpdate();
	      
		String sql = "drop table "+Name+"_sales;"; 
	    stmt.executeUpdate(sql);
	     
	    sql = "drop table "+Name+"_transaction_sales;"; 
	    stmt.executeUpdate(sql);
	    
	    sql = "drop table "+Name+"_storage;"; 
	    stmt.executeUpdate(sql);
	    
	    sql = "drop table "+Name+"_transaction_supply;"; 
	    stmt.executeUpdate(sql);
	    
	    return "success";

	}
	
	public String BranchList() throws SQLException
	{
		DataBase db = new DataBase();
		Connection conn = db.connect();
		
		 PreparedStatement preparedStatement = conn
					.prepareStatement("select * from branch");
			// Parameters start with 1
			

			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				BranchID = rs.getInt(1);
				Name = rs.getString(2);
				Location = rs.getString(3);
				ManagerID = rs.getInt(4);
				
				messages.add(new OutputBean(BranchID,Name,Location,ManagerID));
			}
		
		return "success";
	}
	
	public String AssignBranchManager() throws SQLException
	{
		DataBase db = new DataBase();
		Connection conn = db.connect();
		
		PreparedStatement preparedStatement1 = conn
					.prepareStatement("update branch set ManagerID=? where BranchID=?");
			// Parameters start with 1
		preparedStatement1.setInt(1, ManagerID);
		preparedStatement1.setInt(2, BranchID);
		preparedStatement1.executeUpdate();
		conn.close();
		
		return "success";
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public int getManagerID() {
		return ManagerID;
	}
	public void setManagerID(int managerID) {
		ManagerID = managerID;
	}
	public int getBranchID() {
		return BranchID;
	}
	public void setBranchID(int branchID) {
		BranchID = branchID;
	}
	
	public Vector getMessages() {
		return messages;
	}

	public void setMessages(Vector Messages) {
		messages = Messages;
	}

}
