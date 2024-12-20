package com.kathmandulivinglabs.osmnavigationapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.kathmandulivinglabs.navigationlibrary.models.Geocode;
import com.kathmandulivinglabs.navigationlibrary.models.Geometry;
import com.kathmandulivinglabs.navigationlibrary.models.Place;
import com.kathmandulivinglabs.navigationlibrary.services.BaatoReverseGeoCodeService;
import com.kathmandulivinglabs.navigationlibrary.services.ToasterMessage;
import com.kathmandulivinglabs.navigationlibrary.utilities.BaatoUtil;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "apple";
    String encoded = "wv{gD_`lhOQvAO^c@h@Yj@IZKhADXj@|ABrAElB@NLLp@LHFDJJvDb@pBx@~B`AjBBP@fCArBW|KSxEQbHGbAYhBKVmA`C}A`EuBrGcAxC}@hBA^BJb@`@dBr@tBbAh@RdDbBs@fCYxAmBnKy@`F_Gj[]zBS~AWjDC~AA|BBbBj@|JpB~YRrDbAjONlDD~AAbCuArj@o@nSkB~w@CzB{@x\\e@rLa@jN@zBO|DE|EQjBKp@W~@Sf@o@dAs@x@i@d@_@RkAj@_AP_AJmCDkBD}IDoFF_ETc@@iARyAf@UKa@_@YKk@KuBGu@Kq@MkA[k@SsB{@wCwAgAo@g@SsDiBa@OcJmE{IcEiAm@kJmEmAc@U?QA[IUK]EmI_@wBCk@Dm@FiCt@k@ViNvJ_BlAcAj@{@Xc@JuALsGN{@F_@Da@NeAn@WXWb@]fAIf@Al@Bt@`AvKVhDDpAMjB[jASb@yAzBiAvAuCfEYxAe@SmE}AsGeA_FiBwG_CsB_@sGq@{AWcA]eASuC{@g@IiDs@_BWy@FiG`BqCx@sFvA{JlC_APq@DqA?[CcAOgCg@qFqAgGkA{@Gk@@i@Dg@Je@NmAl@_^~R^p@lAlAdAv@^T~Al@t@l@F?b@MjChANBl@ATIDCFl@APNH?Ho@`CTf@HJ`@FJN?Je@fA[d@CJBJHJ@POv@HPFVd@ZBJAJa@v@bAf@JjAw@`@yAf@s@d@SHQAAf@c@C@p@YAm@R?n@SPkAPAB?|@g@HCLaAB";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToasterMessage.s(this, "Hello Good Morning");
        Geometry geometry = BaatoUtil.getGeoJsonFromEncodedPolyLine(encoded);

        performReverseGeoCoding();
    }

    private void performReverseGeoCoding() {
        new BaatoReverseGeoCodeService(this)
                .setGeoCode(new Geocode(27.73405, 85.33685))
                .setAccessToken(Constants.TOKEN)
                .setRadius(2)
                .withListener(new BaatoReverseGeoCodeService.BaatoReverseGeoCodeRequestListener() {
                    @Override
                    public void onSuccess(List<Place> places) {
                        Log.d(TAG, "onSuccess: " + places.size());
                        for (Place place : places)
                            Log.d(TAG, "onSuccess:reverse " + place);
                    }

                    @Override
                    public void onFailed(Throwable error) {
                        Log.d(TAG, "onFailed:reverse " + error.getMessage());
                    }
                })
                .doReverseGeoCode();
    }

}
