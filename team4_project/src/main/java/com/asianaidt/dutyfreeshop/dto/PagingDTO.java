package com.asianaidt.dutyfreeshop.dto;

import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
   
@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("PagingDTO")
public class PagingDTO {
	List<ProductListDTO> productListDtoList;	
	int curPage; // 현재 페이지 번호
	int perPage = 12; //페이지당 보여줄 갯수
	int totalPage; //화면 하단에 출력할 페이지 사이즈 totalRecord/perPage
}
