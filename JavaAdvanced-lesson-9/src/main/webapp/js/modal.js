$("button.myBtn.modalBtnNo").click(function(){
	window.location.reload();
	})
	
$("button.myBtn.modalBtnYes").click(function(){
	let product_id = jQuery(this).attr("product-id");
	$.post("bucket", {'product_id': product_id}, function(data){
		if(data == 'success'){
			alert("Product added to bucket");
			window.location.reload();
		}
	})
})