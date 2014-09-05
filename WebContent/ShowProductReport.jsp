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
	<% request.getSession().invalidate();%>
	<%
response.setHeader("Pragma","no-cache"); // HTTP 1.0
response.setHeader("Cache-Control","no-store"); // HTTP 1.1
response.setDateHeader("Expires", 0);
%>
<%@ include file="/Header.jsp" %>
	
	<center><h2>Product Report</h2></center><br/>

	<br />
	<center><h3>Sales Report</h3></center><br/>
	<table border="0" align="left" width=30%>
	<tr><td>Supplier ID</td><td>Name</td><td>Price</td><td>Description</td></tr>
	<s:iterator value="messages">
	<tr><td><s:property value="SupplierID"/></td>
	<td><s:property value="Name"/></td>
	<td><s:property value="Price"/></td>
	<td><s:property value="Description"/></td></tr>
	</s:iterator>
	</table>
	
	<br /><br/>
	<center><h3>Storage Status</h3></center><br/>
	<table border="0" align="left" width=30%>
	<tr><td>Product ID</td><td>Quantity</td></tr>
	<s:iterator value="storage">
	<tr><td><s:property value="ProductID"/></td>
	<td><s:property value="Quantity"/></td>	
	</s:iterator>
	</table>
	<br /><br/>
	<center><h3>Supply Main Branch</h3></center><br/>
	<table border="0" align="left" width=30%>
	<tr><td>Quantity</td><td>Total Price</td></tr>
	<s:iterator value="BranchRefund">
	<tr><td><s:property value="Quantity"/></td>
	<td><s:property value="TotalPrice"/></td></tr>
	</s:iterator>
	</table>
	<center><h3>Product sales</h3></center><br/>
	<table border="0" align="left" width=30%>
	<tr><td>Name</td><td>TotalPrice</td><td>Quantity</td></tr>
	<s:iterator value="sales">
	<tr><td><s:property value="Name"/></td>
	<td><s:property value="TotalPrice"/></td>
	<td><s:property value="Quantity"/></td></tr>
	</s:iterator>
	</table>
	<center><h3>Product supply</h3></center><br/>
	<table border="0" align="left" width=30%>
	<tr><td>Name</td><td>Quantity</td></tr>
	<s:iterator value="supply">
	<tr><td><s:property value="Name"/></td>	
	<td><s:property value="Quantity"/></td></tr>
	</s:iterator>
	</table>
	
</body>
</html>