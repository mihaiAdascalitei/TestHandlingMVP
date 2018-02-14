package com.example.mihai.testhandling.gui.first.presenter.local;

import android.app.Activity;

import com.example.mihai.testhandling.gui.first.presenter.FirstPresenter;
import com.example.mihai.testhandling.gui.first.view.FirstView;

/**
 * Created by adasc on 2/14/2018.
 */

public class ThreadHandlingPresenter implements FirstPresenter {
    private FirstView view;
    private Activity activity;

    public ThreadHandlingPresenter(FirstView view, Activity activity) {
        this.view = view;
        this.activity = activity;
    }

    @Override
    public void executeData(final String message) {
        if (view != null) {
            view.goNextScreen(message);
        }

    }

    @Override
    public void doServerRequest(String number, BackgroundCallback callback) {
        throw new UnsupportedOperationException("Not valid for local!");

    }

    @Override
    public void presentUserMessage(final String message) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (view != null) {
                    view.showError(message);
                }
            }
        });
    }
}
