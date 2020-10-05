package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import model.Article;
import model.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewActivity extends AppCompatActivity {

    private RecyclerView mRcArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        mRcArticle = findViewById(R.id.rc_article_data);

        mRcArticle.setLayoutManager(new LinearLayoutManager(ViewActivity.this, RecyclerView.VERTICAL, false));
        ProgressDialog pg = new ProgressDialog(ViewActivity.this);
        pg.setTitle("getting your news");
        pg.show();

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        HashMap<String, Object> queries = new HashMap<>();
        queries.put("apiKey", "fc70d5c9ff6047e8846cb9a715e43b83");
        queries.put("sources", "google-news");

//        Call<String> getNews = apiInterface.getAllNews(queries);
//
//        getNews.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                pg.hide();
//                String responseValue = response.body();
//
//                try{
//                    JSONObject responseObject = new JSONObject(responseValue);
//                    Result resultValue = Result.parseResultResponse(responseObject);
//                    ArrayList<Article> articles = resultValue.articles;
//                    ArticleAdapter adapter = new ArticleAdapter(ViewActivity.this,articles);
//                    mRcArticle.setAdapter(adapter);
//
//                }catch (JSONException e){
//                    e.printStackTrace();
//                }
//                Toast.makeText(ViewActivity.this,"success", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                pg.hide();
//                Toast.makeText(ViewActivity.this,"Failed", Toast.LENGTH_LONG).show();
//            }
//        });


        Call<Result> getNews = apiInterface.getAllNews(queries);

        getNews.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                pg.hide();
                Result resultValue = response.body();
                ArrayList<Article> articles = resultValue.articles;
                ArticleAdapter adapter = new ArticleAdapter(ViewActivity.this,articles);
                mRcArticle.setAdapter(adapter);

                Toast.makeText(ViewActivity.this,"success",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                pg.hide();
                Toast.makeText(ViewActivity.this,"Failed", Toast.LENGTH_LONG).show();
            }
        });
    }


}
