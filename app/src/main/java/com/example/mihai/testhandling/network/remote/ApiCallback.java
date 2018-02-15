package com.example.mihai.testhandling.network.remote;

import android.support.annotation.Nullable;

/**
 * Created by adasc on 2/13/2018.
 */

public interface ApiCallback {
    void onResponse(boolean isSuccess, int code, String message, @Nullable String result);

}
