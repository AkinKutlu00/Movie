package com.aagames.movieroulette.tmdb.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.aagames.movieroulette.tmdb.constant.ConstantTmdb.BASE_URL;

public class TmdbRetrofit {
    private static Retrofit retrofit = null;
    private static TmdbRetrofit instance;

    public TmdbRetrofit() {
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }

    public static synchronized TmdbRetrofit getInstance(){
        if (instance==null){
            instance=new TmdbRetrofit();
        }
        return instance;
    }

    public TmdbClient getApiService(){
        return retrofit.create(TmdbClient.class);
    }
}

