package com.example.mobil_vize;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

public class RandomActivity extends AppCompatActivity {
    TextView et_adet,et_min,et_max;
    Button btn_bar_olustur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        btn_bar_olustur=findViewById(R.id.btn_bar_olustur);
        btn_bar_olustur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_adet=findViewById(R.id.et_adet);
                et_min=findViewById(R.id.et_min);
                et_max=findViewById(R.id.et_max);
                barOlustur();

            }
        });

        }

    public void barOlustur(){
        LinearLayout linearLayout=findViewById(R.id.linearLayout);
        int progresBarCount=Integer.parseInt(et_adet.getText().toString());
        int userMin=Integer.parseInt(et_min.getText().toString());
        int userMax=Integer.parseInt(et_max.getText().toString());

        for(int i=0;i<progresBarCount;i++) {
            LinearLayout progresLayout = new LinearLayout(this);
            progresLayout.setOrientation(LinearLayout.VERTICAL);
            progresLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            int randomProgres = generateRandomValue(userMin, userMax);
            int randomMax = generateRandomValue(userMin, userMax);
            int randomMin = generateRandomValue(userMin, userMax);
            int[] values = {randomProgres, randomMin, randomMax};
            Arrays.sort(values);
            ProgressBar progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
            progressBar.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            TextView minValueTextView = new TextView(this);
            minValueTextView.setGravity(Gravity.START);
            minValueTextView.setText("MIN " + values[0]);
            TextView maxValueTextView = new TextView(this);
            maxValueTextView.setGravity(Gravity.END);
            maxValueTextView.setText("MAX " + values[2]);
            TextView ProgressValueTextView = new TextView(this);
            ProgressValueTextView.setGravity(Gravity.CENTER);
            ProgressValueTextView.setText("PROGRESS " + values[1]);
            progresLayout.addView(minValueTextView);
            progresLayout.addView(progressBar);
            progresLayout.addView(maxValueTextView);
            progresLayout.addView(ProgressValueTextView);

            progressBar.setMax(values[2]);
            progressBar.setMin(values[0]);
            progressBar.setProgress(values[1]);
            linearLayout.addView(progresLayout);
        }

    }
    private int generateRandomValue(int min,int max){
        Random random=new Random();
        return random.nextInt(max - min +1) + min;
    }
}