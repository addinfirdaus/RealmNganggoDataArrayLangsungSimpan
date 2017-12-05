package com.sinau.belajarrealm3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by addin on 05/12/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context c;
    ArrayList<String> spacecrafts;
    public MyAdapter(Context c, ArrayList<String> spacecrafts) {
        this.c = c;
        this.spacecrafts = spacecrafts;
    }
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(c).inflate(R.layout.model,parent,false);
        return new MyAdapter.ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        holder.textView.setText(spacecrafts.get(position));
    }
    @Override
    public int getItemCount() {
        return spacecrafts.size();
    }

        public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
            public ViewHolder(View itemLayoutView) {
                super(itemLayoutView);
                textView = (TextView) itemLayoutView.findViewById(R.id.textView);


            }
        }}