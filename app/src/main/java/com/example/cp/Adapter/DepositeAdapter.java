package com.example.cp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cp.Modal.DepositeListModel;
import com.example.cp.Modal.PlateformModel;
import com.example.cp.R;
import com.example.cp.databinding.DepositeItemBinding;
import com.example.cp.databinding.MyPlateformItemBinding;

import java.util.List;

public class DepositeAdapter extends RecyclerView.Adapter<DepositeAdapter.MyViewHolder> {
    public List<DepositeListModel.Datum> psy;
    public Context context;

    public DepositeAdapter(List<DepositeListModel.Datum> list, Context context) {
        this.psy = list;
        this.context = context;
    }

    @Override
    public DepositeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.deposite_item, parent, false);
        return new DepositeAdapter.MyViewHolder(DepositeItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(final DepositeAdapter.MyViewHolder holder, final int position) {


        holder.b.tvSr.setText(psy.get(position).id);
        holder.b.tvRef.setText(psy.get(position).tn_ref_no);
        holder.b.tvAmount.setText(psy.get(position).amount);
        holder.b.tvStatus.setText(psy.get(position).status);
        holder.b.tvCreate.setText(psy.get(position).created_at);
      //  Glide.with(context).load("https://happyu.co.in/storage/blog/upload/" + psy.get(position).image).into(holder.b.ivImage);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, BlogDetailsActivity.class);
//                intent.putExtra("modelblog", new Gson().toJson(psy.get(position)));
//                context.startActivity(intent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return this.psy.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private DepositeItemBinding b;

        public MyViewHolder(DepositeItemBinding b) {
            super(b.getRoot());
            this.b = b;
        }
    }
}

