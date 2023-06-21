package com.example.generator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.Hashtable;


public class QRActivity extends AppCompatActivity {
    Button qrButton;
    ImageView qrOutput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        qrButton = findViewById(R.id.qrButton);
        qrOutput = findViewById(R.id.qrOutput);

        qrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sText = getIntent().getExtras().getString("ticket");
                Hashtable hints = new Hashtable();
                hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
                MultiFormatWriter writer = new MultiFormatWriter();
                try {
                    BitMatrix matrix = writer.encode(sText, BarcodeFormat.QR_CODE, 350, 350, hints);
                    BarcodeEncoder encoder = new BarcodeEncoder();
                    Bitmap bitmap = encoder.createBitmap(matrix);
                    qrOutput.setImageBitmap(bitmap);
                    InputMethodManager manager = (InputMethodManager) getSystemService(
                            Context.INPUT_METHOD_SERVICE
                    );
                    manager.hideSoftInputFromWindow(qrOutput.getApplicationWindowToken(), 0);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}