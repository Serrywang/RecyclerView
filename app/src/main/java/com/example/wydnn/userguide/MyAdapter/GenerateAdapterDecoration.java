package com.example.wydnn.userguide.MyAdapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class GenerateAdapterDecoration extends RecyclerView.ItemDecoration {
    private final int verticalSpaceHeight;

    public GenerateAdapterDecoration(int verticalSpaceHeight) {
        this.verticalSpaceHeight = verticalSpaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //如果不是最后一个条目才加上间隔分割线
        if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
            outRect.bottom = verticalSpaceHeight;
        }

    }
}
