package com.oleg.myapplication.repository;

import com.oleg.myapplication.repository.insert.InsertRepository;
import com.oleg.myapplication.repository.insert.InsertRepositoryImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract InsertRepository provideInsertRepository(final InsertRepositoryImpl repository);
}
