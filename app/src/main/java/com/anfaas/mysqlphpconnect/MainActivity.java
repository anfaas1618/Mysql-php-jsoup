package com.anfaas.mysqlphpconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {
EditText username,password,email;
Button signup;
public  ProgressBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=findViewById(R.id.user);
        password =findViewById(R.id.pass);
        email=findViewById(R.id.mail);
        bar=findViewById(R.id.progressBar);
        signup=findViewById(R.id.button);
        bar.setVisibility(View.INVISIBLE);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    register(username.getText().toString().trim(),
                            password.getText().toString().trim(),
                            email.getText().toString().trim());
                    bar.setVisibility(View.VISIBLE);
            }
        });
    }


    {

    }

    private void register(String username, String password, String email)  {
        String url="http://anfaas-com.stackstaging.com/UserRegistration/register.php?username="
                +  username+
                "&password="
                +  password+
                "&email="
                + email;
       new  task().execute(url);
        Log.i("tag",url);
    }
     public    class  task extends AsyncTask<String,Void,Void>
    {

        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
           bar.setVisibility(View.GONE);
        }

        @Override
        protected Void doInBackground(String... strings) {
            String url=strings[0];
            Log.i("yes",url);
            try {
      Document doc=          Jsoup.connect(url).get();
          //      Toast.makeText(MainActivity.this, doc.toString(), Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
