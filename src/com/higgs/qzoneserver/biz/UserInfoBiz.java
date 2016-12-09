package com.higgs.qzoneserver.biz;

import java.util.List;

import com.higgs.qzoneserver.model.UserInfo;

public interface UserInfoBiz {
	public UserInfo userLogin(UserInfo oInfo,Object[] outParm);
	public UserInfo userRegister(UserInfo oInfo,Object[] outParm);
	public List<UserInfo> getUserInfos(String selectString,int page ,int pagesize);
	public String updateUser(UserInfo oUserInfo,Object[] parm);
}
