package com.higgs.qzoneserver.biz.impl;

import com.higgs.qzoneserver.biz.MoodBiz;
import com.higgs.qzoneserver.dao.MoodDao;
import com.higgs.qzoneserver.dao.impl.MoodDaoImpl;
import com.higgs.qzoneserver.model.Mood;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class MoodBizImpl implements MoodBiz {
	private MoodDao oMoodDao = new MoodDaoImpl();
	public boolean addMood(Mood oMood) {
		// TODO Auto-generated method stub
		return oMoodDao.addMood(oMood);
	}

}
