package com.example.wydnn.userguide.UI;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.wydnn.userguide.HTTP.NetRepository;
import com.example.wydnn.userguide.MyAdapter.GenerateAdapter;
import com.example.wydnn.userguide.MyAdapter.GenerateAdapterDecoration;
import com.example.wydnn.userguide.MyAdapter.RecyclerViewclickListener;
import com.example.wydnn.userguide.R;
import com.example.wydnn.userguide.domain.BaseResp;
import com.example.wydnn.userguide.domain.ListItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    private SwipeRefreshLayout Refresh1,Refresh2;
    private RecyclerView mRecyclerView;
    private GenerateAdapter generateAdapter;
    private List<ListItem>data=new ArrayList<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initAdapter();
        initData();
        refresh();
    }
    public void refresh(){
        Refresh1.setProgressBackgroundColorSchemeColor(R.color.colorPrimary);
        // mSwipRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        Refresh1.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        Refresh1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        new NetRepository().getData("apps").subscribe(new Observer<BaseResp<List<ListItem>>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                compositeDisposable.add(d);
                            }

                            @Override
                            public void onNext(BaseResp<List<ListItem>> listBaseResp) {
                                generateAdapter.setData(listBaseResp.getResults());
                                Log.d("onNext",listBaseResp.results.size()+"");
                                generateAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                        Refresh1.setRefreshing(false);
                       // mSwipRefreshLayout.setRefreshing(false);
                    }
                }, 1200);
            }
        });
    }
    public void initAdapter(){
        generateAdapter=new GenerateAdapter(MainActivity.this,data);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        mRecyclerView.setAdapter(generateAdapter);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new GenerateAdapterDecoration(12));
        //自定义ItemTouchListener
       mRecyclerView.addOnItemTouchListener(new RecyclerViewclickListener(MainActivity.this, mRecyclerView, new RecyclerViewclickListener.OnItemClickListener() {
           @Override
           public void onItemClick(View view, int position) {
               Toast.makeText(MainActivity.this,"第"+position+"项被点击了",Toast.LENGTH_SHORT).show();
           }

           @Override
           public void onItemLongClick(View view, int position) {

           }
       }));
    }

    public void initData(){
        new NetRepository().getData("apps").subscribe(new Observer<BaseResp<List<ListItem>>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResp<List<ListItem>> listBaseResp) {
                Log.d("onNext",listBaseResp.results.size()+"");

                generateAdapter.setData(listBaseResp.getResults());
                generateAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
    public void init(){
        Refresh1=findViewById(R.id.Refresh);
        Refresh2=findViewById(R.id.Refresh2);
        mRecyclerView=findViewById(R.id.leftItem);
    }
//为了放止在应用结束时还有网络任务所以在此进行取消订阅关系
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(compositeDisposable!=null&&!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }
}
