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
	private int StartYear;
	private int StartMonth;
	private int StartDay;
	private int EndYear;
	private int EndMonth;
	private int EndDay;
	private String BranchName;
	private int ProductID;
	private int EmployeeID;
	private int ManagerID;
	private String Report;
	private String Status; 
	private String Type;
	private Vector<OutputBean> messages = new Vector<OutputBean>();
	private Vector<OutputBean> storage = new Vector<OutputBean>();
	private Vector<OutputBean> BranchRefund = new Vector<OutputBean>();
	private Vector<OutputBean> supply = new Vector<OutputBean>();
	private Vector<OutputBean> sales = new Vector<OutputBean>();

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
				.prepareStatement("select * from " + BranchName
						+ "_transaction_sales where date between ? and ?");
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

		preparedStatement11 = conn.prepareStatement("select * from "
				+ BranchName + "_storage");
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

	public String TimeRangeMain() throws SQLException {
		DataBase db = new DataBase();
		Connection conn = db.connect();

		String d1 = StartYear + "-" + StartMonth + "-" + StartDay;
		String d2 = EndYear + "-" + EndMonth + "-" + EndDay;
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

	public String TimeRangeBranch() throws SQLException {
		DataBase db = new DataBase();
		Connection conn = db.connect();

		String d1 = StartYear + "-" + StartMonth + "-" + StartDay;
		String d2 = EndYear + "-" + EndMonth + "-" + EndDay;
		PreparedStatement preparedStatement11 = conn
				.prepareStatement("select * from " + BranchName
						+ "_transaction_sales where date between ? and ?");
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

		preparedStatement11 = conn.prepareStatement("select * from "
				+ BranchName + "_storage");
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

	public String ProductReport() throws SQLException {
		DataBase db = new DataBase();
		Connection conn = db.connect();

		PreparedStatement preparedStatement11 = conn
				.prepareStatement("select * from product where ProductID=?");
		// Parameters start with 1
		preparedStatement11.setInt(1, ProductID);

		ResultSet rs = preparedStatement11.executeQuery();

		while (rs.next()) {
			OutputBean bean = new OutputBean();
			bean.setName(rs.getString(3));
			bean.setDescription(rs.getString(5));
			bean.setSupplierID(rs.getInt(2));
			bean.setPrice(rs.getInt(4));
			messages.add(bean);
		}

		preparedStatement11 = conn
				.prepareStatement("select * from storage_main where ProductID=?");
		// Parameters start with 1
		preparedStatement11.setInt(1, ProductID);
		rs = preparedStatement11.executeQuery();

		while (rs.next()) {
			OutputBean bean = new OutputBean();
			bean.setProductID(rs.getInt(1));
			bean.setQuantity(rs.getInt(2));
			storage.add(bean);
		}

		preparedStatement11 = conn
				.prepareStatement("select * from transaction_supply_main where ProductID=?");
		// Parameters start with 1
		preparedStatement11.setInt(1, ProductID);

		ResultSet rs1 = preparedStatement11.executeQuery();
		while (rs1.next()) {
			OutputBean bean = new OutputBean();
			bean.setTotalPrice(rs1.getInt(4));
			bean.setProductID(rs1.getInt(2));
			bean.setQuantity(rs1.getInt(3));
			BranchRefund.add(bean);
		}

		preparedStatement11 = conn.prepareStatement("select Name from branch");
		// Parameters start with 1

		rs1 = preparedStatement11.executeQuery();
		while (rs1.next()) {

			String Name = rs1.getString(1);

			PreparedStatement preparedStatement12 = conn
					.prepareStatement("select * from " + Name
							+ "_transaction_supply where ProductID=?");
			// Parameters start with 1
			preparedStatement12.setInt(1, ProductID);

			ResultSet rs2 = preparedStatement12.executeQuery();
			while (rs2.next()) {
				OutputBean bean2 = new OutputBean();
				bean2.setName(Name);
				bean2.setProductID(rs2.getInt(2));
				bean2.setQuantity(rs2.getInt(3));
				supply.add(bean2);
			}
			PreparedStatement preparedStatement121 = conn
					.prepareStatement("select * from " + Name
							+ "_sales where ProductID=?");
			// Parameters start with 1
			preparedStatement121.setInt(1, ProductID);

			ResultSet rs21 = preparedStatement121.executeQuery();
			while (rs21.next()) {
				OutputBean bean21 = new OutputBean();
				bean21.setName(Name);
				bean21.setTotalPrice(rs21.getInt(4));
				bean21.setQuantity(rs21.getInt(3));
				sales.add(bean21);
			}
		}

		conn.close();
		return "success";
	}

	public String ReportEmployee() throws SQLException
	{
		DataBase db = new DataBase();
		Connection conn = db.connect();
		PreparedStatement preparedStatement = conn
				.prepareStatement("insert into reportemployee(EmployeeID,ManagerID,Report,Status) values (?, ?,?,?)");
		// Parameters start with 1
		preparedStatement.setInt(1, EmployeeID);
		preparedStatement.setInt(2, ManagerID);
		preparedStatement.setString(3, Report);
		preparedStatement.setString(4, "U");
		
		preparedStatement.executeUpdate();
		conn.close();
		setType("Manager");
		return "success";
	}
	
	public String EmployeeReportCheck() throws SQLException
	{
		DataBase db = new DataBase();
		Connection conn = db.connect();
		PreparedStatement preparedStatement = conn
				.prepareStatement("select * from reportemployee where Status='U'");
		// Parameters start with 1
		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			OutputBean bean = new OutputBean();
			bean.setEmployeeID(rs.getInt(1));
			bean.setManagerID(rs.getInt(2));
			bean.setReport(rs.getString(3));
			bean.setDate(rs.getDate(4));
			messages.add(bean);
		}
		PreparedStatement preparedStatement1 = conn
				.prepareStatement("update reportemployee set Status='R' where Status='U'");
		// Parameters start with 1
		preparedStatement1.executeUpdate();
		conn.close();
		setType(" Main manager");
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

	public int getStartYear() {
		return StartYear;
	}

	public int getStartMonth() {
		return StartMonth;
	}

	public void setStartMonth(int startMonth) {
		StartMonth = startMonth;
	}

	public int getEmployeeID() {
		return EmployeeID;
	}

	public void setEmployeeID(int employeeID) {
		EmployeeID = employeeID;
	}

	public int getManagerID() {
		return ManagerID;
	}

	public void setManagerID(int managerID) {
		ManagerID = managerID;
	}

	public String getReport() {
		return Report;
	}

	public void setReport(String report) {
		Report = report;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public int getStartDay() {
		return StartDay;
	}

	public void setStartDay(int startDay) {
		StartDay = startDay;
	}

	public int getEndYear() {
		return EndYear;
	}

	public void setEndYear(int endYear) {
		EndYear = endYear;
	}

	public int getEndMonth() {
		return EndMonth;
	}

	public void setEndMonth(int endMonth) {
		EndMonth = endMonth;
	}

	public int getEndDay() {
		return EndDay;
	}

	public void setEndDay(int endDay) {
		EndDay = endDay;
	}

	public void setStartYear(int startYear) {
		StartYear = startYear;
	}

	public int getProductID() {
		return ProductID;
	}

	public void setProductID(int productID) {
		ProductID = productID;
	}

	public Vector<OutputBean> getSupply() {
		return supply;
	}

	public void setSupply(Vector<OutputBean> supply) {
		this.supply = supply;
	}

	public Vector<OutputBean> getSales() {
		return sales;
	}

	public void setSales(Vector<OutputBean> sales) {
		this.sales = sales;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}
}
