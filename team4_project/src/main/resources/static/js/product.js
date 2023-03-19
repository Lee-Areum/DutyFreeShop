/**
 * 
 */

$(document).ready(function(){
	 var amount = $("#cntProduct");
	 $('#cntUp').click(function(){
		 var count = parseInt(amount.val().trim());
		 amount.val(count+1);
	 });
	 $('#cntDown').click(function(){
		 var count = parseInt(amount.val().trim());
		 if(count!=1){
			 amount.val(count-1);
			 return;
		 }
	 }); 
})