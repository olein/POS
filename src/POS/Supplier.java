package POS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


public class Supplier {
	
	private String Name;
	private String Address;
	private String Description;
	private int SupplierID;
	private Vector<OutputBean> messages = new Vector<OutputBean>();
	
	public String Create() throws SQLException
	{
		DataBase db = new DataBase();
		Connection conn = db.connect();
		
		PreparedStatement preparedStatement = conn
				.prepareStatement("insert into supplier(Name,Address,Description) values (?, ?, ?)");
		// Parameters start with 1
		preparedStatement.setString(1, Name);
		preparedStatement.setString(2, Address);
		preparedStatement.setString(3, Description);

		preparedStatement.executeUpdate();
		conn.close();
		
		return "success";
	}
	
	public String Update() throws SQLException
	{
		DataBase db = new DataBase();
		Connection conn = db.connect();
		
		 PreparedStatement preparedStatement = conn
					.prepareStatement("select * from supplier where SupplierID=?");
			// Parameters start with 1
			preparedStatement.setInt(1, SupplierID);

			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				if(Name.length()==0)
				{
					Name = rs.getString(2);
				}
				if(Address.length()==0)
				{
					Address = rs.getString(3);
				}
				if(Description.length()==0)
				{
					Description = rs.getString(4);
				}
				PreparedStatement preparedStatement1 = conn
						.prepareStatement("update supplier set Name=?, Address=?, Description=? where SupplierID=?");
				// Parameters start with 1
				preparedStatement1.setString(1, Name);
				preparedStatement1.setString(2, Address);
				preparedStatement1.setString(3, Description);
				preparedStatement1.setInt(4, SupplierID);

			    preparedStatement1.executeUpdate();
				
			}
			conn.close();
		
		return "success";
	}
	
	public String Delete() throws SQLException
	{
		DataBase db = new DataBase();
		Connection conn = db.connect();
		PreparedStatement preparedStatement1 = conn
				.prepareStatement("delete from supplier where SupplierID=?");
		// Parameters start with 1
		preparedStatement1.setInt(1, SupplierID);

		preparedStatement1.executeUpdate();
		return "success";
	}
	
	public String SupplierList() throws SQLException
	{
		DataBase db = new DataBase();
		Connection conn = db.connect();
		
		 PreparedStatement preparedStatement = conn
					.prepareStatement("select * from supplier");
			// Parameters start with 1
			

			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				OutputBean bean = new OutputBean();
				bean.setSupplierID(rs.getInt(1));
				bean.setName(rs.getString(2));
				bean.setAddress(rs.getString(3));				
				bean.setDescription(rs.getString(4));
				messages.add(bean);
				
			}
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

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}
	
	public int getSupplierID() {
		return SupplierID;
	}

	public void setSupplierID(int supplierID) {
		SupplierID = supplierID;
	}
	
	public Vector getMessages() {
		return messages;
	}

	public void setMessages(Vector Messages) {
		messages = Messages;
	}

}
