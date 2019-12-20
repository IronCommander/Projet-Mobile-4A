package com.github.natha.pizza.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.natha.pizza.R;
import com.github.natha.pizza.controler.recyclerView.AdapteurListPizza;
import com.github.natha.pizza.model.Pizza;
import com.github.natha.pizza.view.activity.ActivityListPizza;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FragmentRecyclerView extends Fragment {
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private AdapteurListPizza adapteurListPizza;
    public FragmentRecyclerView(String text, final List<Pizza> pizzaList, final ActivityListPizza activityListPizza){
        this.recyclerView = null;
        this.gridLayoutManager = new GridLayoutManager(activityListPizza,3);
        final ArrayList<Pizza> pizza = new ArrayList<>();
        for(int i=0;i<pizzaList.size();i++){
            pizza.add(new Pizza(pizzaList.get(i).getName(),pizzaList.get(i).getImage(),text,pizzaList.get(i).getIngredients(),pizzaList.get(i).getRecette(),pizzaList.get(i).getVideos()));
        }
        Collections.sort(pizza);
        this.adapteurListPizza = new AdapteurListPizza(pizza,3);
        // Listener
        AdapteurListPizza.ListenerListPizzaRecyclerView listenerListPizzaRecyclerView = new AdapteurListPizza.ListenerListPizzaRecyclerView(){
            public void click(int position) {
                activityListPizza.detailActivity(pizza.get(position));
            }
        };
        this.adapteurListPizza.setListener(listenerListPizzaRecyclerView);

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view,container,false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(this.gridLayoutManager);
        recyclerView.setAdapter(this.adapteurListPizza);
        return view;
    }
}
