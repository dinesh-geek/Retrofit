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

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleHolder>{
        private ArrayList<Article> articles;
        private Context context;


        public ArticleAdapter(Context context,ArrayList<Article> articles){
            this.context = context;
            this.articles = articles;
        }

    @NonNull
    @Override
    public ArticleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cell_article,parent,false);
        ArticleHolder holder = new ArticleHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleHolder holder, int position) {
        final Article article = articles.get(position);
        holder.mTvTitle.setText(article.title);
        holder.mTvDescription.setText(article.description);
        holder.mTvAuthor.setText(article.author);
        Glide.with(context).load(article.urlToImage).into(holder.mIvPhoto);

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    class ArticleHolder extends RecyclerView.ViewHolder{
        private TextView mTvTitle;
        private TextView mTvAuthor;
        private TextView mTvDescription;

        private ImageView mIvPhoto;

        public ArticleHolder(@NonNull View itemView) {
            super(itemView);
            mTvAuthor = itemView.findViewById(R.id.tv_author);
            mTvDescription=itemView.findViewById(R.id.tv_description);
            mTvTitle = itemView.findViewById(R.id.tv_title);
            mIvPhoto = itemView.findViewById(R.id.iv_photo);
        }
    }
}
