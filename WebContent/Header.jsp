<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<s:url value="style_home.css"/>" />
</head>
<body>


	<ul class="my-dropdown-menu">
		<li><a href="MainManager.jsp">Home</a></li>
		<li><a href="">Branch</a> <!-- sub menu for account -->
			<ul>
				<li><a href="<s:url action="CreateBranch" />">Create Branch</a></li>
				<li><a href="<s:url action="UpdateBranch" />">Update Branch</a></li>
				<li><a href="<s:url action="DeleteBranch" />">Delete Branch</a></li>
				<li><a href="<s:url action="BranchList" />">Branch List</a></li>
				<li><a href="<s:url action="AsssignBranchManager" />">Assign
						Branch Manager</a></li>
			</ul></li>
			
		<li><a href="">Employee</a> <!-- sub menu for account -->
			<ul>
				<li><a href="<s:url action="CreateEmployee" />">Create Employee</a></li>
				<li><a href="<s:url action="UpdateEmployee" />">Update Employee</a></li>
				<li><a href="<s:url action="DeleteEmployee" />">Delete Employee</a></li>
				<li><a href="<s:url action="EmployeeList" />">Employee List</a></li>
				<li><a href="<s:url action="AsssignBranch" />">Assign
						Branch</a></li>
			</ul></li>	

		<li><a href="<s:url action="Logout" />">Logout</a></li>

	</ul>

</body>
</html>