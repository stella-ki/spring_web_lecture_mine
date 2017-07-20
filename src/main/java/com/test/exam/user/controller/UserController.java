package com.test.exam.user.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.exam.user.dto.User;
import com.test.exam.user.service.UserService;

@Controller
public class UserController { 

	@Autowired
	private UserService us; 
	
	@RequestMapping("/user/main")
	public String init(HttpServletRequest request, ModelMap model, HttpSession hs) {
		String id = (String)hs.getAttribute("ID");
		if(id!=null){
			model.addAttribute("userid", id);
			return "/user/main";
		}else{
			return "/user/login";
		}
	}

	//from tag의 parameter값을 가져오는 것과 비슷하다.
	@RequestMapping(value="/user/test", method=RequestMethod.GET)
	public @ResponseBody Map loginTest(HttpServletRequest request, @RequestParam Map pm,ModelMap model,HttpSession hs) {
		User user = us.getUserPwd(pm);
		String url = "";
		if(user==null){
			model.put("data", "F");
			model.put("url", "/user/login");
			model.put("msg", "Login Fail");
		}else{
			hs.setAttribute("ID", user.getUser_id());
			model.put("data", "S");
			model.put("url", "/user/main");
			model.put("msg", "Login Success");
		}
		return model;
	}
	
	//html에서 body에 있는 값을 가져 올 수 있따. json 형태로 읽어오게 된다.
	//DTO도 받아 올 수 있는데 이걸로 쓰면 좀 느려서 그냥 String으로 받아와서 json을 만드는 것이 더 좋다.
	@RequestMapping(value="/user/loginaction", method=RequestMethod.POST)
	public @ResponseBody Map login(HttpServletRequest request, @RequestBody Map pm,ModelMap model,HttpSession hs) {
		User user = us.getUserPwd(pm);
		String url = "";
		if(user==null){
			model.put("data", "F");
			model.put("url", "/user/login");
			model.put("msg", "Login Fail");
		}else{
			hs.setAttribute("ID", user.getUser_id());
			model.put("data", "S");
			model.put("url", "/user/main");
			model.put("msg", "Login Success");
		}
		return model;
	}
	
	@RequestMapping(value="/user/logoutaction", method=RequestMethod.GET)
	public String logout(HttpSession hs) {
		hs.invalidate();
		return "/user/login";
	}
	
	@RequestMapping(value="/user/userlistaction", method=RequestMethod.POST)
	public @ResponseBody Map userlistaction(@RequestBody Map pm,ModelMap model,HttpSession hs) {
		String mode = (String)pm.get("mode");
		String userid = (String)pm.get("userid");
		if(mode.equals("del")){
			int memdel = us.memdel(pm);
			System.out.println("삭제 : "+memdel);
			if(memdel > 0){
				model.put("msg", userid+"가 삭제 되었습니다.");
				model.put("url", "/user/userlist");
			}else{
				model.put("url", "/user/userlist");
				model.put("msg", "유저리스트로 이동합니다.");
			}
		}else{
			List memlist = us.memlist();
			//List memrole = us.memrole();
			model.put("data", memlist);
			//model.put("role", memrole);
			model.put("url", "/user/userlist");
			model.put("msg", "list call");
		}
		return model;
	}

	@RequestMapping(value="/user/sessionRegi")
	public @ResponseBody Map sessionRegi(@RequestBody Map pm,ModelMap model,HttpSession hs) {
		hs.setAttribute("boardadmin", (String)pm.get("boardadmin"));
		
		model.put("url", "/user/main");
		
		return model;
	}
	
	@RequestMapping(value="/user/register")
	public @ResponseBody Map join(@RequestBody Map pm,ModelMap model,HttpSession hs) {
		int memdel = us.memregister(pm);
		String userid = (String)pm.get("userid");
		System.out.println("삭제 : "+memdel);
		if(memdel > 0){
			model.put("msg", userid+"가 가입 되었습니다.");
			model.put("url", "/user/userlist");
		}else{
			model.put("url", "/user/userlist");
			model.put("msg", "유저리스트로 이동합니다.");
		}
		
		return model;
	}
	
	@RequestMapping(value="/user/changeBoardAdmin")
	public @ResponseBody Map changeBoardAdmin(@RequestBody Map pm,ModelMap model,HttpSession hs) {
		int memdel = us.memupdate(pm);
		model.put("url", "/user/main");
		
		return model;
	}
}
