package com.oleg.myapplication.mvp;
import com.oleg.myapplication.model.BaseResponse;
import com.oleg.myapplication.room.model.NewsModel;

import java.util.List;


public interface MainActivityContract {
    interface View {
        void showData(List<NewsModel> data);

        void showError(String message);

        void showComplete();

        void showProgress();

        void hideProgress();
    }

    interface Presenter {
        void loadData();

        void saveData();

        void showData();
    }
}
