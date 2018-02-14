package com.example.mihai.testhandling.network.remote;

import com.example.mihai.testhandling.network.ApiCallback;

/**
 * Created by adasc on 2/14/2018.
 */

public interface ApiServiceCall {
    void doServerRequest(String number, ApiCallback callback);
}
