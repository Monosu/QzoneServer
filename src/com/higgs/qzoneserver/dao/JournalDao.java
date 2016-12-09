package com.higgs.qzoneserver.dao;

import java.util.List;

import com.higgs.qzoneserver.model.Journal;

public interface JournalDao {
	public List<Journal> getJournaList();
	public Journal getJournalById(int id);
}
