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
	<%@include file="./include/navbar.jsp"%>


	<!-- Page Content -->

	<c:if test="${userclickHome == true }">
		<%@include file="home.jsp"%>
	</c:if>
	<!-- load when user click about page -->
	<c:if test="${userclickAbout == true }">
		<%@include file="about.jsp"%>
	</c:if>
	<!-- load when user click contact page -->
	<c:if test="${userclickContact ==true }">
		<%@include file="contact.jsp"%>
	</c:if>
	<!-- load when user click listproducts page -->

	<c:if
		test="${userclickCategoryProducts == true or userclickAllProducts == true}">
		<%@include file="listproduct.jsp"%>
	</c:if>
	
	<!-- load when user click show product // dynamic product page -->
	<c:if test="${userclicksingleProduct == true}">
		<%@include file="singleProduct.jsp"%>
	</c:if>

	<!-- load when user click show product // dynamic product page -->
	<c:if test="${userclickManageProducts == true}">
		<%@include file="manageProducts.jsp"%>
	</c:if>
	

	<!-- Footer -->
<%-- 	<footer class="py-5 bg-dark footer"> 
	<%@include file="./include/footer.jsp"%> 
	</footer>
 --%>

		<!-- jQuery 
		<script src="${js}/jquery.js"></script>-->
		<script src="${js}/jquery-1.12.4.js"></script>
		
		<!-- Jquery Validation -->
		<script src="${js}/jquery.validate.js"></script>
		
		
		<!-- Bootstrap Core JavaScript -->
		 <script src="${js}/bootstrap.min.js"></script>
		
		<!-- <script src="${js}/bootstrap.js"></script>			 -->
		<!-- Data Table -->
		
		<script src="${js}/jquery.dataTables.min.js"></script>
		
			<!-- BootBox -->
		<script src="${js}/bootbox.min.js"></script>
		
		
		
		<!-- Self coded javascript -->
		<script src="${js}/myapp.js"></script>

</body>

</html>
