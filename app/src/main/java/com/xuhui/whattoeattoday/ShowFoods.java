package com.xuhui.whattoeattoday;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;

import java.io.File;
import java.io.FileOutputStream;

public class ShowFoods extends AppCompatActivity {

    ListView lv_foods;
    int Position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_foods);
        lv_foods = (ListView) findViewById(R.id.lv_foods);
        lv_foods.setAdapter(new FoodAdapter());

        lv_foods.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Position = position;
                return false;
            }
        });
        this.registerForContextMenu(lv_foods);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("选择操作");
        menu.add(0, 1, Menu.NONE, "删除");
        menu.add(0, 2, Menu.NONE, "编辑");
    }

    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                try {
                    MainActivity.foods.remove(Position);
                    lv_foods.setAdapter(new FoodAdapter());

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

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                try {
                    Intent intent = new Intent(this, Modify.class);
                    intent.putExtra("Position", Position);
                    startActivityForResult(intent, 2);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "编辑失败", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.list_menu_add:
                Intent intent = new Intent(this, Add.class);
                startActivityForResult(intent, 1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 1:
                lv_foods.setAdapter(new FoodAdapter());
                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                lv_foods.setAdapter(new FoodAdapter());
                break;
        }
    }

    public class FoodAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return MainActivity.foods.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View food_view = View.inflate(ShowFoods.this, R.layout.food_item, null);
            TextView tv_foodname = (TextView) food_view.findViewById(R.id.item_foodName);
            TextView tv_foodps = (TextView) food_view.findViewById(R.id.item_foodps);
            tv_foodname.setText(MainActivity.foods.get(position).getName());
            tv_foodps.setText(MainActivity.foods.get(position).getPs());
            return food_view;
        }
    }
}
