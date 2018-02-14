package com.example.mihai.testhandling.network.remote;

import android.support.annotation.Nullable;

/**
 * Created by adasc on 2/14/2018.
 */

public interface IRequestHandler {
    public void onSucceeded(boolean isSuccess, int code, String message, @Nullable String result);
}
