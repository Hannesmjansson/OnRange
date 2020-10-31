package com.example.qrny;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button scanBtn;
    TextView informationOmSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanBtn = findViewById(R.id.scanBtn);
        scanBtn.setOnClickListener(this);

        informationOmSensor = findViewById(R.id.textView);

    }

    @Override
    public void onClick(View v) {
        scanCode();
    }

    private void scanCode(){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("SCANNING CODE");
        integrator.initiateScan();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents() != null){
                String ASD = result.getContents();
                String [] arrSplit = ASD.split(",");        // ta bort o funkar

              //  informationOmSensor.setText(result.getContents() + "\n" +  ASD.length());     // vetefan vad detta gjorde
               //     informationOmSensor.setText(ASD);             // aktivera
                informationOmSensor.setText("Dev EUI: " + arrSplit[0] + "\n" + "Default App Key: " + arrSplit[1] + "\n" + "Default App EUI: " + arrSplit[2]);
            }


            else {
                Toast.makeText(this, "No results", Toast.LENGTH_LONG).show();
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}