package com.example.personalhandbook;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class EmailsAndPasswordsFragment extends Fragment {
    private List<ModelClass> linklistModelClass;
    ModelClass modelClass;
    EditText domain_name,ep_email,ep_password,search_text_emailpassword;
    Button add_emailPassword,edit_emailpassword,delete_emailpassword,search_emailpassword;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private EmailPasswordAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.emails_passwords,container,false);

        domain_name=view.findViewById(R.id.emailpassword_domain_name);
        ep_email=(EditText)view.findViewById(R.id.emailpassword_email);
        ep_password=(EditText) view.findViewById(R.id.emailpassword_password);
        add_emailPassword=(Button) view.findViewById(R.id.emailpassword_add);
        edit_emailpassword=(Button) view.findViewById(R.id.emailpassword_edit);
        delete_emailpassword=(Button) view.findViewById(R.id.emailpassword_delete);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        search_emailpassword=(Button) view.findViewById(R.id.email_search);
        search_text_emailpassword=(EditText) view.findViewById(R.id.emailpassword_search_text);
        /* AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Add Email&Password");
                //builder.setMessage("Update or delete user?");
                builder.
                builder.setMessage(modelClass.getItemAddress());
                builder.
               //builder.setMessage(modelClass.getItemDetails());

                builder.create().show();*/
        linklistModelClass= new LinkedList<>();
        final LoadEmailFromDataAccessLayer loadEmailFromDataAccessLayer=new LoadEmailFromDataAccessLayer();
        loadEmailFromDataAccessLayer.execute("loadEmail.php");
        //initialize the variables




        add_emailPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(domain_name.getText().toString().equals("")) && !(ep_password.getText().toString().equals("")) &&!(ep_email.getText().toString().equals(""))) {
                    Session session = new Session(getContext());
                    PassEmailToDataAccessLayer saveData = new PassEmailToDataAccessLayer();
                    saveData.execute("saveEmail.php", session.getemail(), domain_name.getText().toString(),
                            ep_email.getText().toString(), ep_email.getText().toString());
                    Toast.makeText(getContext(), "Successfully Added", Toast.LENGTH_LONG).show();

                    modelClass = new ModelClass();

                    modelClass.setItem_Domain_Name(domain_name.getText().toString());
                    modelClass.setItem_Save_Email(ep_email.getText().toString());
                    modelClass.setItem_Save_Password(ep_password.getText().toString());
                    adapter.add(adapter.getItemCount(),modelClass);
                    domain_name.setText("");
                    ep_email.setText("");
                    ep_password.setText("");
                }else {
                    Toast.makeText(getContext(),"Must fill in all the details",Toast.LENGTH_LONG).show();
                }
            }
        });
       edit_emailpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean found=false;
                if(!(domain_name.getText().toString().equals("")) && !(ep_password.getText().toString().equals("")) &&!(ep_email.getText().toString().equals(""))) {
                    Session session = new Session(getContext());
                    // Checking if card number exists
                    for(int i=0;i<linklistModelClass.size();i++) {
                        final ModelClass modelClass = linklistModelClass.get(i);
                        if (modelClass.getItem_Domain_Name().equals(domain_name.getText().toString())) {
                            EditEmailToDataAccessLayer update = new EditEmailToDataAccessLayer();
                            update.execute("updateEmail.php", session.getemail(), domain_name.getText().toString(),
                                    ep_email.getText().toString(), ep_email.getText().toString());
                            Toast.makeText(getContext(), "Successfully Updated", Toast.LENGTH_LONG).show();


                            linklistModelClass.clear();
                            LoadEmailFromDataAccessLayer loadEmailFromDataAccessLayer = new LoadEmailFromDataAccessLayer();
                            loadEmailFromDataAccessLayer.execute("loadEmail.php");
                            domain_name.setText("");
                            ep_email.setText("");
                            ep_password.setText("");
                            found=true;
                        }

                    }
                    if(found==false)Toast.makeText(getContext(), "Email doesn't exist ", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getContext(),"Must Enter all the details",Toast.LENGTH_LONG).show();
                }
            }

        });
        delete_emailpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(domain_name.getText().toString().equals("")) ) {
                    Session session = new Session(getContext());
                    DeleteEmailViaDataAccessLayer deleteData = new DeleteEmailViaDataAccessLayer();
                    deleteData.execute("deleteEmail.php", session.getemail(), domain_name.getText().toString());
                    Toast.makeText(getContext(), "Successfully Deleted", Toast.LENGTH_LONG).show();

                    linklistModelClass.clear();
                    LoadEmailFromDataAccessLayer loadEmailFromDataAccessLayer=new LoadEmailFromDataAccessLayer();
                    loadEmailFromDataAccessLayer.execute("loadEmail.php");
                    domain_name.setText("");
                    ep_email.setText("");
                    ep_password.setText("");
                }else {
                    Toast.makeText(getContext(),"Must Enter the Domain name",Toast.LENGTH_LONG).show();
                }
            }
        });
        search_emailpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean found=false;
                if(!(search_text_emailpassword.getText().toString().equals("")) ) {
                    //Session session = new Session(getContext());
                    // Checking if card number exists
                    for(int i=0;i<linklistModelClass.size();i++) {
                        final ModelClass modelClass = linklistModelClass.get(i);
                        if (modelClass.getItem_Domain_Name().equals(search_text_emailpassword.getText().toString())) {
                            linklistModelClass.clear();
                            linklistModelClass.add(modelClass);
                            domain_name.setText("");
                            ep_email.setText("");
                            ep_password.setText("");
                            found=true;
                        }

                    }
                    mRecyclerView.setHasFixedSize(true);
                    // use a linear layout manager
                    mLayoutManager = new LinearLayoutManager(getContext());
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    adapter = new EmailPasswordAdapter(linklistModelClass, getContext(), mRecyclerView);
                    mRecyclerView.setAdapter(adapter);
                    if(found==false)Toast.makeText(getContext(), "Domain Name doesn't exist ", Toast.LENGTH_LONG).show();
                }else {
                    linklistModelClass.clear();
                    LoadEmailFromDataAccessLayer loadEmailFromDataAccessLayer= new LoadEmailFromDataAccessLayer();
                    loadEmailFromDataAccessLayer.execute("loadEmail.php");
                }
            }

        });
