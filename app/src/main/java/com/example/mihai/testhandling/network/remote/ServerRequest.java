package com.example.mihai.testhandling.network.remote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.mihai.testhandling.network.HttpMethods;

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
 * Created by adasc on 2/13/2018.
 */

public class ServerRequest {
    private HttpMethods httpMethods;
    private HashMap<String, String> parameters;
    private String requestURL;
    private IRequestHandler requestInterface;
    private String jsonBody;
    private Request.Builder builder;
    private final MediaType JSON = MediaType.parse("application/json");

    public interface IRequestHandler {
        public void onSucceeded(boolean isSuccess, int code, String message, @Nullable String result);
    }

    public ServerRequest(@NonNull HttpMethods httpMethods, @NonNull HashMap<String, String> parameters,
                         @NonNull String requestURL, @NonNull final IRequestHandler completion) {
        this.httpMethods = httpMethods;
        this.parameters = parameters;
        this.requestURL = requestURL;
        this.requestInterface = completion;

        builder = new Request.Builder().url(requestURL);

        for (String parameter : parameters.keySet()) {
            builder.addHeader(parameter, parameters.get(parameter));
        }

    }

    public ServerRequest(@NonNull HttpMethods httpMethods, @NonNull HashMap<String, String> parameters,
                         @NonNull String requestURL, @NonNull IRequestHandler requestInterface, @NonNull String jsonBody) {
        this(httpMethods, parameters, requestURL, requestInterface);
        this.jsonBody = jsonBody;

    }

    public void makeRequest() {
        OkHttpClient client = new OkHttpClient();

        if (httpMethods == HttpMethods.POST) {
            RequestBody requestBody = RequestBody.create(JSON, jsonBody);
            builder.post(requestBody);
        }
        Request request = builder.build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                if (requestInterface != null) {
                    requestInterface.onSucceeded(false, HttpURLConnection.HTTP_BAD_REQUEST, e.getMessage(), null);
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (requestInterface != null) {
                    requestInterface.onSucceeded(response.isSuccessful(), response.code(), response.message(), response.body().string());
                }
            }
        });

    }
}
