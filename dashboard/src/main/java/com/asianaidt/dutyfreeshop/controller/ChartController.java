package com.asianaidt.dutyfreeshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import com.asianaidt.dutyfreeshop.dto.ChartDTO;
import com.asianaidt.dutyfreeshop.service.ChartService;

@RequestMapping("/admin/chart")
@Controller
public class ChartController {

	@Autowired
	ChartService service;
	
	@GetMapping("/chartForm")
	public ModelAndView getCountByMonth() throws Exception {
		
		List<ChartDTO> data = service.getAmountByMonth();
		List<ChartDTO> return_data = new ArrayList<ChartDTO>();
		
		int index=0;
		System.out.println("test:"+data.size());
		if(data.size()!=0 ) {
			for(int i=1;i<13;i++) {
				if( (index<data.size())&&i==data.get(index).getMonth()) {
					System.out.println( "i="+i+" index="+index + " data month:"+data.get(index).getMonth());
					return_data.add(data.get(index));
					if (index<data.size()-1) {
						index++;
					}
				}
				else {
					System.out.println( ">>>i="+i+" index="+index + " data month:"+data.get(index).getMonth());
					ChartDTO tmpdto = new ChartDTO();
					tmpdto.setMonth(i);
					tmpdto.setSum(0);
					return_data.add(tmpdto);
				}
			}
		}

		List<ChartDTO> pieData = service.getAmountByCategory();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("chart");
		mav.addObject("datas", return_data);
		mav.addObject("datas_pie", pieData);
		return mav;
	}
	
}
