var memberInfoConfirm = false;
var memberConfirm = false;
var orderConfirm = false;
var checkedRadio = -1;
$( document ).ready( function() {
	//airline 
	$("#startDate").attr("min",getNowDate());
	
	$("#btnSearchAirline").on("click",searchAirline);
	
	//order
	$("#btnStartPayment").on("click",checkInput);
	$("#memberInfoConfirm").on("click",function(){
		memberInfoConfirm = !memberInfoConfirm;
		console.log(`memberInfoConfirm : ${memberInfoConfirm}`);
	});
	$("#memberConfirm").on("click",function(){
		memberConfirm = !memberConfirm;
		console.log(`memberConfirm : ${memberConfirm}`);
	});
	$("#orderConfirm").on("click",function(){
		orderConfirm = !orderConfirm;
		console.log(`orderConfirm : ${orderConfirm}`);
	});
})

//airline
function searchAirline(){ //TODO : 시간 확인 필요
	let departure = $("#departure").val();
	var arrival = $("#arrival").val();
	let pickedDate = $("#startDate").val();
	console.log(departure,arrival,pickedDate);

	if(departure < 0 || arrival < 0 || pickedDate.length < 6){
		alert("출발지와 도착지, 출발일자를 입력하세요");
	}else{
		var dto = {
			airlineId : 0,
			airlineName : "",
			departure : departure,
			arrival : arrival,
			departTime :pickedDate
		}
		$.ajax({
			url: "http://localhost:8090/app/airline/searchAirline",
			data: dto,
			type : "post",
			dataType:"json",
			async: true,
			success: airlineSuccess,
			error: function(){
				alert("알수없는 오류가 발생했습니다.")
			}
		})
	}
}

function airlineSuccess(resp){
	$("#airlines").empty();
	if(resp.length == 0){
		$("#airlines").append("해당하는 항공편이 없습니다.");
	}
	resp.forEach(element => {
		$("#airlines").append("<div class='form-check'>"
					 + `<input class='form-check-input' type='radio' name="airline" value=${element.airlineId} id='airline${element.airlineId}'>`
					+	`<label class='form-check-label' for=${element.airlineId}>`
					+ `${element.departTime} / ${element.airlineName}`	
					+  "</label>"
				+	"</div>");

		$('.form-check-input').on("click",function(){
			checkedRadio = $(this).val();
			console.log(checkedRadio);
		});
	})
}

function getNowDate(){
	var date = new Date();
	var year = date.getFullYear();
	var month = ("0"+(1+date.getMonth())).slice(-2);
	var day = ("0"+date.getDate()).slice(-2);
	return `${year}-${month}-${day}`;
}

//order
function checkInput(){
	if(checkedRadio < 0){
		alert("항공편을 선택해주세요");
		$('#btnSearchAirline').focus();
		return;
	}
	var nation = $("#nation").val();
	//TODO : 주문자 정보 확인
	if(nation < 0){
		alert("국적을 선택해주세요");
		$('#nation').focus();
		return;
	}
	if(!memberInfoConfirm || !memberConfirm || !orderConfirm){
		alert("정보 활용 동의가 필요합니다.");
		$('#memberInfoConfirm').focus();
		return;
	}
	//TODO : 결제 DB에 넣기
	alert("결제가 완료되었습니다.");
}


