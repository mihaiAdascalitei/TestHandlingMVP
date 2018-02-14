package com.example.mihai.testhandling.gui.second.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mihai.testhandling.R;
import com.example.mihai.testhandling.gui.first.view.FirstActivity;

public class SecondActivity extends AppCompatActivity implements SecondView, View.OnClickListener {

    private Button btnBack;
    private TextView tvReplace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        init();
        setListeners();
        Bundle value = getIntent().getExtras();
        adaptViews(value.getString(FirstActivity.NUMBER_KEY));
    }

    private void init() {
        btnBack = findViewById(R.id.btn_back);
        tvReplace = findViewById(R.id.tv_replace);
    }

    private void adaptViews(String value) {
        tvReplace.setText(value);
    }

    private void setListeners() {
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                goBackToFirst();
                break;
        }
    }

    private void goBackToFirst() {
        startActivity(new Intent(this, FirstActivity.class));
        finish();
    }

    @Override
    public void updateView(String text) {
        tvReplace.setText(text);
    }
}
