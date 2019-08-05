package com.example.ubs;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClubsListAdapter extends RecyclerView.Adapter<ClubsListAdapter.ViewHolder> {

public List <ClubData> ClubsList;
public Context context;

public ClubsListAdapter(Context context,List <ClubData> ClubsList)
{

    this.ClubsList=ClubsList;
    this.context=context;
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
    final String user_id=ClubsList.get(position).userId;
    final String name=ClubsList.get(position).getClubName();

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,"Userid"+user_id,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context,ClubMsg.class);
                intent.putExtra("CLUB_NAME",name);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });



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
