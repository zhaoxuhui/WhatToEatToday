package com.xuhui.whattoeattoday;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class Add extends Activity {

    EditText et_food;
    EditText et_ps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        et_food = (EditText) findViewById(R.id.et_food);
        et_ps = (EditText) findViewById(R.id.et_ps);
    }

    public void Confirm(View view) {
        try {
            if (!et_food.getText().toString().isEmpty()) {
                String food_name = et_food.getText().toString();
                String food_ps = et_ps.getText().toString();
                MainActivity.foods.add(new Food(food_name, food_ps));

                String Results = "";
                for (int i = 0; i < MainActivity.foods.size(); i++) {
                    Results += MainActivity.foods.get(i).getName() + "\t" + MainActivity.foods.get(i).getPs() + "\n";
                }
                File file = new File(MainActivity.FileName);
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(file);
                    fos.write(Results.replace("\n", "\r\n").getBytes());
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("Save file", "Save file");
                this.finish();
            } else {
                Toast.makeText(this, "还没有输入想吃什么哦", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Cancel(View view) {
        this.finish();
    }
}
