package com.example.wydnn.userguide.MyAdapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wydnn.userguide.R;
import com.example.wydnn.userguide.domain.ListItem;

import java.util.ArrayList;
import java.util.List;

public class GenerateAdapter extends RecyclerView.Adapter <GenerateAdapter.ViewHolder>{

    private Context mContext;
    private List<ListItem>Item=new ArrayList<>();

    public GenerateAdapter(Context mContext,List<ListItem>Item){
        this.mContext=mContext;
        this.Item=Item;
    }
    public void setData(List<ListItem>data){
        this.Item=data;
    }
   /***
    * 创建ViewHolder
    * */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.left_item, viewGroup, false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        ListItem listItem=Item.get(i);
        Glide.with(mContext).load(listItem.getIcon()).into(viewHolder.ImageView);//加载图片
        viewHolder.title.setText(listItem.getTitle());
        viewHolder.Id.setText(listItem.getId());
        viewHolder.create.setText(listItem.getCreated_at());

    }

    @Override
    public int getItemCount() {
        return Item.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
       ImageView ImageView;
       TextView title_Tips;
       TextView title;
       TextView Id_Tips;
       TextView Id;
       TextView createTips;
       TextView create;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //实例化Item组件
            ImageView=itemView.findViewById(R.id.ImageView);
            title_Tips=itemView.findViewById(R.id.titleTips);
            title=itemView.findViewById(R.id.title);
            Id_Tips=itemView.findViewById(R.id.Id_tips);
            Id=itemView.findViewById(R.id.Id);
            createTips=itemView.findViewById(R.id.createTips);
            create=itemView.findViewById(R.id.createTimeTips);
        }

    }
}
