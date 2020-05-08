package com.example.personalhandbook;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class RegisterActivity extends AppCompatActivity {

    EditText reg_name,reg_email,reg_phone,reg_password,reg_confirm_password;
    Button reg_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        reg_name=(EditText)findViewById(R.id.reg_name);
        reg_email=(EditText)findViewById(R.id.reg_email);
        reg_password=(EditText)findViewById(R.id.reg_password);
        reg_confirm_password=(EditText)findViewById(R.id.reg_confirm_password);
        reg_phone=(EditText)findViewById(R.id.reg_phone);

        reg_submit=(Button)findViewById(R.id.reg_submit);
        reg_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Md5Hashing md5Hashing=new Md5Hashing();
                String reg_md5password=md5Hashing.convertPassMd5(reg_password.getText().toString());
                PassToDataAccessLayer saveData=new PassToDataAccessLayer();
                saveData.execute("saveUser.php",reg_name.getText().toString(),reg_email.getText().toString(),reg_phone.getText().toString(),reg_md5password);
                Toast.makeText(getApplicationContext(),"Successfully Registered",Toast.LENGTH_LONG).show();
                reg_name.setText("");
                reg_email.setText("");
                reg_confirm_password.setText("");
                reg_password.setText("");
                reg_phone.setText("");
            }
        });


    }

    class PassToDataAccessLayer extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... params) {
            DataAccessLayer dataAccessLayer=new DataAccessLayer();
            return dataAccessLayer.saveUserData(params);
        }
        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
            Log.d("Information loaded ", json);

        }
    }
}
