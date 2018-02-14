package com.example.mihai.testhandling.gui.first.view;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mihai.testhandling.R;
import com.example.mihai.testhandling.gui.first.presenter.FirstPresenter;
import com.example.mihai.testhandling.gui.first.presenter.FirstPresenterImplementation;
import com.example.mihai.testhandling.gui.first.presenter.local.ThreadHandlingPresenter;
import com.example.mihai.testhandling.gui.second.view.SecondActivity;
import com.example.mihai.testhandling.gui.second.view.SecondView;
import com.example.mihai.testhandling.network.remote.BackgroundCallback;

public class FirstActivity extends AppCompatActivity implements FirstView, View.OnClickListener {

    private EditText etNumber;
    private Button btnNext;
    private FirstPresenter presenter;
    public static final String NUMBER_KEY = "number_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        init();
        setListeners();
    }

    private void init() {

        etNumber = (EditText) findViewById(R.id.et_number);
        btnNext = (Button) findViewById(R.id.btn_next);
        presenter = new FirstPresenterImplementation(this, this);
    }

    private void setListeners() {
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_next:
                btnNextAction();
                break;
        }
    }

    public void btnNextAction() {
        presenter.doServerRequest(etNumber.getText().toString(), new BackgroundCallback() {
            @Override
            public void onSuccesRequest(boolean isRequestSuccessful, String result) {
                if (isRequestSuccessful) {
                    presenter.executeData(result);
                } else {
                    presenter.presentUserMessage(result);
                }
            }
        });
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goNextScreen(String value) {
        Intent intent = new Intent(this, SecondActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(NUMBER_KEY, value);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
