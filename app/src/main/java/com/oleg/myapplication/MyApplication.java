package com.oleg.myapplication;

import android.app.Activity;
import android.app.Application;

import androidx.room.Room;

import com.oleg.myapplication.di.component.ApplicationComponent;
import com.oleg.myapplication.di.component.DaggerApplicationComponent;
import com.oleg.myapplication.di.module.ContextModule;
import com.oleg.myapplication.room.AppDataBase;


public class MyApplication extends Application {

    ApplicationComponent applicationComponent;

    public static MyApplication instance;

    private AppDataBase database;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        database = Room.databaseBuilder(this, AppDataBase.class, "database")
                .allowMainThreadQueries()
                .build();

        applicationComponent = DaggerApplicationComponent.builder().contextModule(new ContextModule(this)).build();
        applicationComponent.injectApplication(this);

    }

    public static MyApplication get(Activity activity){
        return (MyApplication) activity.getApplication();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public AppDataBase getDatabase() {
        return database;
    }
}

