package com.example.mysqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText et_name;
    EditText et_mobile;
    EditText et_email;
    Button btn_button;
    DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name = findViewById(R.id.tx_name);
        et_mobile = findViewById(R.id.tx_mobile);
        et_email = findViewById(R.id.tx_email);
        btn_button = findViewById(R.id.btn_button);

        dbHelper = new DataBaseHelper(this);
        btn_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = et_name.getText().toString();
                String mobile = et_mobile.getText().toString();
                String email = et_email.getText().toString();
                dbHelper.insertRegisterData(name, mobile, email);


            }
            //   Intent intent=new Intent(MainActivity.this, RecyclerViewList.class);
            // startActivity(intent);
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecyclerViewListActivity.class);
                startActivity(intent);


            }
        });

    }
}