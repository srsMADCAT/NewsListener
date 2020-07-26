package com.oleg.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Insert;
import androidx.room.Room;

import com.oleg.myapplication.di.component.ApplicationComponent;
import com.oleg.myapplication.di.component.DaggerMainActivityComponent;
import com.oleg.myapplication.di.component.MainActivityComponent;
import com.oleg.myapplication.di.module.MainActivityContextModule;
import com.oleg.myapplication.di.module.MainActivityMvpModule;
import com.oleg.myapplication.di.qualifier.ActivityContext;
import com.oleg.myapplication.di.qualifier.ApplicationContext;
import com.oleg.myapplication.model.Article;
import com.oleg.myapplication.model.BaseResponse;
import com.oleg.myapplication.mvp.MainActivityContract;
import com.oleg.myapplication.mvp.PresenterImpl;
import com.oleg.myapplication.repository.insert.InsertRepository;
import com.oleg.myapplication.room.AppDataBase;
import com.oleg.myapplication.room.dao.NewsDAO;
import com.oleg.myapplication.room.model.NewsModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends FragmentActivity implements MainActivityContract.View, RecyclerViewAdapter.ClickListener {

    private RecyclerView recyclerView;
    private AppDataBase appDataBase;
    private NewsDAO newsDAO;
    private ProgressBar progressBar;
    MainActivityComponent mainActivityComponent;

    @Inject
    public RecyclerViewAdapter recyclerViewAdapter;

    @Inject
    @ApplicationContext
    public Context mContext;

    @Inject
    @ActivityContext
    public Context activityContext;

    @Inject
    PresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityContextModule(new MainActivityContextModule(this))
                .mainActivityMvpModule(new MainActivityMvpModule(this))
                .applicationComponent(applicationComponent)
                .build();

        mainActivityComponent.injectMainActivity(this);

        progressBar = findViewById(R.id.progressBar);

        appDataBase = MyApplication.getInstance().getDatabase();
        newsDAO = appDataBase.getNewsDAO();

        displayFragment();

        presenter.loadData();


    }

    @Override
    public void launchIntent(String name) {
        Toast.makeText(mContext, name, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showData(List<NewsModel> data) {
        recyclerViewAdapter.refreshView();
        recyclerViewAdapter.setData(data);
    }

    public void displayFragment() {
        FragmentNews fragment = FragmentNews.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment_container, fragment).addToBackStack(null).commit();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }


    @Override
    public void showError(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showComplete() {

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public RecyclerViewAdapter getAdapter(){
        return this.recyclerViewAdapter;
    }

    public AppDataBase getNewsDatabase(){
        return this.appDataBase;
    }

    public NewsDAO getNewsDAO(){
        return this.newsDAO;
    }

}
