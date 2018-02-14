package com.example.mihai.testhandling.gui.first.presenter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.mihai.testhandling.gui.first.presenter.local.ThreadHandlingPresenter;
import com.example.mihai.testhandling.gui.first.view.FirstView;
import com.example.mihai.testhandling.network.ApiCallback;
import com.example.mihai.testhandling.gui.first.presenter.local.BackgroundCallback;
import com.example.mihai.testhandling.network.ApiServiceExecutor;
import com.example.mihai.testhandling.network.remote.ApiServiceCall;

import java.net.HttpURLConnection;

/**
 * Created by adasc on 2/13/2018.
 */

public class FirstPresenterImplementation implements FirstPresenter {
    private ThreadHandlingPresenter localPresenter;
    private ApiServiceCall apiService;


    public FirstPresenterImplementation(FirstView view, Activity activity) {
        this.localPresenter = new ThreadHandlingPresenter(view, activity);
        this.apiService = new ApiServiceExecutor();
    }

    @Override
    public void executeData(String message) {
        localPresenter.executeData(message);
    }


    @Override
    public void doServerRequest(String number, final BackgroundCallback callback) {
        apiService.doServerRequest(number, new ApiCallback() {
            @Override
            public void onResponse(boolean isSuccess, int code, String message, @Nullable String result) {
                if (isSuccess && code == HttpURLConnection.HTTP_OK) {
                    callback.onSuccesRequest(true, result);
                    Log.e("REQUEST", result);

                } else {
                    callback.onSuccesRequest(false, message);
                    Log.e("REQUEST", "Request not ok " + code + " " + message);
                }
            }
        });
    }

    @Override
    public void presentUserMessage(String message) {
        localPresenter.presentUserMessage(message);
    }
}
