package com.kathmandulivinglabs.navigationlibrary.services;

import android.content.Context;
import android.widget.Toast;

import com.kathmandulivinglabs.navigationlibrary.utilities.BaatoUtil;

public class ToasterMessage {

    public static void s(Context c, String message) {
        Toast.makeText(c, message, Toast.LENGTH_SHORT).show();
    }
}