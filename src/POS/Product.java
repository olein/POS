package POS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Product {
	
	private String Name;
	private int Price;
	private int SupplierID;
	private String Description;
	private int ProductID;
	
	
	private Vector<OutputBean> messages = new Vector<OutputBean>();
	
	public String Create() throws SQLException
	{
		DataBase db = new DataBase();
		Connection conn = db.connect();
		
		PreparedStatement preparedStatement = conn
				.prepareStatement("insert into product(Name,Price,SupplierID,Description) values (?, ?, ?, ?)");
		// Parameters start with 1
		preparedStatement.setString(1, Name);
		preparedStatement.setInt(2, Price);
		preparedStatement.setInt(3, SupplierID);
		preparedStatement.setString(4, Description);
		preparedStatement.executeUpdate();
		
		PreparedStatement preparedStatement1 = conn
				.prepareStatement("select * from product where Name=?");
		// Parameters start with 1
		preparedStatement1.setString(1, Name);

		ResultSet rs = preparedStatement1.executeQuery();
		while(rs.next())
		{
			ProductID = rs.getInt(1);
		}
		
		PreparedStatement preparedStatement11 = conn
				.prepareStatement("insert into storage_main(ProductID,Quantity) values (?,0)");
		// Parameters start with 1
		preparedStatement11.setInt(1, ProductID);
		preparedStatement11.executeUpdate();
		conn.close();
		return "success";
	}
	
	public String Update() throws SQLException
	{
		DataBase db = new DataBase();
		Connection conn = db.connect();
		
		 PreparedStatement preparedStatement = conn
					.prepareStatement("select * from product where ProductID=?");
			// Parameters start with 1
			preparedStatement.setInt(1, ProductID);

			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				if(Name.length()==0)
				{
					Name = rs.getString(3);
				}
				if(Price==0)
				{
					Price = rs.getInt(4);
				}
				if(SupplierID==0)
				{
					SupplierID = rs.getInt(2);
				}
				if(Description.length()==0)
				{
					Description = rs.getString(5);
				}
				
				PreparedStatement preparedStatement1 = conn
						.prepareStatement("update product set Name=?, Price=?, SupplierID=? where Description=?");
				// Parameters start with 1
				preparedStatement1.setString(1, Name);
				preparedStatement1.setInt(2, Price);
				preparedStatement1.setInt(3, SupplierID);
				preparedStatement1.setString(4, Description);

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
				.prepareStatement("delete from product where ProductID=?");
		// Parameters start with 1
		preparedStatement1.setInt(1, ProductID);

		preparedStatement1.executeUpdate();
		return "success";
	}
	
	public String ProductList() throws SQLException
	{
		DataBase db = new DataBase();
		Connection conn = db.connect();
		
		 PreparedStatement preparedStatement = conn
					.prepareStatement("select * from product");
			// Parameters start with 1
			

			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				OutputBean bean = new OutputBean();
				bean.setProductID(rs.getInt(1));
				bean.setDescription(rs.getString(5));
				bean.setName(rs.getString(3));
				bean.setSupplierID(rs.getInt(2));
				bean.setPrice(rs.getInt(4));
				
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
	public int getPrice() {
		return Price;
	}
	public void setPrice(int price) {
		Price = price;
	}
	public int getSupplierID() {
		return SupplierID;
	}
	public void setSupplierID(int supplierID) {
		SupplierID = supplierID;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Vector<OutputBean> getMessages() {
		return messages;
	}
	public void setMessages(Vector<OutputBean> messages) {
		this.messages = messages;
	}
	public int getProductID() {
		return ProductID;
	}

	public void setProductID(int productID) {
		ProductID = productID;
	}

}
