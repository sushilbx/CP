package com.example.cp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cp.Modal.MyPlateformModel;
import com.example.cp.Modal.PlateformModel;
import com.example.cp.R;
import com.example.cp.databinding.MyPlateformItemBinding;

import java.util.List;

public class PlateformAdapter extends RecyclerView.Adapter<PlateformAdapter.MyViewHolder> {
    public List<PlateformModel.Datum> psy;
    public Context context;

    public PlateformAdapter(List<PlateformModel.Datum> list, Context context) {
        this.psy = list;
        this.context = context;
    }

    @Override
    public PlateformAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_plateform_item, parent, false);
        return new PlateformAdapter.MyViewHolder(MyPlateformItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(final PlateformAdapter.MyViewHolder holder, final int position) {


        holder.b.tvPeriod.setText(psy.get(position).bet_no);
        holder.b.tvNumber.setText(psy.get(position).selection);
        holder.b.tvPrice.setText(psy.get(position).contract_money);
        holder.b.tvReslt.setText(psy.get(position).bet_date);
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
        private MyPlateformItemBinding b;

        public MyViewHolder(MyPlateformItemBinding b) {
            super(b.getRoot());
            this.b = b;
        }
    }
}

