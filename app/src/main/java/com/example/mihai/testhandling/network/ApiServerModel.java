package com.example.mihai.testhandling.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.mihai.testhandling.network.remote.ApiCallback;
import com.example.mihai.testhandling.network.remote.ApiServiceCall;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by adasc on 2/14/2018.
 */

public class ApiServerModel implements ApiServiceCall.ApiModel {
    private Request.Builder builder;
    private OkHttpClient client;
    private Request request;
    private final MediaType JSON = MediaType.parse("application/json");

    @Override
    public ApiServiceCall.ApiModel initRequestData(HttpMethods method, @NonNull HashMap<String, String> parameters, @NonNull String requestURL, @Nullable String jsonBody) {
        builder = new Request.Builder().url(requestURL);
        client = new OkHttpClient();

        for (String parameter : parameters.keySet()) {
            builder.addHeader(parameter, parameters.get(parameter));
        }

        if (method == HttpMethods.POST) {
            RequestBody requestBody = RequestBody.create(JSON, jsonBody);
            builder.post(requestBody);
        }
        request = builder.build();

        return this;
    }

    @Override
    public ApiServiceCall.ApiModel triggerRequest(@NonNull final ApiCallback callback) {
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                if (callback != null) {
                    callback.onResponse(false, HttpURLConnection.HTTP_BAD_REQUEST, e.getMessage(), null);
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (callback != null) {
                    callback.onResponse(response.isSuccessful(), response.code(), response.message(), response.body().string());
                }
            }
        });
        return this;
    }
}
