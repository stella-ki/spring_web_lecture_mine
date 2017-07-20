package com.test.exam.depart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.exam.common.dao.MainDao;

@Service
public class DepartServiceImpl implements DepartService{
	
	@Autowired
	private MainDao mainDao;

	@Override
	public List departlist() {
		String sqlID = "depart.DEPART_LIST";
		List departlist = mainDao.getList(sqlID);
		return departlist;
	}


}
