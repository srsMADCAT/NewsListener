package com.oleg.myapplication.room;

import android.content.Context;

import androidx.room.Room;

import com.oleg.myapplication.di.module.MainActivityContextModule;
import com.oleg.myapplication.di.scopes.ActivityScope;
import com.oleg.myapplication.di.scopes.ApplicationScope;
import com.oleg.myapplication.room.dao.NewsDAO;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class DataBaseModule {
    @Provides
    @Singleton
    static AppDataBase provideAppDataBase(final Context context){
        return Room.databaseBuilder(context, AppDataBase.class, "test.db").fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();
    }

    @Provides
    @Singleton
    static NewsDAO provideNewsDAO(AppDataBase appDataBase){
        return appDataBase.getNewsDAO();
    }
}
