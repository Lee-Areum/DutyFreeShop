package com.asianaidt.dutyfreeshop.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.asianaidt.dutyfreeshop.dto.MyPageMemberDTO;
import com.asianaidt.dutyfreeshop.dto.PaymentDTO;
import com.asianaidt.dutyfreeshop.dto.PaymentDetailDTO;
import com.asianaidt.dutyfreeshop.dto.PaymentListDTO;
import com.asianaidt.dutyfreeshop.dto.TotalPaymentDTO;

@Mapper
public interface PaymentMapper {
	
	public ArrayList<PaymentDTO> getPayment(int memberId) throws Exception;
	public TotalPaymentDTO getTotalPayment(int memberId) throws Exception;
	public ArrayList<PaymentListDTO> getPaymentList(int memberId) throws Exception;
	public ArrayList<PaymentDetailDTO> getPaymentDetail(int memberId, int paymentId) throws Exception;
	public MyPageMemberDTO getMyPageMember(int memberId) throws Exception;
	
}
