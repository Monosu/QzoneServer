package com.higgs.qzoneserver.biz.impl;

import java.util.List;

import com.higgs.qzoneserver.biz.UserFilesBiz;
import com.higgs.qzoneserver.dao.UserFilesDao;
import com.higgs.qzoneserver.dao.impl.UserFilesDaoImpl;
import com.higgs.qzoneserver.model.UserFiles;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class UserFilesBizImpl implements UserFilesBiz {
	private UserFilesDao oFilesDao = new UserFilesDaoImpl();
	public UserFiles addUserFiles(UserFiles oFiles, Object[] parm) {
		// TODO Auto-generated method stub
		return oFilesDao.addUserFiles(oFiles, parm);
	}

	public UserFiles getUserFilesById(int fid) {
		// TODO Auto-generated method stub
		return oFilesDao.getUserFilesById(fid);
	}

	public List<UserFiles> getFilespath() {
		// TODO Auto-generated method stub
		return oFilesDao.getFilespath();
	}

}
