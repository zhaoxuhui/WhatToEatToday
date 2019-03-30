package com.xuhui.whattoeattoday;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Result extends Activity {

    TextView tv_result;
    TextView tv_result_ps;
    int Position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        tv_result = (TextView) findViewById(R.id.tv_result);
        tv_result_ps = (TextView) findViewById(R.id.tv_result_ps);
        Intent intent = getIntent();
        Position = intent.getIntExtra("Position", 0);
        tv_result.setText(MainActivity.foods.get(Position).getName());
        tv_result_ps.setText(MainActivity.foods.get(Position).getPs());
    }

    public void Confirm(View view) {
        this.finish();
    }
}
