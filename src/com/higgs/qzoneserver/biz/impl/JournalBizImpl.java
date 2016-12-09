package com.higgs.qzoneserver.biz.impl;

import java.util.List;

import com.higgs.qzoneserver.biz.JournalBiz;
import com.higgs.qzoneserver.dao.JournalDao;
import com.higgs.qzoneserver.dao.impl.JournalDaoimpl;
import com.higgs.qzoneserver.model.Journal;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class JournalBizImpl implements JournalBiz {
	private JournalDao oDao=new JournalDaoimpl();
	public List<Journal> getJournaList() {
		// TODO Auto-generated method stub
		return oDao.getJournaList();
	}

	public Journal getJournalById(int id) {
		// TODO Auto-generated method stub
		return oDao.getJournalById(id);
	}

}
