package com.kathmandulivinglabs.navigationlibrary.services;

import android.content.Context;
import android.location.Geocoder;

import androidx.annotation.NonNull;

import com.kathmandulivinglabs.navigationlibrary.application.App;
import com.kathmandulivinglabs.navigationlibrary.models.Geocode;
import com.kathmandulivinglabs.navigationlibrary.models.Geometry;
import com.kathmandulivinglabs.navigationlibrary.models.Place;
import com.kathmandulivinglabs.navigationlibrary.requests.QueryAPI;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaatoReverseGeoCodeService {
    private Context context;
    private BaatoReverseGeoCodeRequestListener baatoSearchRequestListener;
    private String accessToken;
    private int radius;
    private Geocode geocode;

    public interface BaatoReverseGeoCodeRequestListener {
        /**
         * onSuccess method called after it is successful
         * onFailed method called if it can't places
         */
        void onSuccess(List<Place> places);

        void onFailed(Throwable error);
    }

    public BaatoReverseGeoCodeService(Context context) {
        this.context = context;
    }

    /**
     * Set the accessToken.
     */
    public BaatoReverseGeoCodeService setAccessToken(@NonNull String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    /**
     * Set the query to search.
     */
    public BaatoReverseGeoCodeService setGeoCode(@NonNull Geocode geoCode) {
        this.geocode = geoCode;
        return this;
    }

    public BaatoReverseGeoCodeService setRadius(@NonNull int radius) {
        this.radius = radius;
        return this;
    }

    /**
     * Method to set the UpdateListener for the AppUpdaterUtils actions
     *
     * @param baatoSearchRequestListener the listener to be notified
     * @return this
     */
    public BaatoReverseGeoCodeService withListener(BaatoReverseGeoCodeRequestListener baatoSearchRequestListener) {
        this.baatoSearchRequestListener = baatoSearchRequestListener;
        return this;
    }

    public void doReverseGeoCode() {
        QueryAPI queryAPI = App.retrofit(accessToken).create(QueryAPI.class);
        queryAPI.performReverseGeoCode(geocode.lat, geocode.lon, radius).enqueue(new Callback<List<Place>>() {
            @Override
            public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                if (response.isSuccessful() && response.body() != null)
                    baatoSearchRequestListener.onSuccess(response.body());
                else {
                    try {
                        baatoSearchRequestListener.onFailed(new Throwable(response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Place>> call, Throwable throwable) {
                baatoSearchRequestListener.onFailed(throwable);
            }
        });
    }
}