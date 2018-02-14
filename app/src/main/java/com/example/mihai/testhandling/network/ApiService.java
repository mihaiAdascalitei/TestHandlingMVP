package com.example.mihai.testhandling.network;

import android.support.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by adasc on 2/13/2018.
 */

public class ApiService {
    private static final ApiService INSTANCE = new ApiService();
    private final String HTTP_REQUEST_URL = "http://192.168.100.4:5000/number";

    public static ApiService getInstance() {
        return INSTANCE;
    }

    private ApiService() {
    }

    public void doServerRequest(String number, final ApiCallback helperRequest) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Content-Type", "application/json");

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.accumulate("number", number);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ServerRequest serverRequest = new ServerRequest(HttpMethods.POST, hashMap, HTTP_REQUEST_URL, new ServerRequest.IRequestHandler() {
            @Override
            public void onSucceeded(boolean isSuccess, int code, String message, @Nullable String result) {
                if (helperRequest != null) {
                    helperRequest.onResponse(isSuccess, code, message, result);
                }
            }
        }, jsonObject.toString());

        serverRequest.makeRequest();
    }
}

