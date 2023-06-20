package com.example.generator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.security.KeyPair;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button ticketButton;
    EditText emailText;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        ticketButton = findViewById(R.id.TicketButton);
        emailText = findViewById(R.id.emailText);
        ArrayList<String> stringArrayList = new ArrayList<>();
        Context context = this;


        ticketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection connection = DatabaseConnection.connection();
                List<Ticket> ticketList = DatabaseConnection.read(connection, emailText.getText().toString());
                for (Ticket ticket : ticketList) {
                    stringArrayList.add(ticket.listData());
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, R.layout.list_view, stringArrayList);
                listView.setAdapter(arrayAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        System.out.println(position);
                        Ticket ticket = ticketList.get(position);
                        Intent intent = new Intent(context, QRActivity.class);
                        intent.putExtra("ticket", ticket.toString());
                        startActivity(intent);
                    }
                });
            }


        });
    }

}