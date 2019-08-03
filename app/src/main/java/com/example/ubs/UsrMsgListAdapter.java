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

public class UsrMsgListAdapter extends RecyclerView.Adapter<UsrMsgListAdapter.ViewHolder> {

    public List <UsrMsgData> MsgsList;
    public Context context;
    public UsrMsgListAdapter(Context context,List <UsrMsgData> MsgsList)
    {
        this.MsgsList=MsgsList;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.msgs_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.UsrName.setText(MsgsList.get(position).getName());
        final String user_id=MsgsList.get(position).userId;
        final String name=MsgsList.get(position).getName();

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(context,"Userid"+user_id,Toast.LENGTH_LONG).show();
               Intent intent = new Intent(context,UsrSendMsg.class);
                intent.putExtra("TO",name);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return MsgsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public TextView UsrName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;

            UsrName=(TextView)mView.findViewById(R.id.usrname_text);
        }

    }

}