return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //initialize the variables


    }

    class PassEmailToDataAccessLayer extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... params) {
            DataAccessLayer dataAccessLayer=new DataAccessLayer();
            return dataAccessLayer.saveEmailPasswordData(params);
        }
        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
            Log.d("Information loaded ", json);

        }
    }

    class EditEmailToDataAccessLayer extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... params) {
            DataAccessLayer dataAccessLayer=new DataAccessLayer();
            return dataAccessLayer.editEmailPasswordData(params);
        }
        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
            Log.d("Information loaded ", json);

        }
    }

     class DeleteEmailViaDataAccessLayer extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... params) {
            DataAccessLayer dataAccessLayer=new DataAccessLayer();
            return dataAccessLayer.deleteEmailPasswordData(params);
        }
        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
            Log.d("Information deleted ", json);

        }
    }

    class LoadEmailFromDataAccessLayer extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... params) {
            DataAccessLayer dataAccessLayer=new DataAccessLayer();
            return dataAccessLayer.loadEmailPasswordData(params);
        }
        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
            Log.d("Information loaded ", json);

            try {
                JSONArray jsonArray = new JSONArray(json);
                Session session=new Session(getContext());
                for(int counter = 0; counter < jsonArray.length(); counter++)
                {
                    JSONObject jsonObject = jsonArray.getJSONObject(counter);

                    if(session.getemail().equals(jsonObject.getString("email"))){

                        modelClass = new ModelClass();

                        modelClass.setItem_Domain_Name(jsonObject.getString("domain_name"));
                        modelClass.setItem_Save_Email(jsonObject.getString("save_email"));
                        modelClass.setItem_Save_Password(jsonObject.getString("save_password"));
                        linklistModelClass.add(modelClass);
                    }


                    /*TextView tv = new TextView(MainActivity.this);
                    tv.setText(jsonObject.getString("firstName") + jsonObject.getString("lastname"));
                    layout.addView(tv);*/
                }
                mRecyclerView.setHasFixedSize(true);
                // use a linear layout manager
                mLayoutManager = new LinearLayoutManager(getContext());
                mRecyclerView.setLayoutManager(mLayoutManager);
                adapter = new EmailPasswordAdapter(linklistModelClass, getContext(), mRecyclerView);
                mRecyclerView.setAdapter(adapter);




            } catch (Exception e) {

                e.printStackTrace();
            }


        }

    }
}
