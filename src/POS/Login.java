package POS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;


import com.opensymphony.xwork2.ActionContext;
import java.io.*;
public class Login {

	private String Name;
	private String Password;
	private String Type;
	private int BranchID;
	
	
	public String Login() throws Exception {

		 try {
		      String line;
		      Process p = Runtime.getRuntime().exec("c:/fahim-wamp-starter.bat");
		      p.waitFor();
		      System.out.println(p.exitValue());
		    }
		    catch (Exception err) {
		      err.printStackTrace();
		    }
		Map session = ActionContext.getContext().getSession();
		session.put("logged-in", "true");
		
		for(int i=0;i<1000000;i++)
		{
			for(int j=0;j<1000;j++)
			{}
		}
		DataBase db = new DataBase();
		Connection conn = db.connect();
		PreparedStatement preparedStatement = conn
				.prepareStatement("select BranchID,Type from employee where Name=? and Password=?");
		// Parameters start with 1
		preparedStatement.setString(1, Name);
		preparedStatement.setString(2, Password);

		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next())
		{
			Type = rs.getString(2);
			BranchID = rs.getInt(1);
			preparedStatement = conn
					.prepareStatement("select Name from branch where BranchID=?");
			// Parameters start with 1
			preparedStatement.setInt(1, BranchID);
			ResultSet rs1 = preparedStatement.executeQuery();
			while(rs1.next())
			{
				String BN = rs1.getString(1);
				Map session1 = ActionContext.getContext().getSession();
				session1.put("BN", BN);
			}
			if(Type.equals("Main manager"))
			{
				return "Main manager";
			}
			if(Type.equals("Manager"))
			{
				return "Manager";
			}
			if(Type.equals("Salesman"))
			{
				return "Salesman";
			}
		}
		
		
		return "failure";
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public int getBranchID() {
		return BranchID;
	}

	public void setBranchID(int branchID) {
		BranchID = branchID;
	}
	
}
