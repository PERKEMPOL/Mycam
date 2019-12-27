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
import com.example.mycam.bottom.model.PemesananItem;
import com.example.mycam.bottom.ui.service.PublicStatic;

import java.util.List;

public class PemesananAdapter extends RecyclerView.Adapter<PemesananAdapter.TransaksiViewHolder> {


    private List<PemesananItem> dataList;
    Context ctx;
    public PemesananAdapter(List<PemesananItem> dataList,Context ctx) {
        this.dataList = dataList;
        this.ctx = ctx;
    }

    @Override
    public PemesananAdapter.TransaksiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list_pembeli, parent, false);
        return new TransaksiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TransaksiViewHolder holder, int position) {
        holder.nama_pemesan.setText(dataList.get(position).getNamaPemesan());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class TransaksiViewHolder extends RecyclerView.ViewHolder {
        private TextView nama_pemesan;

        public TransaksiViewHolder(View itemView) {
            super(itemView);
            nama_pemesan = (TextView) itemView.findViewById(R.id.nama_pemesan);
        }
    }
}
