package com.navershop.www;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
