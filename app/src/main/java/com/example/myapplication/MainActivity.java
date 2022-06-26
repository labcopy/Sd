package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText = (EditText) findViewById(R.id.editText);
        Button readData = (Button) findViewById(R.id.readData);
        Button writeData = (Button) findViewById(R.id.writeData);
        Button clearData = (Button) findViewById(R.id.clearData);
        clearData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("");
            }
        });
        writeData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = (editText.getText().toString());
                File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
                File f = new File(folder,"myfile.txt");
                try {
                    f.createNewFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(f);
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                    outputStreamWriter.append(msg);
                    outputStreamWriter.close();
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        readData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg;
                StringBuilder buff = new StringBuilder();
                try {
                    File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
                    File f = new File(folder,"myfile.txt");
                    FileInputStream fileInputStream = new FileInputStream(f);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                    while((msg = bufferedReader.readLine()) != null){
                        buff.append(msg);
                    }
                    editText.setText(buff.toString());
                    bufferedReader.close();
                    fileInputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}