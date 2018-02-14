package com.example.mihai.testhandling.network.remote;

import android.support.annotation.NonNull;

import com.example.mihai.testhandling.network.ApiCallback;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by adasc on 2/14/2018.
 */

public interface ApiServiceCall {

    interface ApiService {
        void doServerRequest(String number, ApiCallback callback);
    }

    interface ApiModel {
        void doGetRequest(@NonNull HashMap<String, String> parameters,
                          @NonNull String requestURL, @NonNull final IRequestHandler requestHandler);


        void doPostRequest(@NonNull HashMap<String, String> parameters,
                           @NonNull String requestURL, @NonNull String jsonBody, @NonNull final IRequestHandler requestHandler);
    }
}
