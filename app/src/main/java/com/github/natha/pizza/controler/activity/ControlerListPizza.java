package com.github.natha.pizza.controler.activity;

import android.view.MenuItem;

import androidx.fragment.app.FragmentTransaction;

import com.github.natha.pizza.R;
import com.github.natha.pizza.model.Pizza;
import com.github.natha.pizza.view.activity.ActivityListPizza;
import com.github.natha.pizza.view.fragment.FragmentRecyclerView;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class ControlerListPizza {
    private ActivityListPizza activityListPizza;
    private List<Pizza> pizzas;
    private FragmentRecyclerView alphabetique,recette,ingredient,video;
    public ControlerListPizza(ActivityListPizza activityListPizza){
        this.activityListPizza = activityListPizza;
        this.pizzas = Pizza.getListPizza();

        this.alphabetique = new FragmentRecyclerView("alphabetique",pizzas,activityListPizza);
        this.recette = new FragmentRecyclerView("recette",pizzas,activityListPizza);
        this.ingredient = new FragmentRecyclerView("ingredient",pizzas,activityListPizza);
        this.video = new FragmentRecyclerView("video",pizzas,activityListPizza);

        FragmentTransaction transaction = this.activityListPizza.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.list_pizza_fragment_1,this.alphabetique);
        transaction.replace(R.id.list_pizza_fragment_2,this.recette);
        transaction.replace(R.id.list_pizza_fragment_3,this.ingredient);
        transaction.replace(R.id.list_pizza_fragment_4,this.video).commit();

        this.fragmentSet(this.alphabetique,this.recette,this.ingredient,this.video);
    }

    public void setNavigationView(final NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.nav_ordre_alphabetique:fragmentSet(alphabetique,recette,ingredient,video);break;
                    case R.id.nav_ordre_etape:fragmentSet(recette,alphabetique,ingredient,video);break;
                    case R.id.nav_ordre_ingredient:fragmentSet(ingredient,recette,alphabetique,video);break;
                    case R.id.nav_ordre_video:fragmentSet(video,recette,ingredient,alphabetique);break;
                    case R.id.nav_server_deconnect:deconnect();break;
                }
                menuItem.setCheckable(true);
                activityListPizza.closeDrawer();
                return true;
            }
        });
    }
    private void fragmentSet(FragmentRecyclerView open,FragmentRecyclerView close1,FragmentRecyclerView close2,FragmentRecyclerView close3){
        FragmentTransaction transaction = this.activityListPizza.getSupportFragmentManager().beginTransaction();
        transaction.show(open);
        transaction.hide(close1);
        transaction.hide(close2);
        transaction.hide(close3).commit();
    }
    private void deconnect() {
        Pizza.clearListPizza();
        this.activityListPizza.finishAffinity();
    }
}
