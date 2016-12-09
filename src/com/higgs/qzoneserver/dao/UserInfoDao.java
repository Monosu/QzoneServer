package com.higgs.qzoneserver.dao;

import java.util.List;

import com.higgs.qzoneserver.model.UserInfo;

public interface UserInfoDao {
	public UserInfo userLogin(UserInfo oInfo,Object[] outParm);
	public UserInfo getUserInfoById(int id);
	public UserInfo userRegister(UserInfo oInfo,Object[] outParm);
	public List<UserInfo> getUserInfos(String selectString,int page,int pagesize);
	public String  updateUser(UserInfo oUserInfo,Object[] parm);
}
