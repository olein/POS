package POS;

public class OutputBean {
	
	private String Name;
	private String Location;
	private int ManagerID;
	private int BranchID;
	
	public OutputBean(int BranchID,String Name,String Location,int ManagerID)
	{
		BranchID = this.BranchID;
		Name  = this.Name;
		Location = this.Location;
		ManagerID = this.ManagerID;
	}

}
