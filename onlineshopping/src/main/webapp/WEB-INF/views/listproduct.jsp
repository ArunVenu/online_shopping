<div class="container">

	<div class="row">


		<!-- would be display by sidebar -->
		<div class="col-md-3">

			<%@include file="./include/sidebar.jsp"%>

		</div>

		<!-- to display the actual products -->
		<div class="col-md-9">
			<!-- Added breadcrumb component -->
			<div class="row">
				<div class="col-lg-12">

					<c:if test="${userclickAllProducts == true}">
						<script>
							window.categoryId = '';
							console.log(window.categoryId);
						</script>

						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">All Products</li>
						</ol>
					</c:if>

					<c:if test="${userclickCategoryProducts == true}">
						<script>
							window.categoryId = '${category.id}';
							console.log(window.categoryId);
						</script>


						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">Category</li>
							<li class="active">${category.name}</li>
						</ol>
					</c:if>
				</div>
			</div>

			<div class="row">
				<div class="col-xs-12">
				
				<div class="container-fluid">
				
				
					<div class="table-responsive">
					
					
					
					<table id="productListTable"
						class="table table-striped table-borderd">
						<thead>
							<tr>
								
								<th>Code</th>
								<th>Name</th>
								<th>Brand</th>
								<th>Unit Price</th>
								<th>Quantity</th>
								<th></th>
								
							</tr>
						</thead>
						
						
						<tfoot>
							<tr>
								<th>Code</th>				
								<th>Name</th>
								<th>Brand</th>
								<th>Unit Price</th>
								<th>Quantity</th>
								<th></th>
							</tr>
						</tfoot>
						

					</table>
					
					
					
					
					
					
					
					</div>
				
				
				
				</div>



				</div>
			</div>
		</div>



	</div>

</div>
