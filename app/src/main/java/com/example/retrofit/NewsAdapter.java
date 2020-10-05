package com.example.retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import model.Article;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    private Context context;
    private ArrayList<Article> articles;

    public NewsAdapter(Context context, ArrayList<Article> articles){
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsHolder(LayoutInflater.from(context).inflate(R.layout.cell_news, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        Article article = articles.get(position);

        holder.mTvNewsTitle.setText(article.title);
        holder.mTvNewsDesc.setText(article.description);

        Glide.with(context).load(article.urlToImage).into(holder.mIvNewsImage);
    }

    @Override
    public int getItemCount() {
        return  articles.size();
    }

    class NewsHolder extends RecyclerView.ViewHolder{

        private TextView mTvNewsTitle;
        private TextView mTvNewsDesc;
        private ImageView mIvNewsImage;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);

            mIvNewsImage = itemView.findViewById(R.id.iv_news);
            mTvNewsTitle = itemView.findViewById(R.id.tv_news_title);
            mTvNewsDesc = itemView.findViewById(R.id.tv_news_desc);
        }
    }
}
