package com.example.mikky.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mikky.R;
import com.example.mikky.activities.DetailActivity;
import com.example.mikky.models.Drink;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder> implements Filterable {

    private Context mContext;
    private List<Drink> mlist;
    private List<Drink> mlistFilter;
    private Drink [] listImg;
    ImageView imageDetail;
    String img;
    public DrinkAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setDrinkData(List<Drink> list){
        mlist = list;
        mlistFilter = list;
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
        Drink drink = mlistFilter.get(position);
        if (drink == null){
            return;
        }
        img = drink.getDrinkImage();
        holder.item_name.setText(drink.getDrinkname());
        DecimalFormat df = new DecimalFormat("###,###,###");
        holder.item_price.setText(df.format((int) drink.getPrice()) + " đồng");
        Picasso.get().load(img).placeholder(R.drawable.noimage).error(R.drawable.error).into(holder.item_img);
    }

    @Override
    public int getItemCount() {
        if(mlistFilter!=null){
            return mlistFilter.size();
        }
        return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString == null){
                    mlistFilter = mlist;
                }else {
                    List<Drink> filteredList = new ArrayList<>();
                    for (Drink d : mlist){
                        if (d.getDrinkname().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(d);
                        }
                    }
                    mlistFilter = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mlistFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mlistFilter =  (ArrayList<Drink>) results.values;
                notifyDataSetChanged();
            }
        };
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
                    intent.putExtra("putIdToDetail", mlistFilter.get(getAdapterPosition()).getDrinkId());
                    mContext.startActivity(intent);
                }
            });
        }
    }

}
