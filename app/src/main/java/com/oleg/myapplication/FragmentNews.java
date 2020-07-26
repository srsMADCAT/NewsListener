package com.oleg.myapplication;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.oleg.myapplication.room.AppDataBase;
import com.oleg.myapplication.room.dao.NewsDAO;
import com.oleg.myapplication.room.model.NewsModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentNews extends Fragment {

    private EditText search;
    private RecyclerView recyclerView;

    public static FragmentNews newInstance() {
        return new FragmentNews();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_neews, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = getView().findViewById(R.id.rv_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(((MainActivity)getActivity()).getAdapter());

        search = getView().findViewById(R.id.search);

        AppDataBase database =  ((MainActivity)getActivity()).getNewsDatabase();
        NewsDAO newsDao =  ((MainActivity)getActivity()).getNewsDAO();

        if (newsDao.getNews().isEmpty()) {
            ((MainActivity)getActivity()).showError("Database is EMPTY. Check internet connection");
        } else {
            ((MainActivity)getActivity()).showData(newsDao.getNews());
        }

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (String.valueOf(search.getText()).isEmpty()){
                    ((MainActivity)getActivity()).showData(newsDao.getNews());

                } else {
                    List<NewsModel> newsModels;
                    newsModels = newsDao.getTitle("%" + String.valueOf(search.getText()) + "%");
                    ((MainActivity)getActivity()).showData(newsModels);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }



    public FragmentNews(){}
}
