package com.example.mobil_vize;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_convertor,btn_random,btn_sms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_convertor=findViewById(R.id.btn_convertor);
        btn_random=findViewById(R.id.btn_random);
        btn_sms=findViewById(R.id.btn_sms);


        btn_convertor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ConvertorActivity.class);
                startActivity(intent);
            }
        });
        btn_random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,RandomActivity.class);
                startActivity(intent);
            }
        });
        btn_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SmsActivity.class);
                startActivity(intent);
            }
        });
    }
}