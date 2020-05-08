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

public class VisitingCardsFragments extends Fragment {
    private List<ModelClass> linklistModelClass;
    ModelClass modelClass;
    EditText vcard_name,vcard_organization,vcard_address, vcard_search_text;
    Button vcard_add,vcard_edit,vcard_delete,vcard_search;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private VisitingCardAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.visiting_cards,container,false);

        vcard_name=view.findViewById(R.id.vcard_name);
        vcard_address=(EditText)view.findViewById(R.id.vcard_address);
        vcard_organization=(EditText) view.findViewById(R.id.vcard_organization);
        vcard_add=(Button) view.findViewById(R.id.vcard_add);
        vcard_edit=(Button) view.findViewById(R.id.vcard_edit);
        vcard_delete=(Button) view.findViewById(R.id.vcard_delete);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        vcard_search=(Button) view.findViewById(R.id.vcard_search);
        vcard_search_text=(EditText)view.findViewById(R.id.vcard_search_text);


        linklistModelClass= new LinkedList<>();
        LoadVcardFromDataAccessLayer loadVcardFromDataAccessLayer=new LoadVcardFromDataAccessLayer();
        loadVcardFromDataAccessLayer.execute("loadVcard.php");
        //initialize the variables




        vcard_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(vcard_name.getText().toString().equals("")) && !(vcard_organization.getText().toString().equals("")) &&!(vcard_address.getText().toString().equals(""))) {
                    Session session = new Session(getContext());
                   PassVcardToDataAccessLayer saveData = new PassVcardToDataAccessLayer();
                  saveData.execute("saveVcard.php", session.getemail(), vcard_name.getText().toString(),
                         vcard_address.getText().toString(), "vcard_picture", vcard_organization.getText().toString());
                    Toast.makeText(getContext(), "Successfully Added", Toast.LENGTH_LONG).show();

                    modelClass = new ModelClass();

                    modelClass.setItem_vcard_name(vcard_name.getText().toString());
                    modelClass.setItem_vcard_organization(vcard_organization.getText().toString());
                    modelClass.setItem_vcard_address(vcard_address.getText().toString());
                    adapter.add(adapter.getItemCount(),modelClass);
                    vcard_name.setText("");
                    vcard_organization.setText("");
                    vcard_address.setText("");
                }else {
                    Toast.makeText(getContext(),"Must fill in all the details",Toast.LENGTH_LONG).show();
                }
            }
        });
        vcard_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(vcard_name.getText().toString().equals("")) ) {
                    Session session = new Session(getContext());
                    DeleteVcardViaDataAccessLayer deleteVcardViaDataAccessLayer= new DeleteVcardViaDataAccessLayer();
                    deleteVcardViaDataAccessLayer.execute("deleteVcard.php", session.getemail(), vcard_name.getText().toString());
                    Toast.makeText(getContext(), "Successfully Deleted", Toast.LENGTH_LONG).show();

                    linklistModelClass.clear();
                    LoadVcardFromDataAccessLayer loadVcardFromDataAccessLayer=new LoadVcardFromDataAccessLayer();
                    loadVcardFromDataAccessLayer.execute("loadVcard.php");
                    vcard_name.setText("");
                    vcard_organization.setText("");
                    vcard_address.setText("");
                }else {
                    Toast.makeText(getContext(),"Must enter the Card name",Toast.LENGTH_LONG).show();
                }
            }
        });

        vcard_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean found=false;
                if(!(vcard_name.getText().toString().equals("")) && !(vcard_organization.getText().toString().equals("")) &&!(vcard_address.getText().toString().equals(""))) {
                    Session session = new Session(getContext());
                    // Checking if card number exists
                    for(int i=0;i<linklistModelClass.size();i++) {
                        final ModelClass modelClass = linklistModelClass.get(i);
                        if (modelClass.getItem_vcard_name().equals(vcard_name.getText().toString())) {
                            EditVcardToDataAccessLayer updateData = new EditVcardToDataAccessLayer();
                           updateData.execute("updateVcard.php", session.getemail(), vcard_name.getText().toString(),
                                    vcard_address.getText().toString(), "vcard_picture", vcard_organization.getText().toString());
                            Toast.makeText(getContext(), "Successfully Updated", Toast.LENGTH_LONG).show();


                            linklistModelClass.clear();
                            LoadVcardFromDataAccessLayer loadCreditDebitFromDataAccessLayer = new LoadVcardFromDataAccessLayer();
                            loadCreditDebitFromDataAccessLayer.execute("loadVcard.php");
                            vcard_name.setText("");
                            vcard_organization.setText("");
                            vcard_address.setText("");
                            found=true;
                        }

                    }
                    if(found==false)Toast.makeText(getContext(), "Card Name doesn't exist ", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getContext(),"Must Enter all the details",Toast.LENGTH_LONG).show();
                }
            }

        });

        vcard_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean found=false;
                if(!(vcard_search_text.getText().toString().equals("")) ) {
                    Session session = new Session(getContext());
                    // Checking if card number exists
                    for(int i=0;i<linklistModelClass.size();i++) {
                        final ModelClass modelClass = linklistModelClass.get(i);
                        if (modelClass.getItem_vcard_name().equals(vcard_search_text.getText().toString())) {
                            linklistModelClass.clear();
                           linklistModelClass.add(modelClass);
                            vcard_name.setText("");
                            vcard_organization.setText("");
                            vcard_address.setText("");
                            found=true;
                        }

                    }
                    mRecyclerView.setHasFixedSize(true);
                    // use a linear layout manager
                    mLayoutManager = new LinearLayoutManager(getContext());
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    adapter = new VisitingCardAdapter(linklistModelClass, getContext(), mRecyclerView);
                    mRecyclerView.setAdapter(adapter);
                    if(found==false)Toast.makeText(getContext(), "Card Name doesn't exist ", Toast.LENGTH_LONG).show();
                }else {
                    linklistModelClass.clear();
                    LoadVcardFromDataAccessLayer loadCreditDebitFromDataAccessLayer = new LoadVcardFromDataAccessLayer();
                    loadCreditDebitFromDataAccessLayer.execute("loadVcard.php");
                }
            }

        });

        return view;
    }

    class PassVcardToDataAccessLayer extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... params) {
            DataAccessLayer dataAccessLayer=new DataAccessLayer();
            return dataAccessLayer.saveVisitingCardData(params);
        }
        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
            Log.d("Information loaded ", json);

        }
    }
    class EditVcardToDataAccessLayer extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... params) {
            DataAccessLayer dataAccessLayer=new DataAccessLayer();
            return dataAccessLayer.editVisitingCardData(params);
        }
        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
            Log.d("Information loaded ", json);

        }
    }
    class DeleteVcardViaDataAccessLayer extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... params) {
            DataAccessLayer dataAccessLayer=new DataAccessLayer();
            return dataAccessLayer.deleteVisitingCardData(params);
        }
        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
            Log.d("Information deleted ", json);

        }
    }

    class LoadVcardFromDataAccessLayer extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... params) {
            DataAccessLayer dataAccessLayer=new DataAccessLayer();
            return dataAccessLayer.loadVisitingCardData(params);
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

                        modelClass.setItem_vcard_name(jsonObject.getString("vcard_name"));
                        modelClass.setItem_vcard_organization(jsonObject.getString("organization"));
                        modelClass.setItem_vcard_address(jsonObject.getString("address"));
                      //  modelClass.set
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
                adapter = new VisitingCardAdapter(linklistModelClass, getContext(), mRecyclerView);
                mRecyclerView.setAdapter(adapter);




            } catch (Exception e) {

                e.printStackTrace();
            }


        }

    }
}
