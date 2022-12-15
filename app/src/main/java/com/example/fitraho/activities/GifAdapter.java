package com.example.fitraho.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitraho.R;
import com.example.fitraho.databinding.ChinItemBinding;

import java.util.List;

public class GifAdapter extends RecyclerView.Adapter<GifAdapter.ViewH>{

    Context context;
    List<GifModel> list;

    public GifAdapter(Context context, List<GifModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewH(LayoutInflater.from(context).inflate(R.layout.chin_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewH holder, int position) {

        holder.binding.gif.setImageResource((list.get(position).getGif()));
        holder.binding.textChin.setText(list.get(position).getDesc());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewH extends RecyclerView.ViewHolder {
        ChinItemBinding binding;
        public ViewH(@NonNull View itemView) {
            super(itemView);
            binding= ChinItemBinding.bind(itemView);
        }
    }
}
