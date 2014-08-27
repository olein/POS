package POS;

import java.sql.*;
import java.util.Vector;

public class Employee {
	
	private String Name;
	private String Address;
	private String Type;
	private int Salary;
	private String Password;
	private int EmployeeID;
	private int BranchID;
	
	private Vector<OutputBean> messages = new Vector<OutputBean>();

	public String Create() throws SQLException
	{
		DataBase db = new DataBase();
		Connection conn = db.connect();
		PreparedStatement preparedStatement = conn
				.prepareStatement("insert into employee(Name,Address,Type,Salary,Password) values (?, ?,?,?,?)");
		// Parameters start with 1
		preparedStatement.setString(1, Name);
		preparedStatement.setString(2, Address);
		preparedStatement.setString(3, Type);
		preparedStatement.setInt(4, Salary);
		preparedStatement.setString(5, Password);

		preparedStatement.executeUpdate();
		conn.close();
	
		return "success";
	}
	
	public String Update() throws SQLException
	{
		DataBase db = new DataBase();
		Connection conn = db.connect();
		
		PreparedStatement preparedStatement = conn
				.prepareStatement("select * from employee where EmployeeID=?");
		// Parameters start with 1
		preparedStatement.setInt(1, EmployeeID);

		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next())
		{
			if(Name.length()==0)
			{
				Name = rs.getString(3);
			}
			if(Address.length()==0)
			{
				Address = rs.getString(4);
			}
			if(Salary==0)
			{
				Salary = rs.getInt(5);
			}
			if(Type.length()==0)
			{
				Type = rs.getString(6);
			}
			if(Password.length()==0)
			{
				Password = rs.getString(7);
			}
			PreparedStatement preparedStatement1 = conn
					.prepareStatement("update employee set Name=?, Address=?, Salary=?,Type=?,Password=? where EmployeeID=?");
			// Parameters start with 1
			preparedStatement1.setString(1, Name);
			preparedStatement1.setString(2, Address);
			preparedStatement1.setInt(3, Salary);
			preparedStatement1.setString(4, Type);
			preparedStatement1.setString(5, Password);
			preparedStatement1.setInt(6, EmployeeID);

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
				.prepareStatement("select * from employee where EmployeeID=?");
		// Parameters start with 1
		preparedStatement.setInt(1, EmployeeID);

		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next())
		{
			Type = rs.getString(6);
		}
		if(Type.equals("Main Manager") || Type.equals("Manager"))
		{
			PreparedStatement preparedStatement1 = conn
					.prepareStatement("update branch set ManagerID=0 where ManagerID=?");
			// Parameters start with 1
			preparedStatement1.setInt(1, EmployeeID);
			
			preparedStatement1.executeUpdate();
		}
		
		PreparedStatement preparedStatement1 = conn
				.prepareStatement("delete from employee where EmployeeID=?");
		// Parameters start with 1
		preparedStatement1.setInt(1, EmployeeID);

		preparedStatement1.executeUpdate();
		
		return "success";
	}
	
	public String EmployeeList() throws SQLException
	{
		DataBase db = new DataBase();
		Connection conn = db.connect();
		
		 PreparedStatement preparedStatement = conn
					.prepareStatement("select * from employee");
			// Parameters start with 1
			

			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				OutputBean bean = new OutputBean();
				bean.setBranchID(rs.getInt(2));
				bean.setAddress(rs.getString(4));
				bean.setName(rs.getString(3));
				bean.setEmployeeID(rs.getInt(1));
				bean.setSalary(rs.getInt(5));
				bean.setType(rs.getString(6));
				messages.add(bean);
				
			}
			conn.close();
		return "success";
	}
	
	public String AssignBranch() throws SQLException
	{
		DataBase db = new DataBase();
		Connection conn = db.connect();
		
		PreparedStatement preparedStatement = conn
				.prepareStatement("select Type from employee where EmployeeID=?");
		// Parameters start with 1
		preparedStatement.setInt(1, EmployeeID);

		ResultSet rs = preparedStatement.executeQuery();
		
		Type = rs.getString(6);
		
		if(Type.equals("Main Manager") || Type.equals("Manager"))
		{
			PreparedStatement preparedStatement1 = conn
					.prepareStatement("update branch set ManagerID=? where BranchID=?");
			// Parameters start with 1
			preparedStatement1.setInt(1, EmployeeID);
			preparedStatement1.setInt(2, BranchID);
			preparedStatement1.executeUpdate();
		}
		
		PreparedStatement preparedStatement1 = conn
					.prepareStatement("update employee set BranchID=? where EmployeeID=?");
			// Parameters start with 1
		preparedStatement1.setInt(2, EmployeeID);
		preparedStatement1.setInt(1, BranchID);
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
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public int getSalary() {
		return Salary;
	}
	public void setSalary(int salary) {
		Salary = salary;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		this.Password = password;
	}

	public int getEmployeeID() {
		return EmployeeID;
	}

	public void setEmployeeID(int employeeID) {
		EmployeeID = employeeID;
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
