package com.higgs.qzoneserver.biz;

import java.util.List;

import com.higgs.qzoneserver.model.Journal;

public interface JournalBiz {
	public List<Journal> getJournaList();
	public Journal getJournalById(int id);
}
