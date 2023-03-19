package com.asianaidt.dutyfreeshop.controller;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.asianaidt.dutyfreeshop.dto.AirlineDTO;
import com.asianaidt.dutyfreeshop.dto.MemberDTO;
import com.asianaidt.dutyfreeshop.dto.RegionDTO;
import com.asianaidt.dutyfreeshop.service.OrderService;

//@CrossOrigin(originPatterns = "http://localhost:8090")
@Controller
public class OrderController {
	@Autowired
	OrderService service;
	
	@ResponseBody
	@PostMapping("/airline/searchAirline") //항공권 검색 : ajax
	public List<AirlineDTO> searchAirline(AirlineDTO m) throws Exception {
		String key = "jzJGDFzTxx8Jh5%2FrxhLaevoAGSZ4YeqDvvR8Hm1fPCezwM2iG3KOuuTU8TrkF%2F0Zj4qGkpvvf6aSESVSyCMBMg%3D%3D";
		String requestURL = "http://openapi.airport.co.kr/service/rest/FlightScheduleList/getIflightScheduleList?";
		StringBuffer sb = new StringBuffer(requestURL);
		
		sb.append("schDate="+m.getDepartTime());
		sb.append("&ServiceKey="+key);
		sb.append("&schDeptCityCode="+m.getDeparture());
		sb.append("&schArrvCityCode="+m.getArrival());
		
		StringBuffer sb2 = new StringBuffer(sb);
		sb2.append("&pageNo=1");
		
		URL url = new URL(sb2.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Content-Type", "application/xml");
		conn.connect();
		
		SAXBuilder builder = new SAXBuilder();
		Document document = builder.build(conn.getInputStream());
		
		Element root = document.getRootElement();
		Element body = root.getChild("body");
		Element items = body.getChild("items");
		int numOfRow = Integer.parseInt(body.getChildText("numOfRows"));
		int totalCount = Integer.parseInt(body.getChildText("totalCount"));
		int totalPage = (totalCount + (numOfRow-1))/numOfRow;

		Set<AirlineDTO> set = new HashSet<AirlineDTO>();
		for(int i = 1;i<=totalPage;i++) {
			sb2 = new StringBuffer(sb);
			sb2.append("&pageNo="+i);
			
			url = new URL(sb2.toString());
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Content-Type", "application/xml");
			conn.connect();
			
			builder = new SAXBuilder();
			document = builder.build(conn.getInputStream());
			
			root = document.getRootElement();
			body = root.getChild("body");
			items = body.getChild("items");
			List<Element> item_list = items.getChildren("item");
			for(Element item : item_list) {
				String airlineName = item.getChildText("internationalNum");
				if(airlineName.contains("OZ")) {
					String time = item.getChildText("internationalTime");
					String departTime = String.format("%4s-%2s-%2s %2s:%2s", m.getDepartTime().substring(0,4),m.getDepartTime().substring(4,6),m.getDepartTime().substring(6),time.substring(0,2),time.substring(2));
					System.out.println("search : "+airlineName+" , "+departTime);			
					AirlineDTO dto = new AirlineDTO(0,airlineName.trim(),m.getDeparture().trim(),m.getArrival().trim(),departTime.trim());
					set.add(dto);
				}
			}
		}
		List<AirlineDTO> list = new ArrayList<AirlineDTO>(set);
		return list;
	}

	@GetMapping("/order/orderForm")
	public String orderForm(Model m) { //주문 페이지 보여주는 변수
		int memberIdx = getUser();
		List<RegionDTO> list = null;
		List<Map<String, Object>> basket = null;
		MemberDTO member = null;
		float exchange = 0.0f;
		try {
			list = service.getRegion();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("memberIdx",memberIdx);
			map.put("exchange","WON");
			basket = service.getBasketByUser(map);
			member = service.getUserData(memberIdx);
			exchange = service.getExchange("WON");
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("regions",list);
		m.addAttribute("products",basket);
		m.addAttribute("member",member);
		m.addAttribute("exchange",exchange);
		return "order";
	}
	

	@GetMapping("/order/result")
	public String paymentSuccess() { //결제 성공 화면
		return "result";
	}

	@GetMapping("/order/error")
	public String paymentFail() { //결제 실패 화면
		return "error";
	}
	
	@Transactional
	@ResponseBody
	@PostMapping("/order/payment")
	public String startPayment(@RequestParam Map<String, Object> map) {
		System.out.println("map : "+map.toString());
		List<Map<String, Object>> basket = null;
		try {
			int memberIdx = getUser();
			Map<String,Object> user = new HashMap<String,Object>();
			user.put("memberIdx",memberIdx);
			user.put("exchange","WON");
			
			basket = service.getBasketByUser(user);
			
			map.put("memberId",memberIdx);
			map.put("priceUsd",basket.get(0).get("price_sum"));
			map.put("priceKrw",basket.get(0).get("price_sum_nation"));
			int n = service.insertPayment(map);
			System.out.println("n : "+n);
			float exchange = service.getExchange("WON");
			System.out.println("n : "+n);
			for(Map<String,Object> m : basket) {
				m.put("paymentId",n);
				m.put("exchange",exchange);
				service.insertOrder(m);
				service.deleteBasket((int)m.get("basket_id"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "result";
	}
	
	public int getUser() {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		System.out.println(userDetails.toString());
		String username = userDetails.getUsername();
		int memberIdx = Integer.parseInt(username);
		return memberIdx;
	}
}