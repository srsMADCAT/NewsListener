package com.oleg.myapplication.repository.insert;

import com.oleg.myapplication.room.model.NewsModel;

public interface InsertRepository {
    void insertNews(NewsModel... newsModels);
}
