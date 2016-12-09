package com.higgs.qzoneserver.biz;

import java.util.List;

import com.higgs.qzoneserver.model.UserFiles;

public interface UserFilesBiz {
	public UserFiles addUserFiles(UserFiles oFiles,Object[] parm);
	public UserFiles getUserFilesById(int fid);
	public List<UserFiles> getFilespath();
}
