var checkedRadio = -1;
var phone = false;
var passport = false;
const IMP = window.IMP;
IMP.init('imp08800398');

$( document ).ready( function() {
	//airline 
	$("#startDate").attr("min",getNowDate());
	$("#birthdate").attr("max",getNowDate());
	
	$("#btnSearchAirline").on("click",searchAirlineAPI);
	
	//order
	$("#btnStartPayment").on("click",checkInput);
	
	// 동의
	$("#memberInfoConfirm").on("click",function(){
		if(!$(this).is(":checked")){
			$("#orderConfirm").prop("checked",false);
		}else if($("#memberConfirm").is(":checked")){
			$("#orderConfirm").prop("checked",true);
		}
	});
	$("#memberConfirm").on("click",function(){
		if(!$(this).is(":checked")){
			$("#orderConfirm").prop("checked",false);
		}else if($("#memberInfoConfirm").is(":checked")){
			$("#orderConfirm").prop("checked",true);
		}
	});
	
	$("#orderConfirm").on("click",function(){
		if($(this).is(":checked")){
			$("#memberInfoConfirm").prop("checked",true);
			$("#memberConfirm").prop("checked",true);
		}else{
			$("#memberInfoConfirm").prop("checked",false);
			$("#memberConfirm").prop("checked",false);
		}
	});

	//전화번호
	$("#phone1").on("change keyup paste",function(){
		var phone = $(this).val();
		var passExp = RegExp("^(.*)[0-9]{3}(.*)$");
		if(passExp.test(phone) && phone.length == 3){
			$(this).removeClass('is-invalid');
			$("#phone2").focus();
		}else{
			$(this).addClass('is-invalid');
		}
	});
	$("#phone2").on("change keyup paste",function(){
		var phone = $(this).val();
		var passExp = RegExp("^(.*)[0-9]{4}(.*)$");
		if(passExp.test(phone) && phone.length == 4){
			$(this).removeClass('is-invalid');
			$("#phone3").focus();
		}else{
			$(this).addClass('is-invalid');
		}
	});
	$("#phone3").on("change keyup paste",function(){
		var phone = $(this).val();
		var passExp = RegExp("^(.*)[0-9]{4}(.*)$");
		if(passExp.test(phone) && phone.length == 4){
			phone = true;
			$(this).removeClass('is-invalid');
			console.log("phone : "+phone);
			$("#nation").focus();
		}else{
			$(this).addClass('is-invalid');
		}
	});
	$("#nation").on("change",function(){
		if($("#nation").val() != '대한민국'){
			$("#passport").removeClass('is-invalid')
		}else if($("#passport").val().length <= 0){
			$("#passport").addClass('is-invalid')
		}
		$("#passport").focus();
	});

	//여권
	$("#passport").on("change keyup paste",function(){
		if($("#nation").val() == '대한민국'){
			var pass = $(this).val();
			var passExp = RegExp("^(.*)[a-zA-Z]{1}[0-9a-zA-Z]{1}[0-9]{7}(.*)$");
			if(passExp.test(pass) && pass.length == 9){
				passport = true;
				$(this).removeClass('is-invalid');
				$(this).addClass('is-valid');
				$("#birthdate").focus();
			}else{
				passport = false;
				$(this).removeClass('is-valid');
				$(this).addClass('is-invalid');
			}
		}else{
			passport = true;
		}
	});
})



function searchAirlineAPI(){ //항공 api 사용
	var departure = $("#departure").val();
	var arrival = $("#arrival").val();
	const dateStr = $("#startDate").val().split('-');
	if(dateStr.length < 3){
		alert("출발일자를 입력해주세요");
		("#startDate").focus();
	}
	var pickedDate = dateStr[0]+dateStr[1]+dateStr[2]; 
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
			url: "/app/airline/searchAirline",
			data: dto,
			type : "post",
			dataType:"json",
			async: true,
			success: airlineSuccess,
			error: function(e){
				alert("알수없는 오류가 발생했습니다.")
				console.log(e);
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
	console.log("phone : "+phone);
	if($("#phone1").hasClass("is-invalid") || $("#phone2").hasClass("is-invalid") || $("#phone3").hasClass("is-invalid") 
			|| $("#phone1").val().length < 3 || $("#phone2").val().length < 4 || $("#phone3").val().length < 4){
		alert("전화번호를 입력해주세요");
		$('#phone1').focus();
		return;
	}
	var nation = $("#nation").val();
	if(nation < 0){
		alert("국적을 선택해주세요");
		$('#nation').focus();
		return;
	}
	if(!passport){
		alert("여권정보를 입력해주세요");
		$('#passport').focus();
		return;
	}
	let birthdate = $("#birthdate").val();
	if(birthdate.length < 6){
		alert("생년월일를 입력해주세요");
		$('#birthdate').focus();
		return;
	}
	
	if(!$("#orderConfirm").is(":checked") || !$("#memberInfoConfirm").is(":checked")){
		alert("정보 활용 동의가 필요합니다.");
		$('#memberInfoConfirm').focus();
		return;
	}
	requestPay();
}

/** 결제 **/
function requestPay() {
    // IMP.request_pay(param, callback) 결제창 호출
	console.log("amount : "+$('#totalPriceWon').text());
	console.log("buyer_email : "+$('#email').val());
	console.log("buyer_name : "+$('#name').val());
    IMP.request_pay({ // param
        pg: "INIpayTest",
        pay_method: "card", //구매 방법
        merchant_uid: 'duty_' + new Date().getTime(), //구매 id
        name: "면세점 구매",
        amount: $('#totalPriceWon').text(), //100, //TODO: 가격
        buyer_email: $('#email').val(),
        buyer_name: $('#name').val(),

    }, function (rsp) { // callback
        /** 결제 검증 **/
    	console.log("검증이 시작되었습니다.");
        $.ajax({
            type: 'POST',
            url: "/app/verifyIamport/"+rsp.imp_uid
        }).done(function(result){
            // rsp.paid_amount와 result.response.amount(서버 검증) 비교 후 로직 실행
            if(rsp.paid_amount === result.response.amount){
            	console.log(result);
                paySuccess(rsp.imp_uid,result.response);
            } else{
                alert("결제에 실패했습니다."+"에러코드 : "+rsp.error_code+"에러 메시지 : "+rsp.error_message);

            }
        })
    });
};

function paySuccess(impUid, response){
	var payment = {
			orderFirstName : $('#englishFirst').val(),
			orderLastName :  $('#englishLast').val(),
			orderName :  response.buyerName,
			phoneNumber :  $('#phone1').val()+$('#phone2').val()+$('#phone3').val(),
			passportNumber :  $('#passport').val(),
			birthDate :  $('#birthdate').val(),
			email :  response.buyerEmail,
			nation :  $('#nation').val(),
			paymentDate :  getNowDate(),
			paymentMethod :  response.payMethod,
			uniquePaymentNumber : impUid,
			status : response.status
		}
	console.log(payment);
	$.ajax({
		url: "/app/order/payment",
		data: payment,
		type : "post",
		async: true,
		success: function(){
			alert("결제가 완료되었습니다.");
			location.href = `/app`
		},
		error: function(e){
			alert("결제 중 오류가 발생했습니다.")
			location.href = `error`
			console.log(e);
		}
	})
}


