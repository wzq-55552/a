package com.example.hasee.second_handbooks.nav_item_activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hasee.second_handbooks.MyApplyItemActicity;
import com.example.hasee.second_handbooks.MyneedsItemActivity;
import com.example.hasee.second_handbooks.R;
import com.example.hasee.second_handbooks.db.ExchangeMessage;

import java.util.List;

//MyneedsMessageAdapter

public class RecordAdapter extends RecyclerView.Adapter <RecordAdapter.ViewHolder> {


    private Context mConext;

    private List<ExchangeMessage> mExchangeMessageList;


    static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;

        //ImageView Book_img;

        TextView Book_name;

        TextView Change_time;

        TextView Change_location;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            Book_name = (TextView) view.findViewById(R.id.Book_name);
            //Book_img = (ImageView)view.findViewById(R.id.Book_img);
            Change_time = (TextView) view.findViewById(R.id.Change_time);
            Change_location = (TextView) view.findViewById(R.id.Change_location);
        }
    }

    public RecordAdapter(List<ExchangeMessage> MessagesList) {
        mExchangeMessageList = MessagesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mConext == null) {
            mConext = viewGroup.getContext();
        }


        View view = LayoutInflater.from(mConext).inflate(R.layout.myneedsmessage_item, viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                ExchangeMessage message = mExchangeMessageList.get(position);
                Intent intent = new Intent(mConext,MyApplyItemActicity.class);
                intent.putExtra(MyApplyItemActicity.MESSAGE_BOOKNAME2,message.getBook_name());
                intent.putExtra(MyApplyItemActicity.MESSAGE_TIME2,message.getTime());
                intent.putExtra(MyApplyItemActicity.MESSAGE_LOCATION2,message.getLocation());
                intent.putExtra(MyApplyItemActicity.MESSAGE_REMARK2,message.getRemark());
                mConext.startActivity(intent);
            }
        });


        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            ExchangeMessage exchangeMessage = mExchangeMessageList.get(i);
            //viewHolder.Book_img.setImageResource(exchangeMessage.getBook_image_id());
            viewHolder.Book_name.setText(exchangeMessage.getBook_name());
            viewHolder.Change_time.setText(exchangeMessage.getTime());
            viewHolder.Change_location.setText(exchangeMessage.getLocation());
    }

    @Override
    public int getItemCount() {
            return mExchangeMessageList.size();
    }



}


