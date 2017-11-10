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

	
	// code for Jquery data table
	
	/*
	 * var products =[ ['1','ABC'], ['2','DEF'], ['3','GHI'], ['3','GHI'],
	 * ['3','GHI'], ['3','GHI'], ['3','GHI'], ['3','GHI'], ['3','GHI'],
	 * ['3','GHI'], ['3','GHI'], ['3','GHI'], ['3','GHI'], ['3','GHI'],
	 * ['3','GHI'], ['3','GHI'], ['3','GHI'], ['3','GHI'], ['3','GHI'],
	 * ['3','GHI'], ['3','GHI']
	 * 
	 * 
	 *  ];
	 * 
	 */
	var $table = $('#productListTable');
	// execute when inside the table
	if($table.length){
		// console.log('Inside the table'
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
						str += '<a href="'
								+ window.contextRoot
								+ '/show/'
								+ data
								+ '/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';

						
						if(userRole == 'ADMIN') {
						
							str += '<a href="'
								+ window.contextRoot
								+ '/manage/'
								+ data
								+ '/product" class="btn btn-warning"><span class="glyphicon glyphicon-pencil"></span></a>';
					
							
						}
						else{
							if (row.quantity < 1) {
								str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
							} else {

								str += '<a href="'
										+ window.contextRoot
										+ '/cart/add/'
										+ data
										+ '/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
							}
						}
						
						return str;
						
					}
				}
			]
			
			
		});
		
		
	}
	
	// Dismissing the alert after 3 seconds !!
	
	/*
	 * window.setTimeout(function() { $(".alert").fadeTo(500, 0).slideUp(500,
	 * function(){ $(this).remove(); }); }, 4000);
	 */

	var $table = $('.alert');
	if($table.length){
	setTimeout(function() {
		$table.fadeOut('slow');
	}, 2000);
	}
	// Product Activation and Deactivation
	// --------------------------------------
	
	
	
	
	//-------------------------------------
	//Data table for admin
	//-------------------------------------
	
	
	var $adminProductsTable = $('#adminProductsTable');
	// execute when inside the table
	if($adminProductsTable.length){
		// console.log('Inside the table'
		var jsonUrl = window.contextRoot + '/json/data/admin/all/products';
		$adminProductsTable.DataTable({
			lengthMenu:[[10,30,50,-1],['10 Records','30 Records','50 Records','ALL Records']],
			pageLength:30,
			
			ajax:{
				dataSrc:'',
				url:jsonUrl
				},
			
			columns:[
				{
					data:'id'
				},
				{
					data:'code',
					mRender:function(data,type,row){
						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="adminDataTableImage"/>';
					}
				},
				{
					data: 'name'
				},
				{
					data: 'brand'
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
					data: 'unitprice'
				},
				{
					data: 'active',
					bSortable:false,
					mRender:function(data,type,row){
						var str ='';
						str +='<label class="switch">';
						if(data){
							str +='<input type="checkbox" checked="checked" value="'+row.id+'" />';
						
						}
						else{
							str +='<input type="checkbox" value="'+row.id+'" />';
						}
												
						str +='<div class="slider"></div></label>';
						
						return str;
					}
		
				},
				{
					data:'id',
					bSortable:false,
					mRender:function(data,type,row){
						var str='';
						str +='<a href="'+window.contextRoot+'/manage/'+data+'/product" class="btn btn-warning">';
						str +='<span class="glyphicon glyphicon-pencil"></span>';
						
						return str;
						
					}
				},
		],
		initComplete:function(){
			var api = this.api();
			api.$('.switch input[type="checkbox"]').on('change',function(){
				var checkbox = $(this);
				var checked = checkbox.prop('checked');
				var dMsg = (checked)? 'You Want to Activate the product?':
									  'You Want to Deactivate the product?';
				var value = checkbox.prop('value');
				bootbox.confirm({
					size:'medium',
					title:'Product Activation & Deactivation',
					message : dMsg,
					callback : function(confirmed){
						if(confirmed){
							console.log(value);
							
							var activationUrl = window.contextRoot + '/manage/product/' + value +'/activation';
							
							$.post(activationUrl,function(data){
								bootbox.alert({
									size:'medium',
									title:'Information',
									message :data
								});
							});
						}
						
						else{
							checkbox.prop('checked',!checked);
						}
					}								
			});
			
			});
		}
				
				
		});
		
		
	}
	
	//-----------------------------------------------------------------------
	//-----Jquery Validation for categoryForm
	
	/*var $categoryForm = $('#categoryForm');
	
	if($categoryForm.lenght){
		console.log('inside category form');
		$categoryForm.validate({
			rules :{
				
				name :{
					
					required:true,
					minlength:2
				},
				
				description:{
					required :true
				}
			},
			
			message :{
				
				name :{
					required : 'Please Enter the Category Name!',
					minlength : 'The category name should not be less than 2 charateries!'
				},
				
				description:{
					required :'Please Enter the description of the category !'
				}
				
				
			},
			
			errorElement:'em',
			errorPlacement: function(error,element){
				//add the class pof help block
				error.addClass('help-block');
				//add the error element after the input element
				error.insertAfter(element);
			}
			
			
		});
	}
	*/
	
	
	var $categoryForm = $('#categoryForm');
	
	if($categoryForm.length){
		$categoryForm.validate({
			rules :{
				
				name :{
					
					required:true,
					minlength:2
				},
				
				description:{
					required :true
				}
			},
			
			message :{
				
				name :{
					required : 'Please Enter the Category Name!',
					minlength : 'The category name should not be less than 2 charateries!'
				},
				
				description:{
					required :'Please Enter the description of the category !'
				}
				
				
			},
			
			errorElement:'em',
			errorPlacement: function(error,element){
				//add the class pof help block
				error.addClass('help-block');
				//add the error element after the input element
				error.insertAfter(element);
			}
			
			
		});
	}
	
	
	//---------------Validation for Login Form 
	
	
	
	var $loginForm = $('#loginForm');
	
	if($loginForm.length){
		$loginForm.validate({
			rules :{
				
				username :{
					
					required:true,
					email : true
				},
				
				password:{
					required :true
				}
			},
			
			message :{
				
				username :{
					required : 'Please enter the username!',
					email : 'Please enter valid email address!'
					},
				
					password:{
					required :'Please enter the password!'
				}
				
				
			},
			
			errorElement:'em',
			errorPlacement: function(error,element){
				//add the class pof help block
				error.addClass('help-block');
				//add the error element after the input element
				error.insertAfter(element);
			}
			
			
		});
	}

	
	
	
	//TO takle csrf problem
	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');
	
	
	if(token.length >0 && header.length >0){
		
		$(document).ajaxSend(function(e, xhr,options){
			xhr.setRequestHeader(header,token);
		})
		
		
	}
	
	
	
});	
