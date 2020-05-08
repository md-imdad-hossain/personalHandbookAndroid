package com.example.personalhandbook;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;


public class EmailPasswordAdapter extends RecyclerView.Adapter<EmailPasswordAdapter.ViewHolder> {
    private List<ModelClass> mModelClass;
    private Context mContext;
    private RecyclerView mRecyclerV;
    EditText dn;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvFieldOne;
        public TextView tvFieldTwo;
        public TextView tvFieldThree;
        //public ImageView itemImageImgV;



        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            tvFieldOne = (TextView) v.findViewById(R.id.fieldOne);
            tvFieldTwo = (TextView) v.findViewById(R.id.fieldTwo);
           tvFieldThree = (TextView) v.findViewById(R.id.fieldThree);



        }
    }

    public void add(int position, ModelClass modelClass) {
        mModelClass.add(position, modelClass);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        mModelClass.remove(position);
        notifyItemRemoved(position);
    }



    // Provide a suitable constructor (depends on the kind of dataset)
    public EmailPasswordAdapter(List<ModelClass> myDataset, Context context, RecyclerView recyclerView) {
        mModelClass = myDataset;
        mContext = context;
        mRecyclerV = recyclerView;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public EmailPasswordAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.custom_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final ModelClass modelClass = mModelClass.get(position);
        holder.tvFieldOne.setText("Domain Name: " + modelClass.getItem_Domain_Name());
        holder.tvFieldTwo.setText("Email: " + modelClass.getItem_Save_Email());
        holder.tvFieldThree.setText("Password: " + modelClass.getItem_Save_Password());


        //listen to single view layout click

    /*    holder.layout.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
               remove(position);





                return false;
            }
        });*/

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // domain_name.setText("Domain Name: " + modelClass.getItem_Domain_Name());
             /*   Intent intent = new Intent(mContext,DetailsActivity.class);
                Bundle extras = new Bundle();
                extras.putString("extra_name",modelClass.getItemName());
                extras.putString("extra_address",modelClass.getItemAddress());
                extras.putString("extra_image",modelClass.getItemImage());
                extras.putString("extra_details",modelClass.getItemDetails());
                extras.putString("extra_website",modelClass.getItemWebsite());
                extras.putString("extra_phone",modelClass.getItemPhone());

                intent.putExtras(extras);
                mContext.startActivity(intent);*/

            }

        });

      /*  holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle(modelClass.getItemName());
                //builder.setMessage("Update or delete user?");
                builder.setIcon(Integer.parseInt(modelClass.getItemImage()));
                builder.setMessage(modelClass.getItemAddress());
                builder.setMessage(modelClass.getItemDetails());

                builder.create().show();
                return false;
            }
        });*/


    }




    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mModelClass.size();
    }



}




























