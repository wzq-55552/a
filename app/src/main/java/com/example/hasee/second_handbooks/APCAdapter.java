package com.example.hasee.second_handbooks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.hasee.second_handbooks.db.ExchangeMessage;
import com.example.hasee.second_handbooks.nav_item_activity.MycollectionAdapter;
import com.example.hasee.second_handbooks.nav_item_activity.MycollectionitemActivity;

import java.io.InputStream;
import java.util.List;

public class APCAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    public boolean isLoadMore = false;

    private Context mContext;

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
            Book_name = (TextView)view.findViewById(R.id.apc_item_Book_name);
            //Book_img = (ImageView)view.findViewById(R.id.Book_img);
            Change_time = (TextView)view.findViewById(R.id.apc_item_Change_time);
            Change_location = (TextView)view.findViewById(R.id.apc_item_Change_location);
        }
    }

    public APCAdapter(List<ExchangeMessage> MessagesList){
        mExchangeMessageList = MessagesList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (mContext ==null){
            mContext = viewGroup.getContext();
        }

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -2);
        if (viewType == TYPE_ITEM) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.apc_item,viewGroup,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position  = holder.getAdapterPosition();
                ExchangeMessage message = mExchangeMessageList.get(position);
                Intent intent = new Intent(mContext,MyApplyItemActicity.class);
                intent.putExtra(MyApplyItemActicity.MESSAGE_BOOKNAME2,message.getBook_name());
                intent.putExtra(MyApplyItemActicity.MESSAGE_TIME2,message.getTime());
                intent.putExtra(MyApplyItemActicity.MESSAGE_LOCATION2,message.getLocation());
                intent.putExtra(MyApplyItemActicity.MESSAGE_REMARK2,message.getRemark());
                mContext.startActivity(intent);
            }
        });
        return holder;
        }

        final ProgressBar bar = new ProgressBar(mContext);
        bar.setLayoutParams(lp);
        ProgressViewHoler barViewHolder = new ProgressViewHoler(bar);
        return barViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ViewHolder) {
            ExchangeMessage exchangeMessage = mExchangeMessageList.get(i);
            //viewHolder.Book_img.setImageResource(exchangeMessage.getBook_image_id());
            ((ViewHolder)viewHolder).Book_name.setText(exchangeMessage.getBook_name());
            ((ViewHolder)viewHolder).Change_time.setText(exchangeMessage.getTime());
            ((ViewHolder)viewHolder).Change_location.setText(exchangeMessage.getLocation());
        }
    }

    @Override
    public int getItemCount() {
        if (isLoadMore) {
            return mExchangeMessageList.size() + 1;
        }
        return mExchangeMessageList.size();
    }


    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount() && isLoadMore) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }


    public static class ProgressViewHoler extends RecyclerView.ViewHolder {
        public ProgressBar bar;

        public ProgressViewHoler(View itemView) {
            super(itemView);
            bar = (ProgressBar) itemView;
        }
    }


    /*public void addItem(List<ExchangeMessage> newDatas) {
        newDatas.addAll(mExchangeMessageList);
        mExchangeMessageList.removeAll(mExchangeMessageList);
        mExchangeMessageList.addAll(newDatas);
        notifyDataSetChanged();
    }*/

    public void addMoreItem(List<ExchangeMessage> newData) {
        mExchangeMessageList.addAll(newData);
        isLoadMore = false;
        notifyDataSetChanged();
    }

    public void startLoad() {
        isLoadMore = true;
        notifyDataSetChanged();
    }
}