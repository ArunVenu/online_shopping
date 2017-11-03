$(function() {
	
	// solving the active menu problem
	switch (menu) {

	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'All Products':
		$('#listproducts').addClass('active');
		break;
	case 'Manage Products':
		$('#manageproduct').addClass('active');
		break;
	case 'Shopping Cart':
		$('#userModel').addClass('active');
		break;		
	default:
		if (menu == "Home")
			break;
		$('#listproducts').addClass('active');
		$('#a_' + menu).addClass('active');
		break;
	}

	
	//code for Jquery data table
	
	/*var products =[
					['1','ABC'],
					['2','DEF'],
					['3','GHI'],
					['3','GHI'],
					['3','GHI'],
					['3','GHI'],
					['3','GHI'],
					['3','GHI'],
					['3','GHI'],
					['3','GHI'],
					['3','GHI'],
					['3','GHI'],
					['3','GHI'],
					['3','GHI'],
					['3','GHI'],
					['3','GHI'],
					['3','GHI'],
					['3','GHI'],
					['3','GHI'],
					['3','GHI'],
					['3','GHI']
					
					
		
	];
	
	*/
	var $table = $('#productListTable');
	//execute when inside the table
	if($table.length){
		//console.log('Inside the table'
		var jsonUrl = '';
		if(window.categoryId == ''){
			jsonUrl = window.contextRoot + '/json/data/all/products';
			console.log(jsonUrl);
		}
		else {
			jsonUrl = window.contextRoot + '/json/data/category/'+ window.categoryId +'/products';
			console.log(jsonUrl);
		}
		$table.DataTable({
			lengthMenu:[[3,5,10,-1],['3 Records','5 Records','10 Records','ALL Records']],
			pageLength:5,
			
			ajax:{
				dataSrc:'',
				url:jsonUrl
				},
			
			columns:[
				{
					data:'code',
					mRender:function(data,type,row){
						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="dataTableImage"/>';
					}
				},
				{
					data: 'name'
				},
				{
					data: 'brand'
				},
				{
					data: 'unitprice'
				},
				{
					data: 'quantity',
					mRender:function(data,type,row){
						if(data < 1){
							return '<span style="color:red"> Out of Stock !</span>';
						}
						
							return data;
											
					}
						
				},
				{
					data: 'id',
					bSortable:false,
					mRender:function(data,type,row){
						var str = '';
						str +='<a href = "'+window.contextRoot +'/show/'+data+'/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a>&#160';
						
						if(row.quantity < 1){
							str +='<a href = "javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
						}
						else{
							str +='<a href = "'+window.contextroot +'/cart/add/'+data+'/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
						}
						
						
						return str;
					}
				}
			]
			
			
		});
		
		
	}
	
	//Dismissing the alert after 3 seconds !!
	
	/*window.setTimeout(function() {
	    $(".alert").fadeTo(500, 0).slideUp(500, function(){
	        $(this).remove(); 
	    });
	}, 4000);
		*/

	var $table = $('.alert');
	if($table.length){
	setTimeout(function() {
		$table.fadeOut('slow');
	}, 2000);
	}
		
	
	
	
	
	
});

		
