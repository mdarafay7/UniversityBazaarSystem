package com.example.ubs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClubsListAdapter extends RecyclerView.Adapter<ClubsListAdapter.ViewHolder> {

public List <ClubData> ClubsList;

public ClubsListAdapter(List <ClubData> ClubsList)
{
    this.ClubsList=ClubsList;
}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.nameText.setText(ClubsList.get(position).getClubName());
    holder.descText.setText(ClubsList.get(position).getDesc());

    }

    @Override
    public int getItemCount() {
        return ClubsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public TextView nameText;
        public TextView descText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;

            nameText=mView.findViewById(R.id.name_text);
            descText=mView.findViewById(R.id.desc_text);
        }
    }
}
