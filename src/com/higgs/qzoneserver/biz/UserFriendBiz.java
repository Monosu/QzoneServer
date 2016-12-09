package com.higgs.qzoneserver.biz;

import java.util.List;

import com.higgs.qzoneserver.model.UserFriend;

public interface UserFriendBiz {
	public boolean addUserFriend(UserFriend oFriend);
	public boolean deleteFriend(UserFriend oFriend);
	public List<UserFriend> getUserFriends(int userid);

}
