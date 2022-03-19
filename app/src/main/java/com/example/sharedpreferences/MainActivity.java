package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.Space;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final String PREFS_NAME = "PrefsFile";
    private final int PREFS_MODE = MODE_PRIVATE;
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvContent = findViewById(R.id.tv_content);
        tvContent.setMovementMethod(ScrollingMovementMethod.getInstance());

        Button btnSave = findViewById(R.id.btn_save);
        Button btnGet = findViewById(R.id.btn_get);
        Button btnRemove = findViewById(R.id.btn_remove);
        Button btnClear = findViewById(R.id.btn_clear);

        // 保存数据
        btnSave.setOnClickListener(view -> {
            SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, PREFS_MODE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", "前端小池");
            editor.putInt("age", 21);
            editor.apply();

            println("保存成功");
        });

        // 获取数据
        btnGet.setOnClickListener(view -> {
            SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, PREFS_MODE);
            String name = sharedPreferences.getString("name", "默认值");
            int age = sharedPreferences.getInt("age", 0);

            print("获取成功");
            print("name => " + name);
            println("age => " + age);
        });

        // 移除数据
        btnRemove.setOnClickListener(view -> {
            SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, PREFS_MODE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("name");
            editor.apply();

            println("移除成功");
        });

        // 清空数据
        btnClear.setOnClickListener(view -> {
            SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, PREFS_MODE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();

            println("清空成功");
        });
    }

    private void print(String text) {
        tvContent.append(text);
        tvContent.append("\n");
        tvContent.post(() -> {
            int scrollAmount = tvContent.getLayout().getLineTop(tvContent.getLineCount()) - tvContent.getHeight();
            tvContent.scrollTo(0, Math.max(scrollAmount + 40, 0));
        });
    }

    private void println(String text) {
        tvContent.append(text);
        tvContent.append("\n");
        tvContent.append("\n");
        tvContent.post(() -> {
            int scrollAmount = tvContent.getLayout().getLineTop(tvContent.getLineCount()) - tvContent.getHeight();
            tvContent.scrollTo(0, Math.max(scrollAmount + 40, 0));
        });
    }
}