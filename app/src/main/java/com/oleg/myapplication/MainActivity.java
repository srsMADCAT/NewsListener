package com.oleg.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.oleg.myapplication.di.component.ApplicationComponent;
import com.oleg.myapplication.di.component.DaggerMainActivityComponent;
import com.oleg.myapplication.di.component.MainActivityComponent;
import com.oleg.myapplication.di.module.MainActivityContextModule;
import com.oleg.myapplication.di.module.MainActivityMvpModule;
import com.oleg.myapplication.di.qualifier.ActivityContext;
import com.oleg.myapplication.di.qualifier.ApplicationContext;
import com.oleg.myapplication.model.BaseResponse;
import com.oleg.myapplication.mvp.MainActivityContract;
import com.oleg.myapplication.mvp.PresenterImpl;
import com.oleg.myapplication.room.AppDataBase;
import com.oleg.myapplication.room.dao.NewsDAO;
import com.oleg.myapplication.room.model.NewsModel;

import javax.inject.Inject;

public class MainActivity extends Activity implements MainActivityContract.View, RecyclerViewAdapter.ClickListener {

    private RecyclerView recyclerView;
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

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(activityContext));
        recyclerView.setAdapter(recyclerViewAdapter);
        progressBar = findViewById(R.id.progressBar);

        presenter.loadData();
      //  presenter.showData();


        AppDataBase database = MyApplication.getInstance().getDatabase();
        NewsDAO newsDao = database.getNewsDAO();
        newsDao.insertHistoryItem(new NewsModel("title", "url", "content"));
    }

    @Override
    public void launchIntent(String name) {
        Toast.makeText(mContext, name, Toast.LENGTH_SHORT).show();
        // startActivity(new Intent(activityContext, DetailActivity.class).putExtra("name", name));
    }

    @Override
    public void showData(BaseResponse data) {
        recyclerViewAdapter.setData(data.getArticles());
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
}
