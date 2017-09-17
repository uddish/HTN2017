package com.example.uddishverma.hackthenorth;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by uddishverma on 17/09/17.
 */

public class HotelsAdapter extends RecyclerView.Adapter<HotelsAdapter.detailsViewHolder> {

    ArrayList<HotelsModel> list;

    public HotelsAdapter(ArrayList<HotelsModel> list) {
        this.list = list;
    }

    public class detailsViewHolder extends RecyclerView.ViewHolder {

        TextView places;
        Typeface openSansReg;

        public detailsViewHolder(View itemView) {
            super(itemView);
            places = (TextView) itemView.findViewById(R.id.place);
            openSansReg = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        }
    }

    @Override
    public HotelsAdapter.detailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotels_list_layout, parent, false);
        return new detailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HotelsAdapter.detailsViewHolder holder, int position) {
        HotelsModel model = list.get(position);
        holder.places.setText(model.name);
        holder.places.setTypeface(holder.openSansReg);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
