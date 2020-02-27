package com.kathmandulivinglabs.osmnavigationapp;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.kathmandulivinglabs.navigationlibrary.models.Geocode;
import com.kathmandulivinglabs.navigationlibrary.models.Geometry;
import com.kathmandulivinglabs.navigationlibrary.models.Place;
import com.kathmandulivinglabs.navigationlibrary.services.Baato;
import com.kathmandulivinglabs.navigationlibrary.services.BaatoReverseGeoCodeService;
import com.kathmandulivinglabs.navigationlibrary.services.ToasterMessage;
import com.kathmandulivinglabs.navigationlibrary.utilities.BaatoUtil;
import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback,
        MapboxMap.OnMapLongClickListener{

    private static final String TAG = "apple";
    private MapboxMap mapboxMap;
    private MapView mapView;
    private NavigationRoute navigationMapRoute;
    String encoded = "wv{gD_`lhOQvAO^c@h@Yj@IZKhADXj@|ABrAElB@NLLp@LHFDJJvDb@pBx@~B`AjBBP@fCArBW|KSxEQbHGbAYhBKVmA`C}A`EuBrGcAxC}@hBA^BJb@`@dBr@tBbAh@RdDbBs@fCYxAmBnKy@`F_Gj[]zBS~AWjDC~AA|BBbBj@|JpB~YRrDbAjONlDD~AAbCuArj@o@nSkB~w@CzB{@x\\e@rLa@jN@zBO|DE|EQjBKp@W~@Sf@o@dAs@x@i@d@_@RkAj@_AP_AJmCDkBD}IDoFF_ETc@@iARyAf@UKa@_@YKk@KuBGu@Kq@MkA[k@SsB{@wCwAgAo@g@SsDiBa@OcJmE{IcEiAm@kJmEmAc@U?QA[IUK]EmI_@wBCk@Dm@FiCt@k@ViNvJ_BlAcAj@{@Xc@JuALsGN{@F_@Da@NeAn@WXWb@]fAIf@Al@Bt@`AvKVhDDpAMjB[jASb@yAzBiAvAuCfEYxAe@SmE}AsGeA_FiBwG_CsB_@sGq@{AWcA]eASuC{@g@IiDs@_BWy@FiG`BqCx@sFvA{JlC_APq@DqA?[CcAOgCg@qFqAgGkA{@Gk@@i@Dg@Je@NmAl@_^~R^p@lAlAdAv@^T~Al@t@l@F?b@MjChANBl@ATIDCFl@APNH?Ho@`CTf@HJ`@FJN?Je@fA[d@CJBJHJ@POv@HPFVd@ZBJAJa@v@bAf@JjAw@`@yAf@s@d@SHQAAf@c@C@p@YAm@R?n@SPkAPAB?|@g@HCLaAB";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));
        setContentView(R.layout.activity_main);
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        ToasterMessage.s(this, "Hello Good Morning");
        Geometry geometry = BaatoUtil.getGeoJsonFromEncodedPolyLine(encoded);
        new BaatoReverseGeoCodeService(this)
                .setGeoCode(new Geocode(27.73405, 85.33685))
                .setAccessToken(Constants.TOKEN)
                .setRadius(2)
                .withListener(new BaatoReverseGeoCodeService.BaatoReverseGeoCodeRequestListener() {
                    @Override
                    public void onSuccess(List<Place> places) {
                        Log.d(TAG, "onSuccess:reverse " + places.toString());
                    }

                    @Override
                    public void onFailed(Throwable error) {
                        Log.d(TAG, "onFailed:reverse " + error.getMessage());
                    }
                })
                .doReverseGeoCode();
//        Log.d(TAG, "onCreate: " + Baato.search(Constants.TOKEN, "chardhunga").toString());
//        NavigationRoute.builder(this)
//                .accessToken(Mapbox.getAccessToken())
//                .origin(Point.fromLngLat(85.4278774, 27.6721352))
//                .destination(Point.fromLngLat(85.3346386, 27.7340328))
//                .alternatives(true)
//                .build()
//                .getRoute(this);
//        Log.d("hello", "onCreate: " + geometry.coordinates);
    }

    @Override
    public boolean onMapLongClick(@NonNull LatLng point) {
        return false;
    }

    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        this.mapboxMap = mapboxMap;
        mapboxMap.setStyle(Style.MAPBOX_STREETS, style -> {
//            initializeLocationComponent(mapboxMap);
//            navigationMapRoute = new NavigationMapRoute(null, mapView, mapboxMap);
            mapboxMap.addOnMapLongClickListener(this);
//            Snackbar.make(mapView, "Long press to select route", Snackbar.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
//        if (navigationMapRoute != null) {
//            navigationMapRoute.onStart();
//        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
//        if (navigationMapRoute != null) {
//            navigationMapRoute.onStop();
//        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

}
