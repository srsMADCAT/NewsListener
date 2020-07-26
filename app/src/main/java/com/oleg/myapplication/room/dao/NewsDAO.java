package com.oleg.myapplication.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.oleg.myapplication.model.Article;
import com.oleg.myapplication.room.model.NewsModel;

import java.util.List;

import dagger.Provides;
import io.reactivex.Single;

@Dao
public interface NewsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertHistoryItem(NewsModel... newsModels);

    @Query("SELECT * FROM news ORDER BY id DESC")
    List<NewsModel> getNews();

    @Query("SELECT * FROM news WHERE title LIKE :title")
    List<NewsModel> getTitle(String title);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NewsModel newsModel);

    @Query("DELETE FROM news WHERE title == :dell")
    void delete(String dell);

    @Query("DELETE FROM news")
    void nukeTable();

}
