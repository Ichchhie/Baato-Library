package com.kathmandulivinglabs.navigationlibrary.services;

import com.kathmandulivinglabs.navigationlibrary.application.App;
import com.kathmandulivinglabs.navigationlibrary.models.Place;
import com.kathmandulivinglabs.navigationlibrary.requests.QueryAPI;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Search {
    private String api_key;
    private String query;

    public Search(String api_key, String query) {
        this.api_key = api_key;
        this.query = query;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Object doSearch() {
        final Object[] object = {new Object()};
        QueryAPI queryAPI = App.retrofit(api_key).create(QueryAPI.class);
        queryAPI.searchQuery(query).enqueue(new Callback<List<Place>>() {
            @Override
            public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                object[0] = response.body();
            }

            @Override
            public void onFailure(Call<List<Place>> call, Throwable t) {
                object[0] = t.getMessage();
            }
        });
        return object[0];
    }

}
