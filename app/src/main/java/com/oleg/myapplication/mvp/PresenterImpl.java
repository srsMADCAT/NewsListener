package com.oleg.myapplication.mvp;

import com.oleg.myapplication.model.BaseResponse;
import com.oleg.myapplication.repository.insert.InsertRepository;
import com.oleg.myapplication.retrofit.APIInterface;
import com.oleg.myapplication.room.dao.NewsDAO;
import com.oleg.myapplication.room.model.NewsModel;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PresenterImpl implements MainActivityContract.Presenter {

    APIInterface apiInterface;
    MainActivityContract.View mView;


    @Inject
    public PresenterImpl(APIInterface apiInterface, MainActivityContract.View mView) {
        this.apiInterface = apiInterface;
        this.mView = mView;
    }


    @Override
    public void loadData() {

        mView.showProgress();
        apiInterface.getData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse>() {

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.showError("Error occurred");
                        mView.hideProgress();
                    }

                    @Override
                    public void onComplete() {
                        mView.showComplete();
                        mView.hideProgress();
                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse data) {

                        mView.showData(data);
                    }
                });
    }

    @Override
    public void saveData() {
     // insertRepository.insertNews(new NewsModel("ee","ee","ee"));
    }

    @Override
    public void showData() {

    }
}
