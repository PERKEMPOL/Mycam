package com.example.mycam.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycam.R;
import com.example.mycam.ui.models.Merk;

import java.util.List;

public class KameraAdapter extends RecyclerView.Adapter<KameraAdapter.MyViewHolder>  {
    List<Merk> merkList;

    public KameraAdapter(List<Merk> merkList) {
        this.merkList = merkList;
    }

    @NonNull
    @Override
    public KameraAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View superHeroView = layoutInflater.inflate(R.layout.fragment_kategori,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(superHeroView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull KameraAdapter.MyViewHolder holder, int position) {
        //ambil satu item hero
        Merk merk = merkList.get(position);
        //set text heroName berdasarkan data dari model hero
        holder.namaMerk.setText(merk.getNamaMerk());
    }

    @Override
    public int getItemCount()    {
        return (merkList != null) ? merkList.size() : 0;
        /*for non shorthand people*/
        /*if(heroList!=null){
          return heroList.size();
        }else{
          return 0;
        }*/
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView namaMerk;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //namaMerk = itemView.findViewById(R.id.namaMerk);
        }
    }
}