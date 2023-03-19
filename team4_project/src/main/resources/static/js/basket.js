
//
function del(num){
	//특정 사이트 요청
	event.preventDefault();
	location.href = "delete?num="+num;
}
function add(basket_id,cur_num){
	//특정 사이트 요청
	event.preventDefault();
	location.href = "updateItem?basketId="+basket_id+"&"+"amount="+(cur_num+1);
	
}
function subtract(basket_id,cur_num){
	//특정 사이트 요청
	event.preventDefault();
	if(cur_num>1){
		location.href = "updateItem?basketId="+basket_id+"&"+"amount="+(cur_num-1);
	}
	else{
		del(basket_id);
	}
}