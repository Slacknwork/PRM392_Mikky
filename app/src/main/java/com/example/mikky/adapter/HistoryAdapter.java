package com.example.mikky.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mikky.R;
import com.example.mikky.models.Drink;
import com.example.mikky.models.Order;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.OrderViewHolder>{
    private Context mContext;
    private List<Order> mlist;

    public HistoryAdapter(Context mContext)  {
        this.mContext = mContext;
    }
    public void setOrderData(List<Order> list){
        mlist = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        return new HistoryAdapter.OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = mlist.get(position);
        if (order == null) return;
        holder.item_number.setText(String.valueOf(position+1));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String s = dateFormat.format(order.getDate());
        holder.item_date.setText(s);
        DecimalFormat df = new DecimalFormat("###,###,###");
        holder.item_price.setText(df.format((int) order.getTotalPrice()) + " đồng");
        holder.item_status.setText(order.getStatus());
        if (order.getStatus().equals("Delivering")){
            holder.item_status.setTextColor(Color.YELLOW);
        }else if (order.getStatus().equals("Delivered")){
            holder.item_status.setTextColor(Color.GREEN);
        }
    }

    @Override
    public int getItemCount() {
        if(mlist!=null){
            return mlist.size();
        }
        return 0;
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder{

        private TextView item_number;
        private TextView item_date;
        private TextView item_price;
        private TextView item_status;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            item_number = itemView.findViewById(R.id.order_number);
            item_date = itemView.findViewById(R.id.order_date);
            item_price = itemView.findViewById(R.id.order_price);
            item_status = itemView.findViewById(R.id.order_status);
        }
    }
}
