package com.navershop.www;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.mysql.cj.xdevapi.JsonArray;

@Controller
public class web_controller {
	PrintWriter pw = null;
	
	//Ajax 통신 CORS방식
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/ajaxok.do")
	public String ajaxok(@RequestParam(value="alldata") List<String> alldata,
			HttpServletResponse res) throws Exception{
		this.pw = res.getWriter();
		JSONObject jo = new JSONObject();
		jo.put("result", "ok");
		this.pw.print(jo);
		//System.out.println(alldata);
		this.pw.close();
		return null;
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/ajaxok2.do")
	public String ajaxok2(@RequestBody String all_data, HttpServletResponse res) throws Exception {
		System.out.println(all_data);
		JSONObject jo = new JSONObject(all_data);
		//JSONArray ja = (JSONArray)jo.get("alldata");
		JSONArray ja = jo.getJSONArray("alldata");
		System.out.println(ja.get(0));
		
		JSONObject result = new JSONObject();
		result.put("result", "ok");
		
		this.pw = res.getWriter();
		this.pw.print(result);
		this.pw.close();
		return null;
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/ajaxok3.do")
	public String ajaxok3(@RequestBody String data, HttpServletResponse res) throws Exception {
		System.out.println(data);
		JSONArray ja = new JSONArray(data);
		JSONArray innerja = ja.getJSONArray(0);
		JSONArray innerja2 = ja.getJSONArray(1);
		System.out.println(innerja.get(0));
		
		this.pw = res.getWriter();
		this.pw.print("ok");
		this.pw.close();
		return null;
	}
	
	//HttpSession : interface를 활용하여 세션을 빠르게 구현하는 방식
	@PostMapping("/loginok.do")
	public String loginok (@RequestParam(value="", required=false) String mid, HttpSession session) {
		if(mid!=null||mid!="") {
			session.setAttribute("mid", mid);
			session.setMaxInactiveInterval(1800); //30분
		}
		return null;
	}
	/*
	@PostMapping("/loginok.do")
	public String loginok (String mid, HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.setAttribute("mid", mid);
		session.setMaxInactiveInterval(1800); //30분
		System.out.println(mid);
		return null;
	}
	*/
	
	@GetMapping("/restapi.do")
	public String restapi(@SessionAttribute(name="mid", required=false) String mid) throws Exception{
		if(mid==null) {
			System.out.println("로그인 해야만 장바구니를 확인 하실수있습니다.");
		}
		else {
			System.out.println("결제내역은 다음과 같습니다");
		}
		return null;
	}
	
	//숙제
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/ajaxok4.do")
	public String ajaxok4(@RequestBody String basket, HttpServletResponse res) throws Exception {
		System.out.println(basket);
		JSONArray ja = new JSONArray(basket);
		System.out.println(ja.get(0));
		JSONObject innerjo1 = (JSONObject) ja.get(0);
		JSONObject innerjo2 = (JSONObject) ja.get(1);
		JSONObject innerjo3 = (JSONObject) ja.get(2);
		System.out.println(innerjo1.get("seq"));
		System.out.println(innerjo2.get("product"));
		System.out.println(innerjo3.get("price"));
		
		JSONObject result = new JSONObject();
		result.put("result", "ok");
		
		this.pw = res.getWriter();
		this.pw.print(result);
		this.pw.close();
		return null;
	}
}
