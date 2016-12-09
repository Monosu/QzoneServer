package com.higgs.qzoneserver.biz.impl;

import java.util.List;

import com.higgs.qzoneserver.biz.UserFriendBiz;
import com.higgs.qzoneserver.dao.UserFriendDao;
import com.higgs.qzoneserver.dao.impl.UserFriendDaoImpl;
import com.higgs.qzoneserver.model.UserFriend;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class UserFriendBizImpl implements UserFriendBiz {
	private UserFriendDao oDao = new UserFriendDaoImpl();
	public boolean addUserFriend(UserFriend oFriend) {
		boolean result = false;
		if(oFriend!=null&&oFriend.getFriendId()>0&&oFriend.getUserId()>0){
			result = oDao.addUserFriend(oFriend);
		}
		return result;
	}
	public boolean deleteFriend(UserFriend oFriend) {
		// TODO Auto-generated method stub
		return oDao.deleteFriend(oFriend);
	}
	public List<UserFriend> getUserFriends(int userid) {
		// TODO Auto-generated method stub
		return oDao.getUserFriends(userid);
	}
	
}
