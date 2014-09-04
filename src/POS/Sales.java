package POS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;

public class Sales {
	
	private int TransactionID;
	private int EmployeeID;
	private int TotalAmount;
	private int ProductID;
	private int Quantity;
	private String BranchName;
	private int TotalBill;
	private String Type;
	
	public String SalesRegistration() throws SQLException
	{
		DataBase db = new DataBase();
		Connection conn = db.connect();
		
		PreparedStatement preparedStatement11 = conn
				.prepareStatement("insert into "+BranchName+"_transaction_sales (EmployeeID, TotalAmount) values (?, 0)");
		// Parameters start with 1
		preparedStatement11.setInt(1, EmployeeID);
		
		preparedStatement11.executeUpdate();
		
		Map session = ActionContext.getContext().getSession();
		session.put("BN", BranchName);
		
		conn.close();
		setType("Manager");
		return "success";
	}
	
	public String SalesProduct() throws SQLException
	{
		DataBase db = new DataBase();
		Connection conn = db.connect();
		
		
		PreparedStatement preparedStatement11 = conn
				.prepareStatement("select * from "+BranchName+"_storage where ProductID=?");
		// Parameters start with 1
		preparedStatement11.setInt(1, ProductID);
		
		ResultSet rs = preparedStatement11.executeQuery();
		
		while(rs.next())
		{
			int quantity = rs.getInt(2);
			quantity = quantity - Quantity;
			
			preparedStatement11 = conn
					.prepareStatement("update "+BranchName+"_storage set Quantity=? where ProductID=?");
			// Parameters start with 1
			preparedStatement11.setInt(1, quantity);
			preparedStatement11.setInt(2, ProductID);
			preparedStatement11.executeUpdate();
		}
		
		preparedStatement11 = conn.prepareStatement("SELECT max(TransactionID) from "+BranchName+"_transaction_sales");
		rs = preparedStatement11.executeQuery();
		while(rs.next())
		{
			TransactionID = rs.getInt(1);
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
					.prepareStatement("insert into "+BranchName+"_sales (TransactionID,  ProductID, Quantity, TotalPrice) values(?,?,?,?)");
			// Parameters start with 1
			preparedStatement11.setInt(1, TransactionID);
			preparedStatement11.setInt(2, ProductID);
			preparedStatement11.setInt(3, Quantity);
			preparedStatement11.setInt(4, TotalPrice);
			preparedStatement11.executeUpdate();
		}
				
		conn.close();
		return "success";
	}
	
	public String CalculateBill() throws SQLException
	{
		
		DataBase db = new DataBase();
		Connection conn = db.connect();
		
		Map session = ActionContext.getContext().getSession();
		BranchName = (String) session.get("BN");
		
		PreparedStatement preparedStatement111 = conn
				.prepareStatement("select max(TransactionID) from "+BranchName+"_sales");
		// Parameters start with 1
		
		ResultSet rs1 = preparedStatement111.executeQuery();
		while(rs1.next())
		{
			TransactionID = rs1.getInt(1);
			System.out.println(TransactionID);
		}
		
		PreparedStatement preparedStatement11 = conn
				.prepareStatement("select * from "+BranchName+"_sales where TransactionID=?");
		// Parameters start with 1
		preparedStatement11.setInt(1, TransactionID);
		
		ResultSet rs = preparedStatement11.executeQuery();
		
		while(rs.next())
		{
			TotalBill = TotalBill + rs.getInt(4);
			preparedStatement11 = conn
					.prepareStatement("update "+BranchName+"_transaction_sales set TotalAmount=? where TransactionID=?");
			// Parameters start with 1
			preparedStatement11.setInt(1, TotalBill);
			preparedStatement11.setInt(2, TransactionID);
			preparedStatement11.executeUpdate();
		}
		setType("Manager");
		return "success"; 	
	}

	
	public int getTransactionID() {
		return TransactionID;
	}
	public void setTransactionID(int transactionID) {
		TransactionID = transactionID;
	}
	public int getEmployeeID() {
		return EmployeeID;
	}
	public void setEmployeeID(int employeeID) {
		EmployeeID = employeeID;
	}
	public int getTotalAmount() {
		return TotalAmount;
	}
	public int getTotalBill() {
		return TotalBill;
	}

	public void setTotalBill(int totalBill) {
		TotalBill = totalBill;
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

	public String getBranchName() {
		return BranchName;
	}

	public void setBranchName(String branchName) {
		BranchName = branchName;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}
	
	
	

}
