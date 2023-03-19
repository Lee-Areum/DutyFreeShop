package com.asianaidt.dutyfreeshop.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asianaidt.dutyfreeshop.dto.PagingDTO;
import com.asianaidt.dutyfreeshop.dto.ProductBasketDTO;
import com.asianaidt.dutyfreeshop.dto.ProductListDTO;
import com.asianaidt.dutyfreeshop.mapper.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductMapper mapper;

	@Autowired
	SqlSessionTemplate session;

	@Transactional
	@Override
	public PagingDTO getProductList(int categoryId, int curPage) throws Exception {
		PagingDTO pagingDto = new PagingDTO();

		// totalPage 저장
		int perPage = pagingDto.getPerPage(); // 12
		int totalRecord = mapper.pagingProductCount(categoryId);
		int totalPage = totalRecord / perPage;
		if (totalRecord % perPage != 0)
			totalPage++;

		// List
		int offset = (curPage - 1) * perPage;
		List<ProductListDTO> list = session.selectList("com.asianaidt.dutyfreeshop.mapper.ProductMapper.getProductList",
				categoryId, new RowBounds(offset, perPage));

		pagingDto.setCurPage(curPage);
		pagingDto.setProductListDtoList(list);
		pagingDto.setTotalPage(totalPage);

		return pagingDto;
	}

	@Transactional
	@Override
	public ProductListDTO getProductOne(int productId) throws Exception {
		return mapper.getProductOne(productId);
	}

	@Transactional
	@Override
	public int productToBasket(ProductBasketDTO productBasketDto) throws Exception {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		int memberIdx = Integer.parseInt(username);
		productBasketDto.setMemberId(memberIdx);
//		productBasketDto.setMemberId(2);

		return mapper.productToBasket(productBasketDto);
	}

	@Transactional
	@Override
	public int updateProductBasket(ProductBasketDTO productBasketDto) throws Exception {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		int memberIdx = Integer.parseInt(username);
		productBasketDto.setMemberId(memberIdx);

		return mapper.updateProductBasket(productBasketDto);
	}

	@Transactional
	@Override
	public int getBasket(ProductBasketDTO productBasketDto) throws Exception {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		int memberIdx = Integer.parseInt(username);
		productBasketDto.setMemberId(memberIdx);

		int n = mapper.getBasket(productBasketDto);

		System.out.println(productBasketDto);
		return n;
	}
}
