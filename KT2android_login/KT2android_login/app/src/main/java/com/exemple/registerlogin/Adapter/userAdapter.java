package com.exemple.registerlogin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.exemple.registerlogin.Models.userModel;
import com.exemple.registerlogin.R;
import com.exemple.registerlogin.adminActivity;

import java.text.BreakIterator;
import java.util.ArrayList;

public class userAdapter extends RecyclerView.Adapter<userAdapter.viewholder>{

    ArrayList<userModel> list ;
    Context context;

    public userAdapter(ArrayList<userModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_login, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        final userModel model = list.get(position);

        holder.imageView.setImageResource(model.getImage());
        holder.name.setText(model.getName());
        holder.email.setText(model.getEmail());

        holder.sua.setOnClickListener(view -> {
            Intent intent = new Intent(context, adminActivity.class);
            intent.putExtra("image", model.getImage());
            intent.putExtra("name", model.getName());
            intent.putExtra("email", model.getEmail());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView name, email, sua, xoa;

        public viewholder(@NonNull View itemView) {

            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            sua = itemView.findViewById(R.id.txtSua);
            xoa = itemView.findViewById(R.id.txtxoa);
        }
    }
}
