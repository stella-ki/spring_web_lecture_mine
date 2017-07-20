package com.test.exam.db.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.exam.common.dao.MainDao;
import com.test.exam.user.dto.User;

@Service
public class DBSerbiceImpl implements DBService {

	@Autowired
	private MainDao mainDao;
	
	@Override
	public int addDB(Map paramMap) {
		String sqlID = "db.DB_INFO_INSERT";
		int user = mainDao.insert(sqlID, paramMap);
		return user;
	}


}
