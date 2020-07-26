package com.oleg.myapplication.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.oleg.myapplication.room.model.NewsModel;

import java.util.List;

import dagger.Provides;
import io.reactivex.Single;

@Dao
public interface NewsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertHistoryItem(NewsModel... newsModels);

    @Query("SELECT * FROM news ORDER BY id DESC")
    Single<List<NewsModel>> getNews();


}
