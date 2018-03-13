package com.example.abc.test_retrofit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.abc.test_retrofit.R;
import com.example.abc.test_retrofit.response.UserResponse;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {

    private Context context;
    private UserResponse response;

    public ContactListAdapter(Context context, UserResponse response){
        this.context=context;
        this.response=response;
    }

    @Override
    public ContactListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_contact_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactListAdapter.ViewHolder viewHolder, int position) {

        viewHolder.text1.setText(""+response.getContact().get(position).getName());
        viewHolder.text2.setText(""+response.getContact().get(position).getEmail());
        viewHolder.text3.setText(""+response.getContact().get(position).getMobile());
        viewHolder.text4.setText(""+response.getContact().get(position).getCompany());

    }

    @Override
    public int getItemCount() {
        return response.getContact().size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.main_layout)
        LinearLayout main_layout;
        @Bind(R.id.text1)
        TextView text1;
        @Bind(R.id.text2)
        TextView text2;
        @Bind(R.id.text3)
        TextView text3;
        @Bind(R.id.text4)
        TextView text4;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}