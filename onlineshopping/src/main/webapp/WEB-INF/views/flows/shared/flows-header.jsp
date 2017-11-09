<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">



<title>Online Shopping - ${title}</title>
<script>
	window.menu = '${title}';

	window.contextRoot = '${contextRoot}';
</script>
<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap readable theme -->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">

<!-- Bootstrap Datatable -->

<!--<link href="${css}/jquery.dataTables.min.css" rel="stylesheet">  -->

<link href="${css}/bootstrap.min.css" rel="stylesheet">

<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">


<!-- Custom styles for this template -->
<link href="${css}/myapp.css" rel="stylesheet">
</head>

<body>

	<!-- Navbar -->
	<!-- Page Content -->
	
<%@include file="../shared/flows-navbar.jsp" %>
