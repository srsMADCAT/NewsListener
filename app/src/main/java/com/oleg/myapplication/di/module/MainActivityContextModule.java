package com.oleg.myapplication.di.module;

import android.app.Application;
import android.content.Context;

import com.oleg.myapplication.MainActivity;
import com.oleg.myapplication.di.qualifier.ActivityContext;
import com.oleg.myapplication.di.scopes.ActivityScope;
import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityContextModule {
    private MainActivity mainActivity;

    public Context context;

    public MainActivityContextModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        context = mainActivity;
    }

    @Provides
    @ActivityScope
    public MainActivity providesMainActivity() {
        return mainActivity;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context provideContext() {
        return context;
    }


}
