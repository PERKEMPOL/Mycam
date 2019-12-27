package com.example.mycam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mycam.R;
import com.example.mycam.bottom.Tools.RecyclerItemClickListener;
import com.example.mycam.bottom.model.CategoriItem;
import com.example.mycam.bottom.ui.service.PublicStatic;

import java.util.List;

public class CategoriAdapter extends RecyclerView.Adapter<CategoriAdapter.TransaksiViewHolder> {


    private List<CategoriItem> dataList;
    Context ctx;
    private RecyclerItemClickListener clickListener;
    public CategoriAdapter(List<CategoriItem> dataList,Context ctx) {
        this.dataList = dataList;
        this.ctx = ctx;
    }

    @Override
    public CategoriAdapter.TransaksiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list_categori, parent, false);
        return new CategoriAdapter.TransaksiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoriAdapter.TransaksiViewHolder holder, int position) {
        Glide.with(ctx)
                .load(PublicStatic.pathMerk+dataList.get(position).getNamaFile())
                .into(holder.merk);
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class TransaksiViewHolder extends RecyclerView.ViewHolder {
        ImageView merk;

        public TransaksiViewHolder(View itemView) {
            super(itemView);
            merk = (ImageView) itemView.findViewById(R.id.merk);
        }
    }
}

