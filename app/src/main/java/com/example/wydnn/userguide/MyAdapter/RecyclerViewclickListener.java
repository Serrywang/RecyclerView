package com.example.wydnn.userguide.MyAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;


public class RecyclerViewclickListener implements RecyclerView.OnItemTouchListener {

    private GestureDetector mGestureDetector;
    private OnItemClickListener mListener;

    public RecyclerViewclickListener(Context mContext, final RecyclerView mRecyclerView, OnItemClickListener onItemClickListener){

        mListener=onItemClickListener;
        mGestureDetector=new GestureDetector(mContext, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {

            }
            //点击一下在拿起来
            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                //根据点击的位置去获取相应的ItemView
                View childview=mRecyclerView.findChildViewUnder(motionEvent.getX(),motionEvent.getY());
                if(childview!=null&&mListener!=null){
                    mListener.onItemClick(childview,mRecyclerView.getChildLayoutPosition(childview));
                }
                return true;
            }
            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                return false;
            }
            @Override
            public void onLongPress(MotionEvent motionEvent) {
                View view=mRecyclerView.findChildViewUnder(motionEvent.getX(),motionEvent.getY());
                if(view!=null&&mListener!=null){
                    mListener.onItemLongClick(view,mRecyclerView.getChildLayoutPosition(view));
                }
            }
            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                return false;
            }
        });
    }
    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        if(mGestureDetector.onTouchEvent(motionEvent)){
            return true;
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }

    public interface OnItemClickListener {

        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);

    }

}
