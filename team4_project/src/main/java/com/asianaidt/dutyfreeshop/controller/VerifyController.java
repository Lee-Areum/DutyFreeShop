package com.asianaidt.dutyfreeshop.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/verifyIamport")
public class VerifyController { //서버 검증 담당
	//Iamport 결제 검증 컨트롤러
	private final IamportClient iamportClient;
	
	//생성자를 통해 REST API와 REST API secret 입력
	public VerifyController() {
		this.iamportClient = new IamportClient("3710331833912842", "285e8af591e20ca1d80a1ceac6537d13f6b17cb53f6a6179f9940e2113471057f7c947b3494e5794");
	}
	
	//프론트에서 받은 PG사 결괏값을 통해 아임포트 토큰 발행
	@PostMapping("/{imp_uid}") //imp_uid : 거래 고유번호
	public IamportResponse<Payment> paymentByImpUid(@PathVariable String imp_uid) throws IamportResponseException, IOException{
		System.out.println("paymentByImpUid 진입");
        return iamportClient.paymentByImpUid(imp_uid);
    }
}
