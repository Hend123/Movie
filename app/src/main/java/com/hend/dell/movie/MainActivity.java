package com.hend.dell.movie;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

;

public class MainActivity extends AppCompatActivity implements MovieAdapter.OnClickListerner {

    private RecyclerView mRecyclerView;
    private ArrayList<MovieModel> movieModelArrayList = new ArrayList<>();
    private MovieAdapter mAdapter;
    private String path = "/3/movie/popular?api_key=e16b24e78f85b37a0ef4d0418db79a04&fbclid=IwAR3Ia1Db2c0FJ58PbE_Rs0w1L5mEI7dOQXje_KeAfpd8Bl0CR4FryuJvtkw";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mToolBar = findViewById(R.id.toolBar);
        setSupportActionBar(mToolBar);
        mRecyclerView = findViewById(R.id.recycler_view);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("movieModelList")) {
                movieModelArrayList = savedInstanceState.getParcelableArrayList("movieModelList");
                mAdapter = new MovieAdapter(getApplicationContext(), movieModelArrayList, MainActivity.this);
                mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                mRecyclerView.setAdapter(mAdapter);

            }
        } else {
            retrofitConnection(path);

        }
    }
    private void retrofitConnection(String path) {
        retrofit2.Call<ResultModel> modelCall = objectRetrofit(path);
        modelCall.enqueue(new Callback<ResultModel>() {
            @Override
            public void onResponse(retrofit2.Call<ResultModel> call, Response<ResultModel> response) {
                movieModelArrayList = response.body().getResults();
                Log.v("test",movieModelArrayList.get(0).getOriginal_title());
                mAdapter = new MovieAdapter(getApplicationContext(), movieModelArrayList, MainActivity.this);
                mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(retrofit2.Call<ResultModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }

        });
    }

    private retrofit2.Call<ResultModel> objectRetrofit(String path) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.themoviedb.org").
                addConverterFactory(GsonConverterFactory.create()).build();
        MovieApi movieApi = retrofit.create(MovieApi.class);
        return movieApi.getMovies(path);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sort_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {

            case R.id.most_popular: {
                sortByMostPopular();
                return true;
            }

            case R.id.top_rated: {
                sortByTopRated();
                return true;

            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("movieModelList", movieModelArrayList);
    }

    @Override
    public void onClickItem(int position) {
        MovieModel m = movieModelArrayList.get(position);
        Intent intent = new Intent(getApplicationContext(), DetailsMovie.class);
        String TITLE = "title";
        intent.putExtra(TITLE, m.getOriginal_title());
        String OVERVIEW = "overview";
        intent.putExtra(OVERVIEW, m.getOverview());
        String RELEASE_DATE = "releaseDate";
        intent.putExtra(RELEASE_DATE, m.getRelease_date());
        String USER_RATING = "userRating";
        intent.putExtra(USER_RATING, m.getVote_average());
        Uri image = MovieModel.buildUrl(m.getPoster_path());
        intent.setData(image);
        startActivity(intent);
    }

    private void sortByMostPopular() {
        retrofitConnection(path);
    }

    private void sortByTopRated() {
        String path = "/3/movie/top_rated?api_key=e16b24e78f85b37a0ef4d0418db79a04&fbclid=IwAR3Ia1Db2c0FJ58PbE_Rs0w1L5mEI7dOQXje_KeAfpd8Bl0CR4FryuJvtkw";
        retrofitConnection(path);

    }
}
