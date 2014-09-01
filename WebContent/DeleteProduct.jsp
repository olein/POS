<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<s:url value="style_result.css"/>"/> 
<title>Delete Branch</title>


</head>
<body>
<br></br>
 <br></br>
 <br></br>
<div  align="center">
<div id="select1">
 <b>Please Enter Employee ID to delete Information:</b><br/><br/>
 
 <s:form action = "DeleteProductAction">

  	  <s:textfield name="ProductID"  label ="Product ID"/>
  	  
  	  
      <s:submit/>

 </s:form>
 </div>
 </div>
 </body>
 </html>
