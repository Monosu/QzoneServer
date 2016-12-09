package com.higgs.qzoneserver.dao;

import java.util.List;

import com.higgs.qzoneserver.model.UserFriend;

public interface UserFriendDao {
	public boolean addUserFriend(UserFriend oFriend);
	public boolean deleteFriend(UserFriend oFriend);
	public List<UserFriend> getUserFriends(int userid);
	
}
