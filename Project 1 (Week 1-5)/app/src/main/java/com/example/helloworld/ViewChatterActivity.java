package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

public class ViewChatterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_chatter);

        getFromChatter();
    }

    private void getFromChatter()
    {
        BufferedReader in = null;
        TextView textView = findViewById(R.id.text_view_read);
        try
        {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI("http://www.youcode.ca/JSONServlet"));
            HttpResponse response = client.execute(request);

            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            String line = "";

            while((line = in.readLine()) != null)
            {
                textView.append(line + "\n");
            }
            in.close();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Error " + e, Toast.LENGTH_LONG).show();
        }
    }
}
