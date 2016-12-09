package com.higgs.qzoneserver.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.higgs.qzoneserver.common.UtiyCommon;
import com.higgs.qzoneserver.dao.MoodDao;
import com.higgs.qzoneserver.model.Mood;

public class MoodDaoImpl implements MoodDao {
	private Connection oConnection;
	private PreparedStatement oStatement;
	private ResultSet oResultSet;
	public boolean addMood(Mood oMood) {
		String sqlString = "call AddMood(?,?,?,?,?,?)";
		boolean result = false;
		oConnection = DBHelper.getConnection();
		if (oConnection!=null) {
			try {
				CallableStatement oCallableStatement = oConnection.prepareCall(sqlString);
				oCallableStatement.setString(1, oMood.getMoodContext());
				oCallableStatement.setInt(2, oMood.getUserId());
				oCallableStatement.setString(3, oMood.getFid());
				oCallableStatement.setString(4,UtiyCommon.dataFormt(oMood.getPostTime()));
				oCallableStatement.registerOutParameter(5, Types.INTEGER);
				oCallableStatement.registerOutParameter(6, Types.VARCHAR,200);
				oCallableStatement.executeUpdate();
				int errcount = oCallableStatement.getInt("errcount");
				String errMsg = oCallableStatement.getString("errmsg");
				if (errcount==4) {
					result = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

}
