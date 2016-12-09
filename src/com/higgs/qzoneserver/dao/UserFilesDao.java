package com.higgs.qzoneserver.dao;

import java.util.List;

import com.higgs.qzoneserver.model.UserFiles;

public interface UserFilesDao {
	public UserFiles addUserFiles(UserFiles oFiles,Object[] parm);
	public UserFiles getUserFilesById(int fid);
	public List<UserFiles> getFilespath();
}
