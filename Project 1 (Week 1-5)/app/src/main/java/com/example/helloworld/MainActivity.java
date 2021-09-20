package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    // Braces must be on seperate line
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button submitButton = findViewById(R.id.button_post_chatter);
        submitButton.setOnClickListener(this);

        Button launchButton = findViewById(R.id.button_view_posts);
        launchButton.setOnClickListener(this);

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }
    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.button_post_chatter:
            {
                EditText editText = findViewById(R.id.edit_text_message);
                String message = editText.getText().toString();

                postToChatter(message);

                //Toast.makeText(this, "Text = " + message,Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.button_view_posts:
            {
                EditText editText = findViewById(R.id.edit_text_message);
                String message = editText.getText().toString();

//                Bundle bundle = new Bundle();
//                bundle.putString("PREFIX", "You typed: ");
//                bundle.putString("MESSAGE", message);

                Intent intent = new Intent(this, ViewChatterActivity.class);
//                intent.putExtras(bundle);
                startActivity(intent);
                break;
            }
        }
    }

    private void postToChatter(String message)
    {
        try
        {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost("http://www.youcode.ca/JitterServlet");
            List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            parameters.add(new BasicNameValuePair("DATA", message));
            parameters.add(new BasicNameValuePair("LOGIN_NAME", "Chris"));
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters);
            post.setEntity(formEntity);
            client.execute(post);
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Error: " + e, Toast.LENGTH_LONG).show();
        }
    }
}
