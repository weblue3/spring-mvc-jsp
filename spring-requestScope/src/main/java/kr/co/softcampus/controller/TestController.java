package kr.co.softcampus.controller;

import kr.co.softcampus.beans.DataBean1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Created by 김홍준
 * Date: 2020-12-21
 * Time: 오후 11:11
 */
@Controller
@Slf4j
public class TestController {

    @GetMapping("/test1")
    public String test1(HttpServletRequest request){

        request.setAttribute("data1", "문자열1");

        return "forward:/result1";
    }

    @GetMapping("/result1")
    public String result1(HttpServletRequest request){
        Optional<String> optional = Optional.ofNullable((String)request.getAttribute("data1"));
        String data1 = optional.orElse(null);
        log.info("data1 : {}", data1);
        return "result1";
    }

    @GetMapping("/test2")
    public String test2(Model model){
        model.addAttribute("data2", "문자열2");
        return "forward:/result2";
    }

    @GetMapping("/result2")
    public String result2(HttpServletRequest request){
        String data2 = (String)request.getAttribute("data2");
        log.info("data2 : {}", data2);
        return "result2";
    }

    @GetMapping("/test3")
    public ModelAndView test3(ModelAndView mv){
        mv.addObject("data3", "문자열3");
        mv.setViewName("forward:/result3");
        return mv;
    }

    @GetMapping("/result3")
    public String result3(HttpServletRequest request){
        String data3 = (String)request.getAttribute("data3");
        log.info("data3 : {}", data3);
        return "result3";
    }

    @GetMapping("/test4")
    public String test4(Model model){

        DataBean1 bean1 = new DataBean1();

        bean1.setData1("문자열3");
        bean1.setData2("문자열4");

        model.addAttribute("bean1", bean1);

        return "forward:/result4";
    }

    @GetMapping("/result4")
    public String result4(HttpServletRequest request){
        Optional<DataBean1> optional = Optional.ofNullable((DataBean1)request.getAttribute("bean1"));
        DataBean1 bean1 = optional.orElse(new DataBean1());
        log.info("bean1 : {}", bean1);
        return "result4";
    }

    @GetMapping("/test5")
    public String test5(@ModelAttribute("bean1") DataBean1 bean1){
        bean1.setData1("문자열6");
        bean1.setData2("문자열7");
        return "forward:/result5";
    }

    @GetMapping("/result5")
    public String result5(HttpServletRequest request){
        Optional<DataBean1> optional = Optional.ofNullable((DataBean1)request.getAttribute("bean1"));
        DataBean1 bean1 = optional.orElse(new DataBean1());
        log.info("bean1 : {}", bean1);
        return "result5";
    }

    @GetMapping("/test6")
    public String test6(@ModelAttribute("bean1") DataBean1 bean1){
        bean1.setData1("문자열8");
        bean1.setData2("문자열9");
        return "forward:/result6";
    }

    @GetMapping("/result6")
    public String result6(@ModelAttribute("bean1") DataBean1 bean1){
        log.info("bean1 : {}", bean1);
        return "result6";
    }
}
