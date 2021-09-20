package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class ReceiveActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        Bundle bundle = this.getIntent().getExtras();
        String prefix = bundle.getString("PREFIX");
        String message = bundle.getString("MESSAGE");

        EditText editText = findViewById(R.id.edit_text_receive_message);
        editText.setText(prefix + message);
    }
}
