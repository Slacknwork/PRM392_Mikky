package com.example.mikky.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mikky.R;
import com.example.mikky.activities.DetailActivity;
import com.example.mikky.models.Drink;

import java.util.List;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder>{

    private Context mContext;
    private List<Drink> mlist;

    public DrinkAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setDrinkData(List<Drink> list){
        mlist = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public DrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_drinks, parent, false);
        return new DrinkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkViewHolder holder, int position) {
        Drink drink = mlist.get(position);
        if (drink == null){
            return;
        }
        holder.item_img.setImageResource(drink.getId());
        holder.item_name.setText(drink.getDrinkname());
        holder.item_price.setText(String.valueOf(drink.getPrice()) + " đồng");
    }

    @Override
    public int getItemCount() {
        if(mlist!=null){
            return mlist.size();
        }
        return 0;
    }

    public class DrinkViewHolder extends RecyclerView.ViewHolder{

        private ImageView item_img;
        private TextView item_name;
        private TextView item_price;

        public DrinkViewHolder(@NonNull View itemView) {
            super(itemView);
            item_img = itemView.findViewById(R.id.drink_img);
            item_name = itemView.findViewById(R.id.drink_name);
            item_price = itemView.findViewById(R.id.drink_price);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, DetailActivity.class);
                    intent.putExtra("putIdToDetail", mlist.get(getAdapterPosition()).getId());
                    mContext.startActivity(intent);
                }
            });
        }
    }

}
