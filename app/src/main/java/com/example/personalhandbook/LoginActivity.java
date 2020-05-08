package com.example.personalhandbook;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    TextView register,forgot,login;
    Button submit,send;
    EditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        register=(TextView)findViewById(R.id.register);
        forgot=(TextView)findViewById(R.id.forgot);
        email=(EditText)findViewById(R.id.log_email);
        password=(EditText)findViewById(R.id.log_password);
        login=(TextView)findViewById(R.id.Login);
        submit=(Button)findViewById(R.id.log_submit);
        send=(Button)findViewById(R.id.send);

        send.setVisibility(View.INVISIBLE);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent register = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(register);
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email.setText("");
                submit.setVisibility(View.INVISIBLE);
                register.setVisibility(View.INVISIBLE);
                forgot.setVisibility(View.INVISIBLE);
                password.setVisibility(View.INVISIBLE);
                email.setHint("Enter your email Address");
                login.setText("Forgot password");
                send.setVisibility(View.VISIBLE);


            }




        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   if (!(email.getText().toString().equals(" ")) && email.getText().toString().contains("imdad") && password.getText().toString().contains("in")) {
                    Toast.makeText(getApplicationContext(), "LoggedIn. Welcome!", Toast.LENGTH_LONG).show();
                    Intent register = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(register);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Invalid Username or password",Toast.LENGTH_LONG).show();
                }*/

            GetDataFromAccessLayer getDataFromAccessLayer=new GetDataFromAccessLayer();
            getDataFromAccessLayer.execute("loadUser.php");

            }

        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!(email.getText().toString().equals(" ")) && email.getText().toString().contains("@") && email.getText().toString().contains(".com")) {


                    Toast.makeText(getApplicationContext(), "An link has been send in your email to reset you password", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Enter a valid Email address",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    class GetDataFromAccessLayer extends AsyncTask<String, String, String>
    {

        @Override
        protected String doInBackground(String... params) {
            DataAccessLayer dataAccessLayer=new DataAccessLayer();
            return dataAccessLayer.loadUserData(params);
        }

        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
            Md5Hashing md5Hashing=new Md5Hashing();
            Log.d("Information loaded ", json);
            int count=0;
            try {
                JSONArray jsonArray = new JSONArray(json);

                for(int counter = 0; counter < jsonArray.length(); counter++)
                {
                    JSONObject jsonObject = jsonArray.getJSONObject(counter);
                    String mdpassword=md5Hashing.convertPassMd5(password.getText().toString());
                    if(((email.getText().toString()).equals(jsonObject.getString("email"))) && (mdpassword.equals(jsonObject.getString("password")))){
                        Session session;//global variable
                        session = new Session(getApplicationContext()); //in oncreate
                        //and now we set sharedpreference then use this like
                        session.setemail(email.getText().toString());

                        Toast.makeText(getApplicationContext(), "LoggedIn. Welcome!", Toast.LENGTH_LONG).show();
                        Intent register = new Intent(getApplicationContext(),MainActivity.class);

                        startActivity(register);
                        count++;
                }


                    /*TextView tv = new TextView(MainActivity.this);
                    tv.setText(jsonObject.getString("firstName") + jsonObject.getString("lastname"));
                    layout.addView(tv);*/
                }
                if(count==0){
                    Toast.makeText(getApplicationContext(),"Invalid Username or password",Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {

                e.printStackTrace();
            }

        }
    }
}
