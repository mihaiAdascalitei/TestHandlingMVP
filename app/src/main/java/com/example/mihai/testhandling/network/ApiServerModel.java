package com.example.mihai.testhandling.network;

import android.support.annotation.NonNull;

import com.example.mihai.testhandling.network.remote.ApiServiceCall;
import com.example.mihai.testhandling.network.remote.IRequestHandler;

import org.json.JSONObject;

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
    private final MediaType JSON = MediaType.parse("application/json");

    @Override
    public void doGetRequest(@NonNull HashMap<String, String> parameters, @NonNull String requestURL, @NonNull final IRequestHandler requestHandler) {
        builder = new Request.Builder().url(requestURL);

        for (String parameter : parameters.keySet()) {
            builder.addHeader(parameter, parameters.get(parameter));
        }

        OkHttpClient client = new OkHttpClient();

        Request request = builder.build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                if (requestHandler != null) {
                    requestHandler.onSucceeded(false, HttpURLConnection.HTTP_BAD_REQUEST, e.getMessage(), null);
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (requestHandler != null) {
                    requestHandler.onSucceeded(response.isSuccessful(), response.code(), response.message(), response.body().string());
                }
            }
        });

    }

    @Override
    public void doPostRequest(@NonNull HashMap<String, String> parameters, @NonNull String requestURL, @NonNull String jsonBody, @NonNull final IRequestHandler requestHandler) {
        builder = new Request.Builder().url(requestURL);

        for (String parameter : parameters.keySet()) {
            builder.addHeader(parameter, parameters.get(parameter));
        }

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = RequestBody.create(JSON, jsonBody);
        builder.post(requestBody);

        Request request = builder.build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                if (requestHandler != null) {
                    requestHandler.onSucceeded(false, HttpURLConnection.HTTP_BAD_REQUEST, e.getMessage(), null);
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (requestHandler != null) {
                    requestHandler.onSucceeded(response.isSuccessful(), response.code(), response.message(), response.body().string());
                }
            }
        });

    }
}
