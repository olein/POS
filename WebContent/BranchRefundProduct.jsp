<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="POS.Supply" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<s:url value="style_result.css"/>"/> 
<title>Product Supply</title>


<s:form action = "BranchRefundProduct">

  	  <s:textfield name="ProductID"  label ="Product ID"/>
  	  <s:textfield name="Quantity"  label ="Quantity"/>
  	  
      <s:submit/>

 </s:form>
 
 <a href="<s:url action="CalculateBill" />">Calculate Bill</a>

</head>
<body>


</body>
</html>