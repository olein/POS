<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main Manager</title>
<link rel="stylesheet" type="text/css"
	href="<s:url value="style_home.css"/>" />
</head>
<body>
	
	<%
response.setHeader("Pragma","no-cache"); // HTTP 1.0
response.setHeader("Cache-Control","no-store"); // HTTP 1.1
response.setDateHeader("Expires", 0);
%>

<%@ include file="/Header.jsp" %>

	<br />
	<br />
	<h1>Welcome</h1>
	<br />
</body>
</html>