package com.example.wydnn.userguide.Service;

import com.example.wydnn.userguide.domain.BaseResp;
import com.example.wydnn.userguide.domain.ListItem;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {
    //参数可以设置apps,wow,android,IOS
    @GET("api/xiandu/category/{wow}")
    Observable<BaseResp<List<ListItem>>>getResult(@Path("wow") String category);
}
