package com.example.generator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.security.KeyPair;

public class MainActivity extends AppCompatActivity {
    EditText qrText;
    Button qrButton;
    ImageView qrOutput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qrText = findViewById(R.id.qrText);
        qrButton = findViewById(R.id.qrButton);
        qrOutput = findViewById(R.id.qrOutput);

        qrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sText = qrText.getText().toString().trim();
                MultiFormatWriter writer = new MultiFormatWriter();
                try {
                    BitMatrix matrix = writer.encode(sText, BarcodeFormat.QR_CODE, 350,350);
                    BarcodeEncoder encoder =  new BarcodeEncoder();
                    Bitmap bitmap = encoder.createBitmap(matrix);
                    qrOutput.setImageBitmap(bitmap);
                    InputMethodManager manager = (InputMethodManager) getSystemService(
                            Context.INPUT_METHOD_SERVICE
                    );
                    manager.hideSoftInputFromWindow(qrOutput.getApplicationWindowToken(),0);
                 } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}