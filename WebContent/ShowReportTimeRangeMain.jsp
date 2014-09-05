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
	
	<center><h2>Report Main Branch</h2></center><br/>

	<br />
	<center><h3>Supply Report</h3></center><br/>
	<table border="0" align="left" width=30%>
	<tr><td>Supplier ID</td><td>Date</td><td>Total Amount</td><td>Transaction ID</td></tr>
	<s:iterator value="messages">
	<tr><td><s:property value="SupplierID"/></td>
	<td><s:property value="date"/></td>
	<td><s:property value="TotalAmount"/></td>
	<td><s:property value="TransactionID"/></td></tr>
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
	<center><h3>Branch Refund</h3></center><br/>
	<table border="0" align="left" width=30%>
	<tr><td>Transaction ID</td><td>Date</td><td>Branch ID</td><td>Product ID</td><td>Quantity</td></tr>
	<s:iterator value="BranchRefund">
	<tr><td><s:property value="TransactionID"/></td>
	<td><s:property value="date"/></td>
	<td><s:property value="branchID"/></td>
	<td><s:property value="ProductID"/></td>
	<td><s:property value="Quantity"/></td></tr>
	</s:iterator>
	</table>
</body>
</html>