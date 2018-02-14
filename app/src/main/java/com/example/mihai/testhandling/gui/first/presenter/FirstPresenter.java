package com.example.mihai.testhandling.gui.first.presenter;

import com.example.mihai.testhandling.network.remote.BackgroundCallback;

/**
 * Created by adasc on 2/13/2018.
 */

public interface FirstPresenter {

    void executeData(String message);

    void doServerRequest(String number, BackgroundCallback callback);

    void presentUserMessage(String message);
}
