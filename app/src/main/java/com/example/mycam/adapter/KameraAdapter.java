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
import com.example.mycam.bottom.model.KameraItem;
import com.example.mycam.bottom.ui.service.PublicStatic;

import java.util.ArrayList;
import java.util.List;

public class KameraAdapter extends RecyclerView.Adapter<KameraAdapter.TransaksiViewHolder> {


    private List<KameraItem> dataList;
    Context ctx;
    public KameraAdapter(List<KameraItem> dataList,Context ctx) {
        this.dataList = dataList;
        this.ctx = ctx;
    }

    @Override
    public TransaksiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list_kamera, parent, false);
        return new TransaksiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KameraAdapter.TransaksiViewHolder holder, int position) {
        holder.namaKamera.setText(dataList.get(position).getNameKamera());
        holder.hargaKamera.setText(dataList.get(position).getHargaKamera());
        Glide.with(ctx)
                .load(PublicStatic.path +dataList.get(position).getNamaFile())
                .into(holder.img_kamera);
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class TransaksiViewHolder extends RecyclerView.ViewHolder {
        private TextView namaKamera, hargaKamera;
        ImageView img_kamera;

        public TransaksiViewHolder(View itemView) {
            super(itemView);
            namaKamera = (TextView) itemView.findViewById(R.id.namaKamera);
            hargaKamera = (TextView) itemView.findViewById(R.id.hargaKamera);
            img_kamera = (ImageView) itemView.findViewById(R.id.img_kamera);
        }
    }
}
