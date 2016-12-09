package com.higgs.qzoneserver.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.higgs.qzoneserver.dao.JournalDao;
import com.higgs.qzoneserver.model.Journal;

public class JournalDaoimpl implements JournalDao  {
	private Connection oConnection;
	private PreparedStatement oStatement;
	private ResultSet oSet;
	public List<Journal> getJournaList() {
		List<Journal> oList=null;
		String sqlString="select * from journal";
		oConnection=DBHelper.getConnection();

		if (oConnection!=null) {
			try {
				oStatement=oConnection.prepareStatement(sqlString);
				oSet=oStatement.executeQuery();
				if (oSet!=null) {
					oList=new ArrayList<Journal>();
					while (oSet.next()) {
						Journal oJournal=getjournalByOset(oSet);
						oList.add(oJournal);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				DBHelper.closeConection(oConnection, oStatement, oSet);
			}
		}
		return oList;
	}
	public Journal getjournalByOset(ResultSet oSet){
		Journal oJournal=new Journal();;
		try {
			oJournal.setId(oSet.getInt("id"));
			oJournal.setTitle(oSet.getString("title"));
			oJournal.setContext(oSet.getString("context"));
			oJournal.setReadCount(oSet.getInt("readcount"));
			oJournal.setUserId(oSet.getInt("userid"));
			oJournal.setPostTime(oSet.getDate("posttime"));
			oJournal.setTypeId(oSet.getInt("typeid"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return oJournal;
	}
	public Journal getJournalById(int id) {
		Journal oJournal=null;
		String sqlString="select * from journal where id=?";
		oConnection=DBHelper.getConnection();
		if (oConnection!=null) {
			try {
				oStatement=oConnection.prepareStatement(sqlString);
				oStatement.setInt(1, id);
				oSet=oStatement.executeQuery();
				if (oSet!=null) {
					oJournal=new Journal();
					if (oSet.next()) {
						oJournal=getjournalByOset(oSet);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBHelper.closeConection(oConnection, oStatement, oSet);
			}
			
		}
		return oJournal;
	}

}
