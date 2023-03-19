package com.asianaidt.dutyfreeshop.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asianaidt.dutyfreeshop.dto.MyPageMemberDTO;
import com.asianaidt.dutyfreeshop.dto.PaymentDTO;
import com.asianaidt.dutyfreeshop.dto.PaymentDetailDTO;
import com.asianaidt.dutyfreeshop.dto.PaymentListDTO;
import com.asianaidt.dutyfreeshop.dto.TotalPaymentDTO;
import com.asianaidt.dutyfreeshop.mapper.PaymentMapper;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	PaymentMapper mapper;

	@Override
	public ArrayList<PaymentDTO> getPayment(int memberId) throws Exception {
		return mapper.getPayment(memberId);
	}

	@Override
	public TotalPaymentDTO getTotalPayment(int memberId) throws Exception {
		return mapper.getTotalPayment(memberId);
	}

	@Override
	public ArrayList<PaymentListDTO> getPaymentList(int memberId) throws Exception {
		return mapper.getPaymentList(memberId);
	}

	@Override
	public ArrayList<PaymentDetailDTO> getPaymentDetail(int memberId, int paymentId) throws Exception {
		return mapper.getPaymentDetail(memberId, paymentId);
	}

	@Override
	public MyPageMemberDTO getMyPageMember(int memberId) throws Exception {
		return mapper.getMyPageMember(memberId);
	}

}
