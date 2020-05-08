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

public class CreditDebitFragment extends Fragment {
    private List<ModelClass> linklistModelClass;
    ModelClass modelClass;
    EditText cdcard_number,cdcard_bank,cdcard_name,cdcard_pin,cdcard_search_text;
    Button add_creditdebit,edit_creditdebit,delete_creditdebit,search_creditdebit;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CreditDebitAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.debit_credit_cards,container,false);

        cdcard_bank=view.findViewById(R.id.credit_debit_bank);
        cdcard_number=(EditText)view.findViewById(R.id.credit_debit_number);
        cdcard_name=(EditText) view.findViewById(R.id.credit_debit_name);
        cdcard_pin=(EditText) view.findViewById(R.id.credit_debit_pin);
        add_creditdebit=(Button) view.findViewById(R.id.creditdebit_add);
        edit_creditdebit=(Button) view.findViewById(R.id.creditdebit_edit);
        delete_creditdebit=(Button) view.findViewById(R.id.creditdebit_delete);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        cdcard_search_text=(EditText) view.findViewById(R.id.creditdebit_search_text);
        search_creditdebit=(Button) view.findViewById(R.id.credit_debit_search);

        linklistModelClass= new LinkedList<>();
        LoadCreditDebitFromDataAccessLayer load=new LoadCreditDebitFromDataAccessLayer();
        load.execute("loadCreditdebit.php");

        add_creditdebit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(cdcard_bank.getText().toString().equals("")) && !(cdcard_number.getText().toString().equals("")) &&!(cdcard_name.getText().toString().equals(""))) {
                    Session session = new Session(getContext());
                    PassCreditDebitToDataAccessLayer saveData = new PassCreditDebitToDataAccessLayer();
                    saveData.execute("saveCreditdebit.php", session.getemail(), cdcard_bank.getText().toString(),
                            cdcard_name.getText().toString(), cdcard_number.getText().toString(),cdcard_pin.getText().toString(),"card_picture");
                    Toast.makeText(getContext(), "Successfully Added", Toast.LENGTH_LONG).show();

                    modelClass = new ModelClass();

                    modelClass.setItem_cdcard_bank(cdcard_bank.getText().toString());
                    modelClass.setItem_cdCard_number(cdcard_number.getText().toString());
                    modelClass.setItem_cdcard_name(cdcard_name.getText().toString());
                    modelClass.setItem_cdcard_pin(cdcard_pin.getText().toString());
                    adapter.add(adapter.getItemCount(),modelClass);
                    cdcard_name.setText("");
                    cdcard_pin.setText("");
                    cdcard_number.setText("");
                    cdcard_bank.setText("");
                }else {
                    Toast.makeText(getContext(),"Must fill in all the details",Toast.LENGTH_LONG).show();
                }
            }
        });
        delete_creditdebit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean found=false;
                if(!(cdcard_number.getText().toString().equals("")) ) {
                    Session session = new Session(getContext());
                    // Checking if card number exists
                   for(int i=0;i<linklistModelClass.size();i++) {
                       final ModelClass modelClass = linklistModelClass.get(i);
                       if (modelClass.getItem_cdCard_number().equals(cdcard_number.getText().toString())) {
                           DeleteCreditDebitViaDataAccessLayer deleteData = new DeleteCreditDebitViaDataAccessLayer();
                           deleteData.execute("deleteCreditdebit.php", session.getemail(), cdcard_number.getText().toString());
                           Toast.makeText(getContext(), "Successfully Deleted", Toast.LENGTH_LONG).show();


                           linklistModelClass.clear();
                           LoadCreditDebitFromDataAccessLayer loadCreditDebitFromDataAccessLayer = new LoadCreditDebitFromDataAccessLayer();
                           loadCreditDebitFromDataAccessLayer.execute("loadCreditdebit.php");
                           cdcard_name.setText("");
                           cdcard_pin.setText("");
                           cdcard_number.setText("");
                           cdcard_bank.setText("");
                           found=true;
                       }

                   }
                    if(found==false)Toast.makeText(getContext(), "Card Number doesn't exist ", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getContext(),"Must Enter The Card number",Toast.LENGTH_LONG).show();
                }
            }
        });

        edit_creditdebit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean found=false;
                if(!(cdcard_bank.getText().toString().equals("")) && !(cdcard_number.getText().toString().equals("")) &&!(cdcard_name.getText().toString().equals(""))) {
                    Session session = new Session(getContext());
                    // Checking if card number exists
                    for(int i=0;i<linklistModelClass.size();i++) {
                        final ModelClass modelClass = linklistModelClass.get(i);
                        if (modelClass.getItem_cdCard_number().equals(cdcard_number.getText().toString())) {
                            EditCreditDebitViaDataAccessLayer updateData = new EditCreditDebitViaDataAccessLayer();
                            updateData.execute("updateCreditdebit.php", session.getemail(), cdcard_bank.getText().toString(),
                                    cdcard_name.getText().toString(), cdcard_number.getText().toString(),cdcard_pin.getText().toString(),"card_picture");
                            Toast.makeText(getContext(), "Successfully Updated", Toast.LENGTH_LONG).show();


                            linklistModelClass.clear();
                            LoadCreditDebitFromDataAccessLayer loadCreditDebitFromDataAccessLayer = new LoadCreditDebitFromDataAccessLayer();
                            loadCreditDebitFromDataAccessLayer.execute("loadCreditdebit.php");
                            cdcard_name.setText("");
                            cdcard_pin.setText("");
                            cdcard_number.setText("");
                            cdcard_bank.setText("");
                            found=true;
                        }

                    }
                    if(found==false)Toast.makeText(getContext(), "Card Number doesn't exist ", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getContext(),"Must Enter all the details",Toast.LENGTH_LONG).show();
                }
            }

        });

        search_creditdebit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean found=false;
                if(!(cdcard_search_text.getText().toString().equals("")) ) {
                    Session session = new Session(getContext());
                    // Checking if card number exists
                    for(int i=0;i<linklistModelClass.size();i++) {
                        final ModelClass modelClass = linklistModelClass.get(i);
                        if (modelClass.getItem_cdCard_number().equals(cdcard_search_text.getText().toString())) {
                            linklistModelClass.clear();
                            linklistModelClass.add(modelClass);
                            cdcard_name.setText("");
                            cdcard_pin.setText("");
                            cdcard_number.setText("");
                            cdcard_bank.setText("");
                            found=true;
                        }

                    }
                    mRecyclerView.setHasFixedSize(true);
                    // use a linear layout manager
                    mLayoutManager = new LinearLayoutManager(getContext());
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    adapter = new CreditDebitAdapter(linklistModelClass, getContext(), mRecyclerView);
                    mRecyclerView.setAdapter(adapter);
                    if(found==false)Toast.makeText(getContext(), "Card Name doesn't exist ", Toast.LENGTH_LONG).show();
                }else {
                    linklistModelClass.clear();
                    LoadCreditDebitFromDataAccessLayer loadCreditDebitFromDataAccessLayer = new LoadCreditDebitFromDataAccessLayer();
                    loadCreditDebitFromDataAccessLayer.execute("loadCreditdebit.php");
                }
            }

        });
        return view;
    }

    class PassCreditDebitToDataAccessLayer extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... params) {
            DataAccessLayer dataAccessLayer=new DataAccessLayer();
            return dataAccessLayer.saveCreditDebitData(params);
        }
        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
            Log.d("Information loaded ", json);

        }
    }

    class DeleteCreditDebitViaDataAccessLayer extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... params) {
            DataAccessLayer dataAccessLayer=new DataAccessLayer();
            return dataAccessLayer.deleteCreditDebitData(params);
        }
        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
            Log.d("Information deleted ", json);

        }
    }

    class EditCreditDebitViaDataAccessLayer extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... params) {
            DataAccessLayer dataAccessLayer=new DataAccessLayer();
            return dataAccessLayer.editCreditDebitData(params);
        }
        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
            Log.d("Information deleted ", json);

        }
    }

    class LoadCreditDebitFromDataAccessLayer extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... params) {
            DataAccessLayer dataAccessLayer=new DataAccessLayer();
            return dataAccessLayer.loadCreditDebitData(params);
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

                        modelClass.setItem_cdCard_number(jsonObject.getString("card_number"));
                        modelClass.setItem_cdcard_bank(jsonObject.getString("bank"));
                        modelClass.setItem_cdcard_name(jsonObject.getString("card_name"));
                        modelClass.setItem_cdcard_pin(jsonObject.getString("pin"));
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
                adapter = new CreditDebitAdapter(linklistModelClass, getContext(), mRecyclerView);
                mRecyclerView.setAdapter(adapter);




            } catch (Exception e) {

                e.printStackTrace();
            }


        }

    }
}
