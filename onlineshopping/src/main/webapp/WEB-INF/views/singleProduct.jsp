<div class="container">

	<!-- Breadcrumb -->
	<div class="row">

		<div class="col-xs-12">

			<ol class="breadcrumb">

				<li><a href="${contextRoot}/home">Home</a></li>
				<li><a href="${contextRoot}/show/all/products">Products</a></li>
				<li class="active">${product.name}</li>


			</ol>


		</div>

		<div class="row">

			<!-- display the product image -->
			<div class="col-xs-12 col-sm-4">

				<div class="thumbnail">

					<img src="${images}/${product.code}.jpg" class="img img-responsive" />

				</div>

			</div>
			<!-- display the product image -->
			<div class="col-xs-12 col-sm-8">

				<h3>${product.name}</h3>
				<hr/>
				<h5>${product.description}</h5>
				<hr/>
				<h4>Price: <strong> ${product.unitprice} /-</strong></h4>

				<c:choose>
					<c:when test="${product.quantity < 1}">
						<h6>Qty. Available:<span style="color:red"> Out of Stack!</span></h6>
						<a href="javascript:void(0)" class="btn btn-success disabled"><strike><span class="glyphicon glyphicon-shopping-cart"></span>Add to Cart</strike></a>
					</c:when>
					<c:otherwise>
						<h6>Qty. Available:${product.quantity}</h6>
						<a href="${contextRoot}/cart/add/${product.id}/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span>Add to Cart</a>
					</c:otherwise>
				</c:choose>
				<a href="${contextRoot}/show/all/products" class="btn btn-primary">Back</a>
				<hr/>
			</div>



	</div>






</div>