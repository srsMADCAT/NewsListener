package com.oleg.myapplication.repository.insert;

import com.oleg.myapplication.room.dao.NewsDAO;
import com.oleg.myapplication.room.model.NewsModel;

import javax.inject.Inject;

public final class InsertRepositoryImpl implements InsertRepository{
    private final NewsDAO newsDAO;
    @Inject
    InsertRepositoryImpl(final NewsDAO _newsDAO){
        newsDAO =_newsDAO;
    }
    @Override
    public void insertNews(NewsModel... newsModels) {
        newsDAO.insertHistoryItem(newsModels);
    }
}
