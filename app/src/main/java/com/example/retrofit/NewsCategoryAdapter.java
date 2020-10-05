package com.example.retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

public class NewsCategoryAdapter extends RecyclerView.Adapter<NewsCategoryAdapter.NewsCategoryHolder> {

    private Context context;
    private String[] newsCategories;
    private NewsCategoryClickListener listener;
    private int selectedPosition = 0;

    public void setListener(NewsCategoryClickListener listener){
        this.listener = listener;
    }

    public NewsCategoryAdapter(Context context){
        this.context = context;
        newsCategories = context.getResources().getStringArray(R.array.news_category);
    }


    @NonNull
    @Override
    public NewsCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsCategoryHolder(LayoutInflater.from(context).inflate(R.layout.cell_news_category,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsCategoryHolder holder, int position) {
        String categoryName = newsCategories[position];
        holder.mTvNewsCategory.setText(categoryName);


        if(selectedPosition == position){
            holder.mRlRoot.setBackground(ResourcesCompat.getDrawable(context.getResources(),R.drawable.bg_cat_selected,null));
            holder.mTvNewsCategory.setTextColor(ResourcesCompat.getColor(context.getResources(), R.color.colorWhite, null));
        }else{
            holder.mRlRoot.setBackground(ResourcesCompat.getDrawable(context.getResources(),R.drawable.bg_cat_unselected, null ));
            holder.mTvNewsCategory.setTextColor(ResourcesCompat.getColor(context.getResources(), R.color.colorBlack, null));
        }

        holder.mRlRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.onNewsCategoryClicked(categoryName);
                    selectedPosition = position;
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return  newsCategories.length;
    }

    class NewsCategoryHolder extends RecyclerView.ViewHolder{

        private RelativeLayout mRlRoot;
        private TextView mTvNewsCategory;

        public NewsCategoryHolder(@NonNull View itemView) {
            super(itemView);
            mTvNewsCategory = itemView.findViewById(R.id.tv_news_category);
            mRlRoot = itemView.findViewById(R.id.rl_news_cat);
        }
    }

    public interface NewsCategoryClickListener{
        void onNewsCategoryClicked(String categoryName);
    }
}
