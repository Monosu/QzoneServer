package com.higgs.qzoneserver.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.higgs.qzoneserver.common.UtiyCommon;
import com.higgs.qzoneserver.dao.UserFriendDao;
import com.higgs.qzoneserver.model.UserFriend;

public class UserFriendDaoImpl implements UserFriendDao {
	private Connection oConnection;
	private PreparedStatement oStatement;
	private ResultSet oResultSet;
	public boolean addUserFriend(UserFriend oFriend) {
		boolean result = false;
		oConnection = DBHelper.getConnection();

		if(oConnection!=null){
			String sql = "insert into userfriend(FriendId,userid,RemarkName) values(?,?,?)";
			try {
				oStatement = oConnection.prepareStatement(sql);
				oStatement.setInt(1, oFriend.getFriendId());
				oStatement.setInt(2, oFriend.getUserId());
				oStatement.setString(3, oFriend.getRemarkName());
				if(oStatement.executeUpdate()>0){
					result = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBHelper.closeConection(oConnection, oStatement, oResultSet);
			}
		}
		return result;
	}

	public boolean deleteFriend(UserFriend oFriend ) {
		boolean result = false;
		String sqlString = "delete from userfriend where userid= ? and FriendId=?";
		oConnection = DBHelper.getConnection();
		if (oConnection!=null) {
			try {
				oStatement = oConnection.prepareStatement(sqlString);
				oStatement.setInt(1, oFriend.getUserId());
				oStatement.setInt(2, oFriend.getFriendId());
				result = oStatement.executeUpdate() > 0 ;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBHelper.closeConection(oConnection, oStatement, oResultSet);
			}
		}
		return result;
	}

	public List<UserFriend> getUserFriends(int userid) {
		List<UserFriend> dataList = null;
		String sqlString = "select * from userfriend where userid = ?";
		oConnection = DBHelper.getConnection();
		if (oConnection!=null) {
			try {
				oStatement = oConnection.prepareStatement(sqlString);
				oStatement.setInt(1, userid);
				oResultSet = oStatement.executeQuery();
				if (oResultSet!=null) {
					dataList = new ArrayList<UserFriend>();
					UserFriend oFriend  = null;
					while (oResultSet.next()) {
						oFriend = new UserFriend();
						oFriend.setFriendId(oResultSet.getInt("FriendId"));
						oFriend.setId(oResultSet.getInt("id"));
						oFriend.setUserId(oResultSet.getInt("userid"));
						oFriend.setRemarkName(oResultSet.getString("RemarkName"));
						dataList.add(oFriend);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dataList;
	}

}
