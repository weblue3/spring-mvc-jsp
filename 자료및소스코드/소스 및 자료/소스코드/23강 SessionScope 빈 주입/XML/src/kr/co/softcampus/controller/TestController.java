package kr.co.softcampus.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.softcampus.beans.DataBean1;
import kr.co.softcampus.beans.DataBean2;
import kr.co.softcampus.beans.DataBean3;
import kr.co.softcampus.beans.DataBean4;

@Controller
public class TestController {
	@Autowired
	@Lazy
	DataBean1 sessionBean1;
	
	@Resource(name = "sessionBean2")
	@Lazy
	DataBean2 sessionBean2;
	
	@Autowired
	DataBean3 sessionBean3;
	
	@Resource(name = "sessionBean4")
	DataBean4 sessionBean4;
	
	@GetMapping("/test1")
	public String test1() {
		
		sessionBean1.setData1("data1");
		sessionBean1.setData2("data2");
		
		sessionBean2.setData3("data3");
		sessionBean2.setData4("data4");
		
		sessionBean3.setData5("data5");
		sessionBean3.setData6("data6");
		
		sessionBean4.setData7("data7");
		sessionBean4.setData8("data8");
		
		return "test1";
	}
	
	@GetMapping("/result1")
	public String result1(Model model) {
		
		System.out.printf("sessionBean1.data1 : %s\n", sessionBean1.getData1());
		System.out.printf("sessionBean1.data2 : %s\n", sessionBean1.getData2());
		
		System.out.printf("sessionBean2.data3 : %s\n", sessionBean2.getData3());
		System.out.printf("sessionBean2.data4 : %s\n", sessionBean2.getData4());
		
		System.out.printf("sessionBean3.data5 : %s\n", sessionBean3.getData5());
		System.out.printf("sessionBean3.data6 : %s\n", sessionBean3.getData6());
		
		System.out.printf("sessionBean4.data7 : %s\n", sessionBean4.getData7());
		System.out.printf("sessionBean4.data8 : %s\n", sessionBean4.getData8());
		
		model.addAttribute("sessionBean1", sessionBean1);
		model.addAttribute("sessionBean3", sessionBean3);
		model.addAttribute("sessionBean4", sessionBean4);
		
		return "result1";
	}
}








