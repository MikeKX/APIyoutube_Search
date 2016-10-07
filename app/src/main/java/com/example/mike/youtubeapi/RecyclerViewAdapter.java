package com.example.mike.youtubeapi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by mc on 10/7/16.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<objectItems> items;
    ViewHolder viewHolder;
    Context context;
    View views;
    private int itemLayout;

    public RecyclerViewAdapter(List<objectItems> items, Context c) {
        this.items = items;
        this.context = c;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle;
        public ImageView image;

        public ViewHolder(View view) {
            super(view);
            txtTitle = (TextView) view.findViewById(R.id.txt_Title);
            image = (ImageView) view.findViewById(R.id.img_List);
        }
    }

    public void setItemLayout(int itemLayout) {
        this.itemLayout = itemLayout;
    }


    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        items = (List<objectItems>) inflater.inflate(itemLayout, parent, false);
        viewHolder = new ViewHolder((View) items);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final objectItems objectItems=items.get(position);
        holder.txtTitle.setText(objectItems.getTitle());
        Glide.with(context)
                .load(objectItems
                        .getImage())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}


