<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List</title>
<link rel="stylesheet" type="text/css"
	href="<s:url value="style_home.css"/>" />

</head>
<body>
	
<%@ include file="/Header.jsp" %>
	
	<center><h2>Product Report</h2></center><br/>

	<br />
	<center><h3>Branch refund Detail</h3></center><br/>
	<table border="0" align="left" width=30%>
	<tr><td>TransactionID</td><td>Product ID</td><td>Name</td><td>Quantity</td></tr>
	<s:iterator value="messages">
	<tr><td><s:property value="TransactionID"/></td>
	<td><s:property value="ProductID"/></td>
	<td><s:property value="Name"/></td>
	<td><s:property value="Quantity"/></td></tr>
	</s:iterator>
	</table>
	
	
	
	
	
</body>
</html>