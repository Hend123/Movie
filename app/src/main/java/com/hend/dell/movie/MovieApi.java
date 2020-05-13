package com.hend.dell.movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface MovieApi {
    //path
    @GET
    Call<ResultModel> getMovies(@Url String url);
}
