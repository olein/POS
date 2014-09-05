<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<s:url value="style_result.css"/>"/> 
<title>Create New Account</title>


</head>
<body>
<br></br>
 <br></br>
 <br></br>
<div  align="center">
<div id="select1">
 <b>Please Enter required information:</b><br/><br/>
 
 <s:form action = "TimeRangeBranchAction">
  	  <s:textfield name="BranchName"  label ="Branch Name"/>
  	  <s:textfield name="StartYear"  label ="Start Year"/>
  	  <s:textfield name="StartMonth"  label ="Start Month"/>
  	  <s:textfield name="StartDay"  label ="Start Day"/>
  	  <s:textfield name="EndYear"  label ="End Year"/>
  	  <s:textfield name="EndMonth"  label ="End Month"/>
  	  <s:textfield name="EndDay"  label ="End Day"/>
      <s:submit/>

 </s:form>
 </div>
 </div>
 </body>
 </html>
