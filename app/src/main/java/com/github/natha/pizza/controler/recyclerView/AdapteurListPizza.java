package com.github.natha.pizza.controler.recyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.natha.pizza.model.Pizza;
import com.github.natha.pizza.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapteurListPizza extends RecyclerView.Adapter<AdapteurListPizza.MyViewHolder> {
    private ArrayList<Pizza> pizzaList;
    private Integer size,number;
    private ListenerListPizzaRecyclerView listener;
    public void setListener(ListenerListPizzaRecyclerView listener){
        this.listener = listener;
    }
    public AdapteurListPizza(ArrayList<Pizza> pizzaList,Integer number){
        this.pizzaList = pizzaList;
        this.number = number;
        this.listener = null;
    }
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view,parent,false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = parent.getWidth() / this.number;
        layoutParams.height = parent.getWidth() / this.number;
        this.size = parent.getWidth() / this.number;
        view.setLayoutParams(layoutParams);
        return new MyViewHolder(view);
    }
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.display(this.pizzaList.get(position));
    }
    public int getItemCount() {
        return this.pizzaList.size();
    }
    // Class or interface for each element
    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private MyViewHolder(View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.recyclerView_imageView);
            this.textView = itemView.findViewById(R.id.recyclerView_textView);
            if(listener!=null){
                itemView.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                        listener.click(getAdapterPosition());
                    }
                });
            }
        }
        private void display(Pizza pizza){
            Picasso.with(imageView.getContext()).load(pizza.getImage()).resize(size,size).into(imageView);
            textView.setText(pizza.getName());
        }
    }
    public interface ListenerListPizzaRecyclerView {
        void click(int position);
    }
}
