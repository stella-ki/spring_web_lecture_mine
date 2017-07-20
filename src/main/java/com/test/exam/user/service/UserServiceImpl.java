package com.test.exam.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.exam.common.dao.MainDao;
import com.test.exam.user.dto.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private MainDao mainDao;
	
	public User getUserPwd(Map pm) {
		String sqlId = "user.USER_PWD_OBJECT";
		User user = (User) mainDao.getObject(sqlId, pm);
		boolean checkPwd = checkPwd(pm, user);
		if(!checkPwd){
			return null;
		}
		return user;
	}
	
	public boolean checkPwd(Map pm, User ud){
		if(ud==null){
			return false;
		}
		String userPwd = (String)pm.get("userpwd");
		String checkPwd = ud.getUser_pwd();
		
		if(userPwd.equals(checkPwd)){
			return true;
		}
		return false;
	}

	public List memlist(){
		String sqlId="user.USER_LIST";
		List memlist= mainDao.getList(sqlId);
		return memlist;
	}
	

	public List memrole(){
		String sqlId="user.USER_ROLE";
		List memrole= mainDao.getList(sqlId);
		return memrole;
	}
	
	
	public int memdel(Map pm){
		String sqlId = "user.USER_DEL";
		int user = mainDao.delete(sqlId, pm);
		return user;
	}

	@Override
	public int memregister(Map paramMap) {
		String sqlID = "user.REGISTER";
		int user = mainDao.insert(sqlID, paramMap);
		return user;
	}

	@Override
	public int memupdate(Map paramMap) {
		String sqlID = "user.USER_BOARD_UPDATE";
		int user = mainDao.insert(sqlID, paramMap);
		return user;
	}
}
