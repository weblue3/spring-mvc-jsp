package kr.co.softcampus.controller;

import kr.co.softcampus.beans.UserDataBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by 김홍준
 * Date: 2020-12-21
 * Time: 오후 1:56
 */
@Controller
@Slf4j
public class TestController {

    @GetMapping("test1")
    public String test1(@ModelAttribute UserDataBean bean){

        bean.setUser_id("abcd");
        bean.setUser_name("홍길동 ");
        bean.setUser_pw("1234");
        bean.setUser_postcode("12345");
        bean.setUser_address1("주소 1번 입니다.");
        bean.setUser_address2("주소 2번 입니다.");

        return "test1";
    }

    @GetMapping("test2")
    public String test2(@ModelAttribute UserDataBean bean){

        bean.setUser_id("abcd");
        bean.setUser_name("홍길동 ");
        bean.setUser_pw("1234");
        bean.setUser_postcode("12345");
        bean.setUser_address1("주소 1번 입니다.");
        bean.setUser_address2("주소 2번 입니다.");

        return "test2";
    }

    @GetMapping("test3")
    public String test3(@ModelAttribute("testBean") UserDataBean bean){

        bean.setUser_id("abcd");
        bean.setUser_name("홍길동 ");
        bean.setUser_pw("1234");
        bean.setUser_postcode("12345");
        bean.setUser_address1("주소 1번 입니다.");
        bean.setUser_address2("주소 2번 입니다.");

        return "test3";
    }

    @GetMapping("test4")
    public String test4(Model model){

        UserDataBean bean = new UserDataBean();

        bean.setUser_id("abcd");
        bean.setUser_name("홍길동 ");
        bean.setUser_pw("1234");
        bean.setUser_postcode("12345");
        bean.setUser_address1("주소 1번 입니다.");
        bean.setUser_address2("주소 2번 입니다.");

        model.addAttribute("test_user2", bean);

        return "test4";
    }
}