package com.test.exam.db.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.exam.db.service.DBService;

@Controller
public class DBController {

	@Autowired
	private DBService ds; 

	@RequestMapping(value="/db/add")
	public @ResponseBody Map insertDB(@RequestBody Map pm,ModelMap model,HttpSession hs) {
		int result = ds.addDB(pm);
		model.addAttribute("create", "fail");		
		if(result == 1){
			model.addAttribute("create", "successfully made");		
		}	
		return model;
	}

}
