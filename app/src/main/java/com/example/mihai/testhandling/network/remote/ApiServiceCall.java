package com.example.mihai.testhandling.network.remote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.mihai.testhandling.network.HttpMethods;

import java.util.HashMap;

/**
 * Created by adasc on 2/14/2018.
 */

public interface ApiServiceCall {

    interface ApiService {
        void doServerRequest(String number, ApiCallback callback);
    }

    interface ApiModel {

        ApiModel initRequestData(HttpMethods method, @NonNull HashMap<String, String> parameters,
                                 @NonNull String requestURL, @Nullable String jsonBody);

        ApiModel triggerRequest(@NonNull final ApiCallback callback);
    }
}
