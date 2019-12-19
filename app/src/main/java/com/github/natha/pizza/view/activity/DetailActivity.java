package com.github.natha.pizza.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.natha.pizza.R;
import com.github.natha.pizza.controler.recyclerView.DetailRecyclerViewAdapteur;
import com.github.natha.pizza.model.Pizza;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private Pizza pizza;
    private RecyclerView video,ingredient,recette;
    private ImageView imageView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        this.pizza = getIntent().getParcelableExtra("pizza");

        this.createRecyclerView();

        this.adjusteTitle();

        this.imageView = findViewById(R.id.activity_detail_ImageView);
        Picasso.with(this.imageView.getContext()).load(pizza.getImage()).into(this.imageView);
    }

    private void adjusteTitle() {
        TextView textView = findViewById(R.id.activity_detail_textView1);
        if(pizza.getName().length()>10){
            TextView textView2 = findViewById(R.id.activity_detail_textView2);
            TextView textView3 = findViewById(R.id.activity_detail_textView3);

            double size = 25.0 - ((double)pizza.getName().length()-10.0)%15;
            textView.setTextSize((float)size);
            textView2.setTextSize((float)size);
            textView3.setTextSize((float)size);
        }
        textView.setText(textView.getText()+pizza.getName()+" ?");

    }

    private void createRecyclerView() {
        this.video = findViewById(R.id.activity_detail_recyclerView_video);
        this.ingredient = findViewById(R.id.activity_detail_recyclerView_ingredient);
        this.recette = findViewById(R.id.activity_detail_recyclerView_recette);

        this.video.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        this.ingredient.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        this.recette.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

        this.video.setAdapter(new DetailRecyclerViewAdapteur("video",this.pizza.getVideos()));
        this.ingredient.setAdapter(new DetailRecyclerViewAdapteur("ingredient",this.pizza.getIngredients()));
        this.recette.setAdapter(new DetailRecyclerViewAdapteur("recette",this.pizza.getRecette()));
    }
}
