package com.example.qrscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button a;
    Button a2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a = findViewById(R.id.button);

        a.setOnClickListener(new View.OnClickListener(){ // 버튼을 누를 경우 넘어가는 함수
            public void onClick(View view){
                Intent i= new Intent(MainActivity.this,scanner.class);//클래스 이름 변경시 변경해주어야함
                startActivity(i);
            }
        });

    }

}