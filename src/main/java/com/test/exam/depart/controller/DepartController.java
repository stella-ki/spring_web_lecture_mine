package com.test.exam.depart.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.exam.depart.service.DepartService;

@Controller
public class DepartController {
	
	@Autowired
	private DepartService ds;
	
	@RequestMapping(value = "/depart/list", method = RequestMethod.GET)
	public String get(HttpServletRequest request, ModelMap model, HttpSession hs) {
		System.out.println("test");
		System.out.println(ds.departlist());
		
		return "test";
	}

}
