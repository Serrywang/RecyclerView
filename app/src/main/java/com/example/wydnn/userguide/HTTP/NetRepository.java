package com.example.wydnn.userguide.HTTP;

import com.example.wydnn.userguide.Service.Service;
import com.example.wydnn.userguide.domain.BaseResp;
import com.example.wydnn.userguide.domain.ListItem;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NetRepository {
    public Observable<BaseResp<List<ListItem>>>getData(String category){
        return RetrofitServiceManager.getInstance()
                .create(Service.class)
                .getResult(category)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
