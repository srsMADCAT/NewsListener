package com.oleg.myapplication.di.component;

import android.app.Application;
import android.content.Context;

import com.oleg.myapplication.MainActivity;
import com.oleg.myapplication.di.module.AdapterModule;
import com.oleg.myapplication.di.module.MainActivityMvpModule;
import com.oleg.myapplication.di.qualifier.ActivityContext;
import com.oleg.myapplication.di.scopes.ActivityScope;
import com.oleg.myapplication.repository.RepositoryModule;
import com.oleg.myapplication.room.DataBaseModule;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Component;


@ActivityScope
@Component(modules = {AdapterModule.class,
        MainActivityMvpModule.class}, dependencies = ApplicationComponent.class)
public interface MainActivityComponent {

    @ActivityContext
    Context getContext();
    void injectMainActivity(MainActivity mainActivity);

}
