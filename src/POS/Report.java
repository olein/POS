package POS;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Report {

	private int Year;
	private int Month;
	private String BranchName;
	private Vector<OutputBean> messages = new Vector<OutputBean>();
	private Vector<OutputBean> storage = new Vector<OutputBean>();
	private Vector<OutputBean> BranchRefund = new Vector<OutputBean>();

	public String ReportMain() throws SQLException {
		DataBase db = new DataBase();
		Connection conn = db.connect();

		String d1 = Year + "-" + Month + "-01";
		String d2 = Year + "-" + Month + "-31";
		PreparedStatement preparedStatement11 = conn
				.prepareStatement("select * from supply where date between ? and ?");
		// Parameters start with 1
		preparedStatement11.setString(1, d1);
		preparedStatement11.setString(2, d2);

		ResultSet rs = preparedStatement11.executeQuery();

		while (rs.next()) {
			OutputBean bean = new OutputBean();
			bean.setSupplierID(rs.getInt(4));
			bean.setDate(rs.getDate(2));
			bean.setTotalAmount(rs.getInt(3));
			bean.setTransactionID(rs.getInt(1));
			messages.add(bean);
		}

		preparedStatement11 = conn
				.prepareStatement("select * from storage_main");
		// Parameters start with 1

		rs = preparedStatement11.executeQuery();

		while (rs.next()) {
			OutputBean bean = new OutputBean();
			bean.setProductID(rs.getInt(1));
			bean.setQuantity(rs.getInt(2));
			storage.add(bean);
		}

		preparedStatement11 = conn.prepareStatement("select Name from branch");
		// Parameters start with 1
		rs = preparedStatement11.executeQuery();

		while (rs.next()) {
			String Name = rs.getString(1);

			preparedStatement11 = conn
					.prepareStatement("select a.TransactionID,a.Date, a.BranchID, b.ProductID, b.Quantity from BranchRefund as a, "
							+ Name
							+ "_transaction_supply as b where a.TransactionID = b.TransactionID and date between ? and ?");
			// Parameters start with 1
			preparedStatement11.setString(1, d1);
			preparedStatement11.setString(2, d2);
			ResultSet rs1 = preparedStatement11.executeQuery();
			while (rs1.next()) {
				OutputBean bean = new OutputBean();
				bean.setTransactionID(rs1.getInt(1));
				bean.setDate(rs1.getDate(2));
				bean.setBranchID(rs1.getInt(3));
				bean.setProductID(rs1.getInt(4));
				bean.setQuantity(rs1.getInt(5));
				BranchRefund.add(bean);
			}
		}
		conn.close();
		return "success";
	}
	
	public String ReportBranch() throws SQLException {
		DataBase db = new DataBase();
		Connection conn = db.connect();

		String d1 = Year + "-" + Month + "-01";
		String d2 = Year + "-" + Month + "-31";
		PreparedStatement preparedStatement11 = conn
				.prepareStatement("select * from "+BranchName+"_transaction_sales where date between ? and ?");
		// Parameters start with 1
		preparedStatement11.setString(1, d1);
		preparedStatement11.setString(2, d2);

		ResultSet rs = preparedStatement11.executeQuery();

		while (rs.next()) {
			OutputBean bean = new OutputBean();
			bean.setDate(rs.getDate(4));
			bean.setEmployeeID(rs.getInt(2));
			bean.setTotalAmount(rs.getInt(3));
			bean.setTransactionID(rs.getInt(1));
			messages.add(bean);
		}

		preparedStatement11 = conn
				.prepareStatement("select * from "+BranchName+"_storage");
		// Parameters start with 1

		rs = preparedStatement11.executeQuery();

		while (rs.next()) {
			OutputBean bean = new OutputBean();
			bean.setProductID(rs.getInt(1));
			bean.setQuantity(rs.getInt(2));
			storage.add(bean);
		}

	

			preparedStatement11 = conn
					.prepareStatement("select a.TransactionID,a.Date, a.BranchID, b.ProductID, b.Quantity from BranchRefund as a, "
							+ BranchName
							+ "_transaction_supply as b where a.TransactionID = b.TransactionID and date between ? and ?");
			// Parameters start with 1
			preparedStatement11.setString(1, d1);
			preparedStatement11.setString(2, d2);
			ResultSet rs1 = preparedStatement11.executeQuery();
			while (rs1.next()) {
				OutputBean bean = new OutputBean();
				bean.setTransactionID(rs1.getInt(1));
				bean.setDate(rs1.getDate(2));
				bean.setBranchID(rs1.getInt(3));
				bean.setProductID(rs1.getInt(4));
				bean.setQuantity(rs1.getInt(5));
				BranchRefund.add(bean);
			}
		
		conn.close();
		return "success";
	}

	public int getYear() {
		return Year;
	}

	public void setYear(int year) {
		Year = year;
	}

	public int getMonth() {
		return Month;
	}

	public void setMonth(int month) {
		Month = month;
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

	public Vector<OutputBean> getBranchRefund() {
		return BranchRefund;
	}

	public void setBranchRefund(Vector<OutputBean> branchRefund) {
		BranchRefund = branchRefund;
	}

	public String getBranchName() {
		return BranchName;
	}

	public void setBranchName(String branchName) {
		BranchName = branchName;
	}
}
