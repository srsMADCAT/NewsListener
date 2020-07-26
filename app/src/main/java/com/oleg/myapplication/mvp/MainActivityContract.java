package com.oleg.myapplication.mvp;
import com.oleg.myapplication.model.BaseResponse;


public interface MainActivityContract {
    interface View {
        void showData(BaseResponse data);

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
