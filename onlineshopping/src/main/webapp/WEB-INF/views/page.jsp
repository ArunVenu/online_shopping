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

<title>Online Shopping - ${title}</title>
<script>
	window.menu = '${title}';
</script>
<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap readable theme CSS  -->
<link href="${css}/bootswatch-readable-theme.css" rel="stylesheet">

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
	<c:if test="${userclickContact}">
		<%@include file="contact.jsp"%>
	</c:if>
	<!-- load when user click listproducts page -->

	<c:if
		test="${userclickCategoryProducts == true or userclickAllProducts == true}">
		<%@include file="listproduct.jsp"%>
	</c:if>

	<!-- Footer -->
	<footer class="py-5 bg-dark footer"> <%@include
		file="./include/footer.jsp"%> </footer>
	<!-- Bootstrap core JavaScript -->
	<script src="${js}/jquery.min.js"></script>

	<!-- Bootstrap core JavaScript -->
	<script src="${js}/bootstrap.bundle.min.js"></script>

	<!-- self coded JavaScript -->
	<script src="${js}/myapp.js"></script>

</body>

</html>
