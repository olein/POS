package POS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;


import com.opensymphony.xwork2.ActionContext;

public class Login {

	private String Name;
	private String Password;
	private String Type;
	
	
	public String Login() throws Exception {

		Map session = ActionContext.getContext().getSession();
		session.put("logged-in", "true");
		
		
		DataBase db = new DataBase();
		Connection conn = db.connect();
		PreparedStatement preparedStatement = conn
				.prepareStatement("select Type from employee where Name=? and Password=?");
		// Parameters start with 1
		preparedStatement.setString(1, Name);
		preparedStatement.setString(2, Password);

		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next())
		{
			Type = rs.getString(1);
			
			if(Type.equals("Main manager"))
			{
				return "Main manager";
			}
			if(Type.equals("Manager"))
			{
				return "Manager";
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
	
}
