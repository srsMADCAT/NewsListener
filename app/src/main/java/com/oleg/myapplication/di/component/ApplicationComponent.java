package com.oleg.myapplication.di.component;

import android.content.Context;

import com.oleg.myapplication.MyApplication;
import com.oleg.myapplication.di.module.ContextModule;
import com.oleg.myapplication.di.module.RetrofitModule;
import com.oleg.myapplication.di.qualifier.ApplicationContext;
import com.oleg.myapplication.di.scopes.ApplicationScope;
import com.oleg.myapplication.repository.RepositoryModule;
import com.oleg.myapplication.retrofit.APIInterface;
import com.oleg.myapplication.room.DataBaseModule;
import com.oleg.myapplication.room.dao.NewsDAO;

import dagger.Component;

@ApplicationScope
@Component(modules = {ContextModule.class,/*
        RepositoryModule.class,*/
        DataBaseModule.class,
        RetrofitModule.class})
public interface ApplicationComponent {

    APIInterface getApiInterface();
   // NewsDAO getNewsDao();


    @ApplicationContext
    Context getContext();

    void injectApplication(MyApplication myApplication);
}
