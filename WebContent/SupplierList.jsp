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
	
	<center><h2>Supplier List</h2></center><br/>

	<br />
	
	<table border="0" align="left" width=30%>
	<tr><td>Supplier ID</td><td>Name</td><td>Address</td><td>Description</td></tr>
	<s:iterator value="messages">
	<tr><td><s:property value="SupplierID"/></td>
	<td><s:property value="Name"/></td>
	<td><s:property value="Address"/></td>
	<td><s:property value="Description"/></td>
	</s:iterator>
	</table>
	
	<br />
</body>
</html>