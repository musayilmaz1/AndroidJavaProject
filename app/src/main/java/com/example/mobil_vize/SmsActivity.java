package com.example.mobil_vize;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SmsActivity extends AppCompatActivity {

    Button sendButton;
    TextView et_message,et_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        sendButton=findViewById(R.id.btn_send_sms);
        et_message=findViewById(R.id.et_message);
        et_number=findViewById(R.id.et_phone);
        checkSmsPermission();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendSms();
            }
        });



    }

    private void checkSmsPermission(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)==PackageManager.PERMISSION_GRANTED){

        }else{
            requestPermissions();
        }

    }

    private void requestPermissions() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.SEND_SMS)){
            showPermissionRotionable();
        }else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},50);
        }

    }
    private void showPermissionRotionable(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Sms Gonderme Izni");
        builder.setMessage("Uygulama SMS  gonderme islemini gerceklestirecek.Izin vermek iste misnizi?");
        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ActivityCompat.requestPermissions(SmsActivity.this,new String[]{Manifest.permission.SEND_SMS},50);
            }
        });
        builder.setNegativeButton("Hayir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(SmsActivity.this,"Sms erisim izni verilemedi",Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==50){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Sms izni verildi",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Sms erisim izni vermelisin",Toast.LENGTH_SHORT).show();
            }
        }

    }
    private void SendSms(){
        String PhoneNumber=(et_number.getText().toString());
        String Message=(et_message.getText().toString());
        if(Message.length()==0||PhoneNumber==null||PhoneNumber.length()==0||Message==null){
            Toast.makeText(this,"Mesaj ve telefon numarasi girisi dogru yapilmadi",Toast.LENGTH_SHORT).show();
        }else{
            SmsManager smsManager= SmsManager.getDefault();
            smsManager.sendTextMessage(PhoneNumber,null,Message,null,null);
            Toast.makeText(this,"Sms Gonderildi",Toast.LENGTH_SHORT).show();
        }
    }
}