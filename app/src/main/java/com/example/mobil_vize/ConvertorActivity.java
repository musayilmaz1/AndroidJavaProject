package com.example.mobil_vize;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ConvertorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView et_decimal,tv_sonuc_decimal,et_byte,tv_sonuc_byte,et_celcius,tv_sonuc_celcius;
    Spinner sp_decimal,sp_byte;
    RadioGroup radioGroup;
    RadioButton rb_fah,rb_kel;

    Integer decimal_input;
    Double byte_input,celcius_input;

    public String[] decimal_items={"Secilecek donusun tipi","Binary","Octal","Hexadecimal"};

    public String [] byte_items={"Secilecek donusun tipi","kilo byte","byte","kibi byte","bit"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertor);
        et_decimal=findViewById(R.id.et_decimal);
        tv_sonuc_decimal=findViewById(R.id.tv_sonuc_decimal);
        sp_decimal=findViewById(R.id.sp_decimal);
        et_byte=findViewById(R.id.et_Byte);
        tv_sonuc_byte=findViewById(R.id.tv_sonuc_byte);
        sp_byte=findViewById(R.id.sp_Byte);
        tv_sonuc_celcius=findViewById(R.id.tv_sonuc_celcius);
        et_celcius=findViewById(R.id.et_celcius);
        radioGroup=findViewById(R.id.radioGroup);
        rb_fah=findViewById(R.id.rb_fah);
        rb_kel=findViewById(R.id.rb_kel);

        ArrayAdapter<String>decimal_ad=new ArrayAdapter<String>
                (ConvertorActivity.this, android.R.layout.simple_spinner_item,decimal_items);
        decimal_ad.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sp_decimal.setAdapter(decimal_ad);
        sp_decimal.setOnItemSelectedListener(ConvertorActivity.this);

        ArrayAdapter<String>byte_ad=new ArrayAdapter<String>
                (ConvertorActivity.this, android.R.layout.simple_spinner_item,byte_items);
        byte_ad.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sp_byte.setAdapter(byte_ad);
        sp_byte.setOnItemSelectedListener(ConvertorActivity.this);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(rb_fah.getId()==i){
                    CelciusToFah();
                }
                else if(rb_kel.getId()==i){
                    CelciusToKel();
                }
            }
        });
        


    }

    private void CelciusToKel() {
        celcius_input=Double.parseDouble(et_celcius.getText().toString());
        celcius_input=celcius_input+273.15;
        tv_sonuc_celcius.setText(Double.toString(celcius_input));
    }

    private void CelciusToFah() {
        celcius_input=Double.parseDouble(et_celcius.getText().toString());
        celcius_input=celcius_input*((9/5)+32);
        tv_sonuc_celcius.setText(Double.toString(celcius_input));
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
        if(parent.getId()==R.id.sp_decimal){
            switch (i){
                case 0:
                    break;
                case 1:
                    decimalToBinary();
                    break;
                case 2:
                    decimalToOctal();
                    break;
                case 3:
                    decimalToHexa();
                    break;

            }
            
        }
        else if(parent.getId()==R.id.sp_Byte){
            switch (i){
                case 0:
                    break;
                case 1:
                    MegaByteToKbyte();
                    break;
                case 2:
                    MegaByteToByte();
                    break;
                case 3:
                    MegaByteToKibiByte();
                    break;
                case 4:
                    MegaByteToBit();
                    break;

            }
            
        }
        

    }

    private void MegaByteToBit() {
        byte_input=Double.parseDouble(et_byte.getText().toString());
        byte_input=(byte_input*8388608);
        tv_sonuc_byte.setText(Double.toString(byte_input));
    }

    private void MegaByteToKibiByte() {
        byte_input=Double.parseDouble(et_byte.getText().toString());
        byte_input=(byte_input*976.5625);
        tv_sonuc_byte.setText(Double.toString(byte_input));
    }

    private void MegaByteToByte() {
        byte_input=Double.parseDouble(et_byte.getText().toString());
        byte_input=(byte_input*1048575);
        tv_sonuc_byte.setText(Double.toString(byte_input));
    }

    private void MegaByteToKbyte() {
        byte_input=Double.parseDouble(et_byte.getText().toString());
        byte_input=(byte_input*1024);
        tv_sonuc_byte.setText(Double.toString(byte_input));
    }

    private void decimalToHexa() {
        decimal_input=Integer.parseInt(et_decimal.getText().toString());
        tv_sonuc_decimal.setText(Integer.toHexString(decimal_input));

    }
    private void decimalToOctal() {
        decimal_input=Integer.parseInt(et_decimal.getText().toString());
        tv_sonuc_decimal.setText(Integer.toOctalString(decimal_input));

    }
    private void decimalToBinary() {
        decimal_input=Integer.parseInt(et_decimal.getText().toString());
        tv_sonuc_decimal.setText(Integer.toBinaryString(decimal_input));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}