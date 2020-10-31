package com.example.OnRange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class Activity2 extends AppCompatActivity implements View.OnClickListener{ // implements View.OnClickListener

    private TextView InformationOmInlogg2;
    private Button scanBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        InformationOmInlogg2 = findViewById(R.id.textView2);
        scanBtn = findViewById(R.id.button2);
        scanBtn.setOnClickListener(this);


        Intent intent = getIntent();
        String text1 = intent.getStringExtra(MainActivity.Extra_text1);
        String text2 = intent.getStringExtra(MainActivity.Extra_text2);

        InformationOmInlogg2.setText(text1 + "\n" + text2);
    }

    @Override
    public void onClick(View v) {
        scanCode();
    }

    private void scanCode() {
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
                String [] arrSplit = ASD.split(",");


                // skicka till Activity3?
                InformationOmInlogg2.setText("Dev EUI: " + arrSplit[0] + "\n" + "Default App Key: " + arrSplit[1] + "\n" + "Default App EUI: " + arrSplit[2]);
            }


            else {
                Toast.makeText(this, "No results", Toast.LENGTH_LONG).show();
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}