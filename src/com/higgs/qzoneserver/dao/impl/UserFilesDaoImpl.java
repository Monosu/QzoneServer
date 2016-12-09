package com.higgs.qzoneserver.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.higgs.qzoneserver.common.UtiyCommon;
import com.higgs.qzoneserver.dao.UserFilesDao;
import com.higgs.qzoneserver.model.UserFiles;

public class UserFilesDaoImpl implements UserFilesDao {
	private Connection oConnection;
	private PreparedStatement oStatement;
	private ResultSet oResultSet;
	public UserFiles addUserFiles(UserFiles oFiles,Object[] parm) {
		// TODO Auto-generated method stub
		UserFiles userFiles = null;
		oConnection = DBHelper.getConnection();
		if (oConnection!=null) {
			String sqlString = "call AddUserFiles(?,?,?,?,?,?,?,?)";
			CallableStatement oCallableStatement = null;
			try {
				 oCallableStatement = oConnection.prepareCall(sqlString);
				oCallableStatement.setString(1, oFiles.getFileName());
				oCallableStatement.setString(2, oFiles.getFilePath());
				oCallableStatement.setLong(3, oFiles.getFileSize());
				oCallableStatement.setString(4, oFiles.getFileExp());
				oCallableStatement.setString(5, UtiyCommon.dataFormt(oFiles.getUploadTime()));
				oCallableStatement.registerOutParameter(6, Types.INTEGER);
				oCallableStatement.registerOutParameter(7, Types.VARCHAR,200);
				oCallableStatement.registerOutParameter(8, Types.INTEGER);
				oCallableStatement.executeQuery();
				parm[0] = oCallableStatement.getInt("errcount");
				parm[1] = oCallableStatement.getString("errmsg");
				int fid = oCallableStatement.getInt("fileid");
				if (fid>0) {
					userFiles = getUserFilesById(fid);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBHelper.closeConection(oConnection, oCallableStatement, oResultSet);
			}
		}
		return userFiles;
	}

	public UserFiles getUserFilesById(int fid) {
		UserFiles oFiles = null;
		String sqlString = "select * from userfiles where id = ?";
		oConnection = DBHelper.getConnection();
		if (oConnection!=null) {
			try {
				oStatement = oConnection.prepareStatement(sqlString);
				oStatement.setInt(1, fid);
				oResultSet = oStatement.executeQuery();
				if (oResultSet!=null&&oResultSet.next()) {
					oFiles = new UserFiles();
					oFiles.setFileExp(oResultSet.getString("fileexp"));
					oFiles.setFileName(oResultSet.getString("filename"));
					oFiles.setFilePath(oResultSet.getString("filepath"));
					oFiles.setFileSize(oResultSet.getLong("filesize"));
					oFiles.setId(oResultSet.getInt("id"));
					oFiles.setUploadTime(UtiyCommon.stringParseDate(oResultSet.getString("uploadtime")));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBHelper.closeConection(oConnection, oStatement, oResultSet);
			}
		}
		return oFiles;
	}

	public List<UserFiles> getFilespath() {
		List<UserFiles> oList=null;
		String sqlString="select filepath from userfiles";
		oConnection=DBHelper.getConnection();
		if (oConnection!=null) {
			try {
				oStatement=oConnection.prepareStatement(sqlString);
				oResultSet=oStatement.executeQuery();
				if (oResultSet!=null) {
					oList=new ArrayList<UserFiles>();
					while (oResultSet.next()) {
						UserFiles oFiles=new UserFiles();
						oFiles.setFilePath(oResultSet.getString("filepath"));
						oList.add(oFiles);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				DBHelper.closeConection(oConnection, oStatement, oResultSet);
			}
		}
		return oList;
	}

}
