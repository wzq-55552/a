package com.example.hasee.second_handbooks;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hasee.second_handbooks.db.ExchangeMessage;

import java.util.List;


//MyneedsMessageItemAdapter

public class MyneedsMessageItemAdapter extends RecyclerView.Adapter <MyneedsMessageItemAdapter.ViewHolder>{


    private Context mConext;

    private List<ExchangeMessage> mExchangeMessageList;


    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;

        //ImageView Book_img;

        TextView Book_name;

        TextView Change_time;

        TextView Change_location;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView)view;
            Book_name = (TextView)view.findViewById(R.id.myneedsitem_fagment_item_Book_name);
            //Book_img = (ImageView)view.findViewById(R.id.Book_img);
        }
    }

    public MyneedsMessageItemAdapter(List<ExchangeMessage> MessagesList){
        mExchangeMessageList = MessagesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mConext ==null){
            mConext = viewGroup.getContext();
        }
        View view = LayoutInflater.from(mConext).inflate(R.layout.myneedsitem_fragment_item,viewGroup,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position  = holder.getAdapterPosition();
                ExchangeMessage message = mExchangeMessageList.get(position);
                Intent intent = new Intent(mConext,Myneedsitem_itemActivity.class);
                intent.putExtra(Myneedsitem_itemActivity.MESSAGE_BOOKNAME,message.getBook_name());
                intent.putExtra(Myneedsitem_itemActivity.MESSAGE_REMARK,message.getRemark());
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
    }

    @Override
    public int getItemCount() {
        return mExchangeMessageList.size();
    }
}
