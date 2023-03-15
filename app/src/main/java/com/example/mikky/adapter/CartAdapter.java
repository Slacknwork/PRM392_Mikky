package com.example.mikky.adapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mikky.R;
import com.example.mikky.activities.CartActivity;
import com.example.mikky.activities.DrinkListActivity;
import com.example.mikky.models.Drink;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<Integer> quantity = new ArrayList<>();
    private Context mContext;
    private List<Drink> mlist = new ArrayList<>();
    private Drink[] listImg;
    ImageView imageDetail;
    String img;

    public CartAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setCartData(List<Drink> list, List<Integer> q){
        mlist = list;
        quantity = q;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, int position) {
        Drink drink = mlist.get(position);
        int amount = quantity.get(position);
        if (drink == null){
            return;
        }
        img = drink.getDrinkImage();
        holder.item_name.setText(drink.getDrinkname());
        DecimalFormat df = new DecimalFormat("###,###,###");
        holder.item_price.setText(df.format( drink.getPrice() *amount) + " đồng");
        Picasso.get().load(img).placeholder(R.drawable.noimage).error(R.drawable.error).into(holder.item_img);
        holder.item_quantity.setText(String.valueOf(amount));
    }

    @Override
    public int getItemCount() {
        return mlist != null ? mlist.size() : 0;
    }
    public class CartViewHolder extends RecyclerView.ViewHolder{
        private int drinkId, quantity;
        private String name,img;
        private Double price;
        private ImageButton item_remove;
        private ImageView item_img;
        private TextView item_name;
        private TextView item_price;
        private TextView item_quantity;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            item_img = itemView.findViewById(R.id.cart_img);
            item_name = itemView.findViewById(R.id.cart_name);
            item_price = itemView.findViewById(R.id.cart_price);
            item_quantity = itemView.findViewById(R.id.quantity);
            item_remove = itemView.findViewById(R.id.remove_cart);


            item_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int posAdapter = getAdapterPosition();
                    SharedPreferences shCart = mContext.getSharedPreferences("Cart", MODE_PRIVATE);
                    for (Drink d : mlist){
                        int position = shCart.getInt("DrinkId"+d.getDrinkId(),0);
                        if (position > 0 && posAdapter == mlist.indexOf(d)){
                            shCart.edit().remove("DrinkId"+position).commit();
                            shCart.edit().remove("Quantity"+position).commit();
                            shCart.edit().remove("Price"+position).commit();
                            shCart.edit().remove("Name"+position).commit();
                            shCart.edit().remove("Image"+position).commit();
                            Toast.makeText(mContext, "Remove item" , Toast.LENGTH_SHORT).show();
                            mContext.startActivity( new Intent(mContext, CartActivity.class));
                        }
                    }
                }
            });
        }
    }
}
