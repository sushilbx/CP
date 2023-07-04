package com.example.cp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cp.Modal.DepositeListModel;
import com.example.cp.Modal.WalletWithdrawlModel;
import com.example.cp.R;
import com.example.cp.databinding.DepositeItemBinding;
import com.example.cp.databinding.WitdrawItemBinding;

import java.util.List;

public class WithdrawAdapter extends RecyclerView.Adapter<WithdrawAdapter.MyViewHolder> {
    public List<WalletWithdrawlModel.Withdrawl> psy;
    public Context context;

    public WithdrawAdapter(List<WalletWithdrawlModel.Withdrawl> list, Context context) {
        this.psy = list;
        this.context = context;
    }

    @Override
    public WithdrawAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.witdraw_item, parent, false);
        return new WithdrawAdapter.MyViewHolder(WitdrawItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(final WithdrawAdapter.MyViewHolder holder, final int position) {


        holder.b.tvSr.setText(psy.get(position).id);
        holder.b.tvType.setText(psy.get(position).transfer_type);
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
        private WitdrawItemBinding b;

        public MyViewHolder(WitdrawItemBinding b) {
            super(b.getRoot());
            this.b = b;
        }
    }
}

