package com.oleg.myapplication.retrofit;


import com.oleg.myapplication.model.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("everything?q=bitcoin&apiKey=3e57b271c98d4af3ba91cc32d82faa69")
    Observable<BaseResponse> getData();
}
