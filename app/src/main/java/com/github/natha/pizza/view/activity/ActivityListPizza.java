package com.github.natha.pizza.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;

import com.github.natha.pizza.controler.activity.ControlerListPizza;
import com.github.natha.pizza.R;
import com.github.natha.pizza.model.Pizza;
import com.google.android.material.navigation.NavigationView;

public class ActivityListPizza extends AppCompatActivity {
    private ControlerListPizza controlerListPizza;
    private DrawerLayout drawerLayout;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pizza);

        this.drawerLayout = findViewById(R.id.drawer_layout);
        this.controlerListPizza = new ControlerListPizza(this);
        this.controlerListPizza.setNavigationView((NavigationView)findViewById(R.id.navigation_view));
    }
    public void closeDrawer(){
        this.drawerLayout.closeDrawer(GravityCompat.START);
    }
    public void onBackPressed() {
        if(this.drawerLayout.isDrawerOpen(GravityCompat.START))
            closeDrawer();
    }
    public void detailActivity(Pizza pizza){
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("pizza",pizza);
        startActivity(intent);
    }
}
