<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Welcome to POS system</title>
<link rel="stylesheet" type="text/css"
	href="<s:url value="style_index.css"/>" /> 
	
</head>
<body>
	<% request.getSession().invalidate();%>
	<%
response.setHeader("Pragma","no-cache"); // HTTP 1.0
response.setHeader("Cache-Control","no-store"); // HTTP 1.1
response.setDateHeader("Expires", 0);
%>
<br></br>
<div align="center" style="border:2px solid;border-radius:25px">
<h1><strong>Point Of Sale System</strong></h1>
 </div>
 <br></br>
 <br></br>
 <br></br>
 <div align="center">
 <h3 class="login-button">Login
<s:form action="Login">

				<s:textfield name="name" label="Name" />
				<s:password name="password" label="Password" />

				<s:submit label="Login" />

			</s:form>
</h3>

</div>
</body>
</html>


