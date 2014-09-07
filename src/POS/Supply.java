package POS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Vector;

public class Supply {
	
	private int SupplierID;
	private Date date;
	private int TransactionID;
	private int TotalAmount;
	private int ProductID;
	private int Quantity;
	private int TotalBill;
	private int BranchID;
	private String Name;
	private String BranchName;
	private Vector<OutputBean> messages = new Vector<OutputBean>();
	private Vector<OutputBean> storage = new Vector<OutputBean>();
	
	public String SupplyRegistration() throws SQLException
	{
		DataBase db = new DataBase();
		Connection conn = db.connect();
		try{
			
		conn.setAutoCommit(false);
		PreparedStatement preparedStatement11 = conn
				.prepareStatement("insert into supply (SupplierID, TotalAmount) values (?, 0)");
		// Parameters start with 1
		preparedStatement11.setInt(1, SupplierID);
		
		preparedStatement11.executeUpdate();
		
		preparedStatement11 = conn.prepareStatement("SELECT max(TransactionID) from supply");
		ResultSet rs = preparedStatement11.executeQuery();
		while(rs.next())
		{
			TransactionID = rs.getInt(1);
		}
		conn.commit();
		conn.close();
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
	
	public String SupplyProduct() throws SQLException
	{
		DataBase db = new DataBase();
		Connection conn = db.connect();
		try{
			
		conn.setAutoCommit(false);
		PreparedStatement preparedStatement11 = conn
				.prepareStatement("select * from storage_main where ProductID=?");
		// Parameters start with 1
		preparedStatement11.setInt(1, ProductID);
		
		ResultSet rs = preparedStatement11.executeQuery();
		
		while(rs.next())
		{
			int quantity = rs.getInt(2);
			quantity = quantity + Quantity;
			
			preparedStatement11 = conn
					.prepareStatement("update storage_main set Quantity=? where ProductID=?");
			// Parameters start with 1
			preparedStatement11.setInt(1, quantity);
			preparedStatement11.setInt(2, ProductID);
			preparedStatement11.executeUpdate();
		}
		
		preparedStatement11 = conn
				.prepareStatement("select Price from product where ProductID=?");
		// Parameters start with 1
		preparedStatement11.setInt(1, ProductID);
		
		rs = preparedStatement11.executeQuery();
		
		while(rs.next())
		{
			int price = rs.getInt(1);
			int TotalPrice = Quantity*price;
			
			preparedStatement11 = conn
					.prepareStatement("insert into transaction_supply_main (TransactionID,  ProductID, Quantity, TotalPrice) values(?,?,?,?)");
			// Parameters start with 1
			preparedStatement11.setInt(1, TransactionID);
			preparedStatement11.setInt(2, ProductID);
			preparedStatement11.setInt(3, Quantity);
			preparedStatement11.setInt(4, TotalPrice);
			preparedStatement11.executeUpdate();
		}
		conn.commit();		
		conn.close();
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
	
	public String CalculateBill() throws SQLException
	{
		
		DataBase db = new DataBase();
		Connection conn = db.connect();
		try
		{
			
		conn.setAutoCommit(false);
		PreparedStatement preparedStatement111 = conn
				.prepareStatement("select max(TransactionID) from transaction_supply_main");
		// Parameters start with 1
		
		ResultSet rs1 = preparedStatement111.executeQuery();
		while(rs1.next())
		{
			TransactionID = rs1.getInt(1);
			System.out.println(TransactionID);
		}
		
		PreparedStatement preparedStatement11 = conn
				.prepareStatement("select * from transaction_supply_main where TransactionID=?");
		// Parameters start with 1
		preparedStatement11.setInt(1, TransactionID);
		
		ResultSet rs = preparedStatement11.executeQuery();
		
		while(rs.next())
		{
			TotalBill = TotalBill + rs.getInt(4);
			preparedStatement11 = conn
					.prepareStatement("update supply set TotalAmount=? where TransactionID=?");
			// Parameters start with 1
			preparedStatement11.setInt(1, TotalBill);
			preparedStatement11.setInt(2, TransactionID);
			preparedStatement11.executeUpdate();
		}
		conn.commit();
		conn.close();
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
	
	public String BranchRefund() throws SQLException
	{
		DataBase db = new DataBase();
		Connection conn = db.connect();
		try
		{
			
		conn.setAutoCommit(false);
		PreparedStatement preparedStatement11 = conn
				.prepareStatement("insert into branchrefund (BranchID) values (?)");
		// Parameters start with 1
		preparedStatement11.setInt(1, BranchID);
		
		preparedStatement11.executeUpdate();
		
		preparedStatement11 = conn.prepareStatement("SELECT max(TransactionID) from supply");
		ResultSet rs = preparedStatement11.executeQuery();
		while(rs.next())
		{
			TransactionID = rs.getInt(1);
		}
		conn.commit();
		conn.close();
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
	
	public String BranchRefundProduct() throws SQLException
	{
		DataBase db = new DataBase();
		Connection conn = db.connect();
		try
		{
			
		conn.setAutoCommit(false);
		PreparedStatement preparedStatement10 = conn.prepareStatement("SELECT max(TransactionID) from branchrefund");
		ResultSet rs = preparedStatement10.executeQuery();
		while(rs.next())
		{
			TransactionID = rs.getInt(1);
		}
		
		preparedStatement10 = conn.prepareStatement("SELECT BranchID from branchrefund where TransactionID=?");
		preparedStatement10.setInt(1, TransactionID);
		rs = preparedStatement10.executeQuery();
		while(rs.next())
		{
			BranchID = rs.getInt(1);
		}
		
		preparedStatement10 = conn.prepareStatement("SELECT Name from branch where BranchID=?");
		preparedStatement10.setInt(1, BranchID);
		rs = preparedStatement10.executeQuery();
		while(rs.next())
		{
			Name = rs.getString(1);
		}
		
		PreparedStatement preparedStatement11 = conn
				.prepareStatement("select * from storage_main where ProductID=?");
		// Parameters start with 1
		preparedStatement11.setInt(1, ProductID);
		
		rs = preparedStatement11.executeQuery();
		
		while(rs.next())
		{
			int quantity = rs.getInt(2);
			quantity = quantity - Quantity;
			
			preparedStatement11 = conn
					.prepareStatement("update storage_main set Quantity=? where ProductID=?");
			// Parameters start with 1
			preparedStatement11.setInt(1, quantity);
			preparedStatement11.setInt(2, ProductID);
			preparedStatement11.executeUpdate();
		}		
		
		preparedStatement11 = conn
				.prepareStatement("insert into "+Name+"_transaction_supply (TransactionID, ProductID, Quantity) values (?,?,?)");
		// Parameters start with 1
		preparedStatement11.setInt(1, TransactionID);
		preparedStatement11.setInt(2, ProductID);
		preparedStatement11.setInt(3, Quantity);
		preparedStatement11.executeUpdate();
		
		preparedStatement11 = conn
				.prepareStatement("select * from "+Name+"_storage where ProductID=?");
		// Parameters start with 1
		preparedStatement11.setInt(1, ProductID);
		
		rs = preparedStatement11.executeQuery();
		
		int quantity = 0;
		while(rs.next())
		{
			quantity = rs.getInt(2);
			
			quantity = quantity + Quantity;
			
			preparedStatement11 = conn
					.prepareStatement("update "+Name+"_storage set Quantity=? where ProductID=?");
			// Parameters start with 1
			preparedStatement11.setInt(1, quantity);
			preparedStatement11.setInt(2, ProductID);
			preparedStatement11.executeUpdate();
		}	
		
		if(quantity==0)
		{
			preparedStatement11 = conn
					.prepareStatement("insert into "+Name +"_storage (ProductID, Quantity) values (?,?)");
			// Parameters start with 1
			preparedStatement11.setInt(1, ProductID);
			preparedStatement11.setInt(2, Quantity);
			preparedStatement11.executeUpdate();
		}
		conn.commit();
		conn.close();
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
	
	public String SupplyDetail() throws SQLException {
		DataBase db = new DataBase();
		Connection conn = db.connect();

		PreparedStatement preparedStatement11 = conn
				.prepareStatement("select * from supply");
		// Parameters start with 1		

		ResultSet rs = preparedStatement11.executeQuery();

		while (rs.next()) {
			OutputBean bean = new OutputBean();
			bean.setDate(rs.getDate(2));
			bean.setTransactionID(rs.getInt(1));
			bean.setSupplierID(rs.getInt(4));
			bean.setTotalPrice(rs.getInt(3));
			messages.add(bean);
		}
		
		preparedStatement11 = conn
				.prepareStatement("select * from transaction_supply_main");
		
		rs = preparedStatement11.executeQuery();

		while (rs.next()) {
			OutputBean bean = new OutputBean();
			bean.setTransactionID(rs.getInt(1));
			bean.setProductID(rs.getInt(2));
			bean.setQuantity(rs.getInt(3));
			bean.setTotalPrice(rs.getInt(4));
			storage.add(bean);
		}
		conn.close();
		return "success";
	}
	
	public String BranchRefundDetail() throws SQLException {
		DataBase db = new DataBase();
		Connection conn = db.connect();

		PreparedStatement preparedStatement11 = conn
				.prepareStatement("select a.TransactionID, a.ProductID, b.Name, a.Quantity from "+BranchName+"_transaction_supply as a, Product as b where a.ProductID=b.ProductID");
		// Parameters start with 1		

		ResultSet rs = preparedStatement11.executeQuery();

		while (rs.next()) {
			OutputBean bean = new OutputBean();
			bean.setQuantity(rs.getInt(4));
			bean.setTransactionID(rs.getInt(1));
			bean.setProductID(rs.getInt(2));
			bean.setName(rs.getString(3));
			messages.add(bean);
		}		
		
		conn.close();
		return "success";
	}
	
	public int getSupplierID() {
		return SupplierID;
	}
	public Vector<OutputBean> getMessages() {
		return messages;
	}

	public void setMessages(Vector<OutputBean> messages) {
		this.messages = messages;
	}

	public Vector<OutputBean> getStorage() {
		return storage;
	}

	public void setStorage(Vector<OutputBean> storage) {
		this.storage = storage;
	}

	public void setSupplierID(int supplierID) {
		SupplierID = supplierID;
	}
	public java.util.Date getDate() {
		return date;
	}
	public void setDate(java.util.Date date) {
		date = date;
	}
	public int getTransactionID() {
		return TransactionID;
	}
	public void setTransactionID(int transactionID) {
		TransactionID = transactionID;
	}
	public int getTotalAmount() {
		return TotalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		TotalAmount = totalAmount;
	}

	public int getProductID() {
		return ProductID;
	}

	public void setProductID(int productID) {
		ProductID = productID;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public int getTotalBill() {
		return TotalBill;
	}

	public void setTotalBill(int totalBill) {
		TotalBill = totalBill;
	}

	public int getBranchID() {
		return BranchID;
	}

	public void setBranchID(int branchID) {
		BranchID = branchID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getBranchName() {
		return BranchName;
	}

	public void setBranchName(String branchName) {
		BranchName = branchName;
	}
	

}
