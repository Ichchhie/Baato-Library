package com.kathmandulivinglabs.navigationlibrary.services;

import android.util.Log;

import java.util.List;

import static android.content.ContentValues.TAG;

public class Baato {
    public static Object search(String api_key, String query) {
        Search search = new Search(api_key, query);
        Log.d(TAG, "search:string " + search.doSearch().equals(String.class));
        Log.d(TAG, "search:list " + search.doSearch().equals(List.class));
        return search.doSearch();
    }
}
