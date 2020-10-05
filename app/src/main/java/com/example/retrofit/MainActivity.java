package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import model.Article;
import model.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NewsCategoryAdapter.NewsCategoryClickListener {

//    private ImageView mIvSingleImage;
    private RecyclerView mRcNews;

    private String CategoryName = "business";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView mRcNewsCategory = findViewById(R.id.rc_news_categories);
        mRcNewsCategory.setLayoutManager(new LinearLayoutManager(MainActivity.this,RecyclerView.HORIZONTAL,false));

        mRcNews = findViewById(R.id.rc_news);
        mRcNews.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));

        NewsCategoryAdapter newsCategoryAdapter = new NewsCategoryAdapter(MainActivity.this);
        newsCategoryAdapter.setListener(this);
        mRcNewsCategory.setAdapter(newsCategoryAdapter);

        Button mBtnGetResult = findViewById(R.id.btn_get_result);
//        mIvSingleImage = findViewById(R.id.iv_single_image);

        mBtnGetResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                getNews();
                Intent data = new Intent(MainActivity.this, ViewActivity.class);
                startActivity(data);
            }
        });
        getSourcesNews();
    }

    private void getSourcesNews(){
        HashMap<String , Object> queries = new HashMap<>();
        queries.put("apiKey", "fc70d5c9ff6047e8846cb9a715e43b83");
        queries.put("category", CategoryName);

        ProgressDialog dialog = new ProgressDialog( MainActivity.this);
           dialog.setTitle("getting your news");
           dialog.show();

           ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

//        Call<String> getSourcesNews = apiInterface.getAllSources(queries);
////        getSourcesNews.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                dialog.hide();
//              Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
//
//                String responseValue = response.body();
//
//                try {
//                    JSONObject responseObject = new JSONObject(responseValue);
//                    Result resultValue = Result.parseResultResponse(responseObject);
//
//                   ArrayList<Article> articles = resultValue.articles;
//
//                   NewsAdapter newsAdapter = new NewsAdapter(MainActivity.this, articles);
//                   mRcNews.setAdapter(newsAdapter);
//
//             }catch (JSONException e){
//                 e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                dialog.hide();
//              Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_LONG).show();
//            }
//        });


        Call<Result> getTopHeadlines = apiInterface.getAllSource(queries);
        getTopHeadlines.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                dialog.hide();
                Result result = response.body();

                ArrayList<Article> articles = result.articles;
                NewsAdapter newsAdapter = new NewsAdapter(MainActivity.this, articles);
                mRcNews.setAdapter(newsAdapter);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                dialog.hide();
            }
        });

    }

    @Override
    public void onNewsCategoryClicked(String categoryName) {
        this.CategoryName = categoryName;
        getSourcesNews();
    }

//    private void getNews(){
//
//        ProgressDialog pg = new ProgressDialog( MainActivity.this);
//        pg.setTitle("getting your news");
//        pg.show();
//        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
//
//        HashMap<String, Object> queries = new HashMap<>();
//        queries.put("apiKey", "fc70d5c9ff6047e8846cb9a715e43b83");
//        queries.put("sources", "google-news");
//
//
//        Call<String> getNews = apiInterface.getAllNews(queries);
//
//        getNews.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                pg.hide();
//
//                String responseValue = response.body();
//
//                try {
//                    JSONObject responseObject = new JSONObject(responseValue);
//                    Result resultValue = Result.parseResultResponse(responseObject);
//
//                    ArrayList<Article> articles = resultValue.articles;
//
//                    Glide.with(MainActivity.this).load(articles.get(0).urlToImage).into(mIvSingleImage);
//                }catch (JSONException e){
//                    e.printStackTrace();
//                }
//                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                pg.hide();
//                Toast.makeText(MainActivity.this, "Failed ", Toast.LENGTH_LONG).show();
//            }
//        });
//    }
}