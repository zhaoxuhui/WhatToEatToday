package com.xuhui.whattoeattoday;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.*;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView iv_select;
    static String path;
    Context context;
    static String FileName;

    static List<Food> foods = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_select = (ImageView) findViewById(R.id.icon_select);
        randomImage();

        context = getApplicationContext();
        path = context.getFilesDir() + "/";
        File filedir = new File(path);
        if (!filedir.exists()) {
            filedir.mkdir();
        }

        FileName = path + "foods" + ".txt";
        File file = new File(FileName);
        if (file.exists()) {
            try {
                Log.d("Load file", "Load file");
                foods.clear();
                InputStream inputStream = null;
                inputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String temp;
                try {
                    while ((temp = bufferedReader.readLine()) != null) {
                        String[] numbers = temp.split("\t");
                        String foodname = numbers[0];
                        String foodps;
                        if (numbers.length == 1) {
                            foodps = "";
                        } else {
                            foodps = numbers[1];
                        }
                        Food foodTemp = new Food(foodname, foodps);
                        foods.add(foodTemp);
                        inputStream.close();
                    }
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            Log.d("Create file", "Create file");
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                fos.write("".getBytes());
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String Results = "";
        for (int i = 0; i < foods.size(); i++) {
            Results += foods.get(i).getName() + "\t" + foods.get(i).getPs() + "\n";
        }
        File file = new File(FileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(Results.replace("\n", "\r\n").getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("Save file", "Save file");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                Intent intent = new Intent(this, Add.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.check_food:
                Intent intent2 = new Intent(this, ShowFoods.class);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 1:
                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void Select(View view) {
        rotateAnim();
        if (foods.size() != 0) {
            int length = foods.size();
            int select_index = (int) (Math.random() * (length + 1) - 1);
            Log.d("Select food", String.valueOf(select_index));
            Intent intent = new Intent(getApplicationContext(), Result.class);
            intent.putExtra("Position", select_index);
            startActivityForResult(intent, 2);
        } else {
            Toast.makeText(getApplicationContext(), "还没有添加吃的哦，快去添加吧~", Toast.LENGTH_SHORT).show();
        }

    }

    public void rotateAnim() {
        Animation anim = new RotateAnimation(0f,
                360f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);
        anim.setFillAfter(true); // 设置保持动画最后的状态
        anim.setDuration(1000); // 设置动画时间
        anim.setInterpolator(new AccelerateDecelerateInterpolator()); // 设置插入器
        iv_select.startAnimation(anim);
    }

    public void randomImage() {
        List<Integer> imgs = new ArrayList<>();
        imgs.add(R.drawable.img_chopsticks);
        imgs.add(R.drawable.img_fork);
        imgs.add(R.drawable.img_fork2);
        imgs.add(R.drawable.img_spoon);

        imgs.add(R.drawable.img_apple);
        imgs.add(R.drawable.img_banana);
        imgs.add(R.drawable.img_baozi);
        imgs.add(R.drawable.img_beef);
        imgs.add(R.drawable.img_bread);
        imgs.add(R.drawable.img_cabbage);
        imgs.add(R.drawable.img_cake);
        imgs.add(R.drawable.img_carrot);
        imgs.add(R.drawable.img_cheese);
        imgs.add(R.drawable.img_cherry);
        imgs.add(R.drawable.img_chicken);
        imgs.add(R.drawable.img_chips);
        imgs.add(R.drawable.img_chips2);
        imgs.add(R.drawable.img_coffee);
        imgs.add(R.drawable.img_coke);
        imgs.add(R.drawable.img_cookie);
        imgs.add(R.drawable.img_donut);
        imgs.add(R.drawable.img_eggs);
        imgs.add(R.drawable.img_fish);
        imgs.add(R.drawable.img_hamburger);
        imgs.add(R.drawable.img_hotdog);
        imgs.add(R.drawable.img_ice_cream);
        imgs.add(R.drawable.img_ice_cream2);
        imgs.add(R.drawable.img_juice);
        imgs.add(R.drawable.img_lobster);
        imgs.add(R.drawable.img_milk);
        imgs.add(R.drawable.img_milk2);
        imgs.add(R.drawable.img_milk3);
        imgs.add(R.drawable.img_noodles);
        imgs.add(R.drawable.img_noodles2);
        imgs.add(R.drawable.img_octopus);
        imgs.add(R.drawable.img_orange);
        imgs.add(R.drawable.img_orange_juice);
        imgs.add(R.drawable.img_pistachio);
        imgs.add(R.drawable.img_pizza);
        imgs.add(R.drawable.img_porridge);
        imgs.add(R.drawable.img_pudding);
        imgs.add(R.drawable.img_rice);
        imgs.add(R.drawable.img_rice2);
        imgs.add(R.drawable.img_sausage);
        imgs.add(R.drawable.img_strawberry);
        imgs.add(R.drawable.img_sushi);
        imgs.add(R.drawable.img_watermelon);
        imgs.add(R.drawable.img_watermelon2);
        imgs.add(R.drawable.img_watermelon3);
        imgs.add(R.drawable.img_yogurt);

        int length = imgs.size();
        int random_index = (int) (Math.random() * (length - 1));
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.get(random_index));
        iv_select.setImageBitmap(bitmap);
    }
}
