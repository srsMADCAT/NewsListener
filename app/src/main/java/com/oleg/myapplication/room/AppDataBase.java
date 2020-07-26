package com.oleg.myapplication.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.oleg.myapplication.di.scopes.ApplicationScope;
import com.oleg.myapplication.room.dao.NewsDAO;
import com.oleg.myapplication.room.model.NewsModel;

import javax.inject.Singleton;

@Database(entities = {NewsModel.class}, version = 1, exportSchema = false)
@ApplicationScope
public abstract class AppDataBase extends RoomDatabase {
    public abstract NewsDAO getNewsDAO();
}
