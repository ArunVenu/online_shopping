<div class="container">

	<div class="row">


		<!-- would be display by sidebar -->
		<div class="col-md-3">

			<%@include file="./include/sidebar.jsp"%>

		</div>

		<!-- to display the actual products -->
		<div class="col-md-9">
			<!-- Added breadcrumb component -->
			<div class="col-lg-12">
				<c:if test="${userclickAllProducts == true}">
					<ol class="breadcrumb">
						<li><a href="${contextRoot}/home">Home</a></li>
						<li class="active">All Products</li>
					</ol>
				</c:if>

				<c:if test="${userclickCategoryProducts == true}">
					<ol class="breadcrumb">
						<li><a href="${contextRoot}/home">Home</a></li>
						<li class="active">Category</li>
						<li class="active">${category.name}</li>
					</ol>
				</c:if>
			</div>

		</div>


	</div>

</div>
