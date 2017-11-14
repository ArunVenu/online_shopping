<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<a class="navbar-brand" href="${contextRoot}/home">Online Shopping</a>

		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="nav navbar-nav">
				<!-- <li class="nav-item active"><a class="nav-link" href="${contextRoot}/home">Home
						<span class="sr-only">(current)</span>
				</a></li> -->
				<li class="nav-item" id="listproducts"><a class="nav-link"
					href="${contextRoot}/show/all/products">View Products</a></li>
				<li class="nav-item" id="about"><a class="nav-link"
					href="${contextRoot}/about">About</a></li>
				<li class="nav-item" id="contact"><a class="nav-link"
					href="${contextRoot}/contact">Contact</a></li>


				<!-- Security Access For Admin -->
				<security:authorize access="hasAuthority('ADMIN')">
					<li class="nav-item" id="manageproduct"><a class="nav-link"
						href="${contextRoot}/manage/products">Manage Products</a></li>
				</security:authorize>
			</ul>


			    <ul class="nav navbar-nav navbar-right">
			    	<security:authorize access="isAnonymous()">
	                    <li id="signup">
	                        <a href="${contextRoot}/register">Sign Up</a>
	                    </li>
						<li id="login">
	                        <a href="${contextRoot}/login">Login</a>
	                    </li> 			    	
			    	</security:authorize>
			    	<security:authorize access="isAuthenticated()">
						<li class="dropdown" id="userCart">
						  <a class="btn btn-default dropdown-toggle" href="javascript:void(0)" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
						    ${userModel.fullname}
						    <span class="caret"></span>
						  </a>
						  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
		                    <security:authorize access="hasAuthority('USER')">
			                    <li id="cart">
			                        <a href="${contextRoot}/cart/show">
			                        	<span class="glyphicon glyphicon-shopping-cart"></span>&#160;<span class="badge">${userModel.cart.cartLines}</span> - &#8377; ${userModel.cart.grandTotal} 
			                        </a>
			                    </li>		     
			                	<li role="separator" class="divider"></li>	                                   
		                    </security:authorize>
							<li id="logout">
		                        <a href="${contextRoot}/perform-logout">Logout</a>
		                    </li>                    			    	
						  </ul>		
						</li>    			    
			    	</security:authorize>                    
			    </ul>               
				
										



			
		</div>
	</div>
</nav>

<!-- To do role based access -->
<script>

	window.userRole = '${userModel.role}';

</script>