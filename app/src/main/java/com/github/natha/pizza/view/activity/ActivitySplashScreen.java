package com.github.natha.pizza.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.github.natha.pizza.controler.activity.ControlerSplashScreen;
import com.github.natha.pizza.R;
import com.github.natha.pizza.model.Pizza;

import pl.droidsonroids.gif.GifImageView;

public class ActivitySplashScreen extends AppCompatActivity {
    private ControlerSplashScreen contolerSplashScreen;
    private GifImageView gifImageView;
    private TextView textView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        if(Pizza.getListPizza(getSharedPreferences("pizza",MODE_PRIVATE))!=null){
            otherActivity();
        } else {
            this.gifImageView = findViewById(R.id.splash_screen_gifImageView);
            this.textView = findViewById(R.id.splash_screen_textView);
            this.contolerSplashScreen = new ControlerSplashScreen(this);
            this.contolerSplashScreen.launchGif();
            this.contolerSplashScreen.getApi();
        }
    }
    public GifImageView getGifImageView(){
        return this.gifImageView;
    }
    public void loading(int point) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<point;i++)
            stringBuilder.append(".");
        this.textView.setText("Chargement"+(point>0?" "+stringBuilder.toString():""));
    }
    public void error(String error){
        this.textView.setText(error);
        this.textView.setTextColor(Color.RED);
    }

    public void otherActivity() {
        startActivity(new Intent(this,ActivityListPizza.class));
    }

    @Override
    public void onBackPressed() {
    }
}
