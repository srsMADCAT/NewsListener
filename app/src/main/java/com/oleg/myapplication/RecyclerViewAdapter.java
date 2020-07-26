package com.oleg.myapplication;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.oleg.myapplication.model.Article;
import com.oleg.myapplication.room.model.NewsModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<NewsModel> data;
    private RecyclerViewAdapter.ClickListener clickListener;

    @Inject
    public RecyclerViewAdapter(ClickListener clickListener) {
        this.clickListener = clickListener;
        data = new ArrayList<>();
    }

    @SuppressLint("ResourceType")
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_neews, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(data.get(position).getTitle());
        holder.content.setText(data.get(position).getContent());
        Glide
                .with(holder.itemView.getContext())
                .load(data.get(position).getUrlToImage())
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView content;
        private ImageView image;


        ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            image = itemView.findViewById(R.id.image);
        }
    }

    public interface ClickListener {
        void launchIntent(String name);
    }

    public void setData(List<NewsModel> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void refreshView() {
        this.data.clear();
        notifyDataSetChanged();
    }

}

