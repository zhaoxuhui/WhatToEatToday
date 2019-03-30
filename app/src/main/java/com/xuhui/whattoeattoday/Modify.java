package com.xuhui.whattoeattoday;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class Modify extends Activity {

    EditText et_foodname;
    EditText et_foodps;
    int Position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        et_foodname = (EditText) findViewById(R.id.modify_foodname);
        et_foodps = (EditText) findViewById(R.id.modify_foodps);
        Intent intent = getIntent();
        Position = intent.getIntExtra("Position", 0);
        et_foodname.setText(MainActivity.foods.get(Position).getName());
        et_foodps.setText(MainActivity.foods.get(Position).getPs());
    }

    public void Confirm(View view) {
        try {
            if (!et_foodname.getText().toString().isEmpty()) {
                String food_name = et_foodname.getText().toString();
                String food_ps = et_foodps.getText().toString();
                MainActivity.foods.get(Position).setName(food_name);
                MainActivity.foods.get(Position).setPs(food_ps);

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
