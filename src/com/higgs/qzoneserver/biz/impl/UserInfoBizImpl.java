package com.higgs.qzoneserver.biz.impl;

import java.util.List;

import com.higgs.qzoneserver.biz.UserInfoBiz;
import com.higgs.qzoneserver.dao.UserInfoDao;
import com.higgs.qzoneserver.dao.impl.UserInfoDaoImpl;
import com.higgs.qzoneserver.model.UserInfo;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class UserInfoBizImpl implements UserInfoBiz {
	private UserInfoDao oInfoDao = new UserInfoDaoImpl();
	public UserInfo userLogin(UserInfo oInfo, Object[] outParm) {
		// TODO Auto-generated method stub
		UserInfo oInfo2 = null;
		if (oInfo==null||oInfo.getLoginName()==null||oInfo.getLoginName().equals("")||oInfo.getPassWord()==null||oInfo.getPassWord().equals("")) {
			return oInfo2;
		}else {
			oInfo2 = oInfoDao.userLogin(oInfo, outParm);
		}
		return oInfo2;
	}
	public UserInfo userRegister(UserInfo oInfo, Object[] outParm) {
		// TODO Auto-generated method stub
		if (oInfo==null) {
			outParm[0] = 7;
			outParm[1] = "注册信息不能为空";
			return null;
		}
		return oInfoDao.userRegister(oInfo, outParm);
	}
	public List<UserInfo> getUserInfos(String selectString, int page,
			int pagesize) {
		// TODO Auto-generated method stub
		return oInfoDao.getUserInfos(selectString, page, pagesize);
	}
	public String updateUser(UserInfo oUserInfo, Object[] parm) {
		// TODO Auto-generated method stub
		return oInfoDao.updateUser(oUserInfo, parm);
	}

}
