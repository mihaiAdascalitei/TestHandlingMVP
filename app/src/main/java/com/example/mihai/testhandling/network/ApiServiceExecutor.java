package com.example.mihai.testhandling.network;

import android.support.annotation.Nullable;

import com.example.mihai.testhandling.network.remote.ApiServiceCall;
import com.example.mihai.testhandling.network.remote.IRequestHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by adasc on 2/14/2018.
 */

public class ApiServiceExecutor implements ApiServiceCall.ApiService {

    private final String HTTP_REQUEST_URL = "http://192.168.100.4:5000/number";
    private ApiServiceCall.ApiModel apiModel;

    public ApiServiceExecutor() {
        apiModel = new ApiServerModel();
    }


    @Override
    public void doServerRequest(String number, final ApiCallback callback) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Content-Type", "application/json");

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.accumulate("number", number);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        apiModel.doPostRequest(hashMap, HTTP_REQUEST_URL, jsonObject.toString(), new IRequestHandler() {
            @Override
            public void onSucceeded(boolean isSuccess, int code, String message, @Nullable String result) {
                if (callback != null) {
                    callback.onResponse(isSuccess, code, message, result);
                }
            }
        });

    }
}
