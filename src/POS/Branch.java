package POS;

import java.sql.*;
import java.util.Vector;



public class Branch {
	
	private String Name;
	private String Location;
	private int ManagerID;
	private int BranchID;
	private String Type;
	public Vector<OutputBean> messages = new Vector<OutputBean>();
	
	public String Create() throws SQLException
	{
		DataBase db = new DataBase();
		Connection conn = db.connect();
		
		Statement stmt;
		stmt = conn.createStatement();
		try{
	      conn.setAutoCommit(false);
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
                  "(TransactionID INTEGER not NULL, " +
                   "ProductID INTEGER not NULL, " +
	    		  " Quantity INTEGER not null " + 
                  " )"; 

	     stmt.executeUpdate(sql);
	     
	     PreparedStatement preparedStatement = conn
					.prepareStatement("insert into branch(Name,Location,ManagerID) values (?, ?,?)");
			// Parameters start with 1
			preparedStatement.setString(1, Name);
			preparedStatement.setString(2, Location);
			preparedStatement.setInt(3, ManagerID);

			preparedStatement.executeUpdate();
			conn.commit();
			conn.close();
		setType("Main manager");
		return "success";
		}
		catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		      // If there is an error then rollback the changes.
		      System.out.println("Rolling back data here....");
			  try{
				 if(conn!=null)
		            conn.rollback();
		      }catch(SQLException se2){
		         se2.printStackTrace();
		      }
			  return "failure";
		}
	}
	
	public String Update() throws SQLException
	{
		DataBase db = new DataBase();
		Connection conn = db.connect();
		try{
		      conn.setAutoCommit(false);
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
				else if(ManagerID!=0)
				{
					PreparedStatement preparedStatement1 = conn
							.prepareStatement("update employee set BranchID=? where EmployeeID=?");
					// Parameters start with 1
					int id = rs.getInt(4);
					preparedStatement1.setInt(1, 0);
					preparedStatement1.setInt(2, id);
					preparedStatement1.executeUpdate();
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
			conn.commit();
			conn.close();
			setType("Main manager");
		return "success";
		}
		catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		      // If there is an error then rollback the changes.
		      System.out.println("Rolling back data here....");
			  try{
				 if(conn!=null)
		            conn.rollback();
		      }catch(SQLException se2){
		         se2.printStackTrace();
		      }
			  return "failure";
		}
	}
	
	public String Delete() throws SQLException
	{
		DataBase db = new DataBase();
		Connection conn = db.connect();
		try
		{
		conn.setAutoCommit(false);	
		
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
	    conn.commit();
	    setType("Main manager");
	    return "success";
		}
		catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		      // If there is an error then rollback the changes.
		      System.out.println("Rolling back data here....");
			  try{
				 if(conn!=null)
		            conn.rollback();
		      }catch(SQLException se2){
		         se2.printStackTrace();
		      }
			  return "failure";
		}

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
				OutputBean bean = new OutputBean();
				bean.setBranchID(rs.getInt(1));
				bean.setLocation(rs.getString(3));
				bean.setName(rs.getString(2));
				bean.setManagerID(rs.getInt(4));
				messages.add(bean);
			}
		
		return "success";
	}
	
	public String AssignBranchManager() throws SQLException
	{
		DataBase db = new DataBase();
		Connection conn = db.connect();
		try{
		conn.setAutoCommit(false);		
		PreparedStatement preparedStatement1 = conn
					.prepareStatement("update branch set ManagerID=? where BranchID=?");
			// Parameters start with 1
		preparedStatement1.setInt(1, ManagerID);
		preparedStatement1.setInt(2, BranchID);
		preparedStatement1.executeUpdate();
		
		PreparedStatement preparedStatement = conn
				.prepareStatement("update employee set BranchID=? where EmployeeID=?");
		// Parameters start with 1
		preparedStatement.setInt(1, BranchID);
		preparedStatement.setInt(2, ManagerID);
		preparedStatement.executeUpdate();
		conn.commit();
		conn.close();
		setType("Main manager");
		return "success";
		}
		catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		      // If there is an error then rollback the changes.
		      System.out.println("Rolling back data here....");
			  try{
				 if(conn!=null)
		            conn.rollback();
		      }catch(SQLException se2){
		         se2.printStackTrace();
		      }
			  return "failure";
		}
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

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

}
