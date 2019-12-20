package com.github.natha.pizza.controler.activity;

import com.github.natha.pizza.model.API;
import com.github.natha.pizza.model.Pizza;
import com.github.natha.pizza.view.activity.ActivitySplashScreen;

import java.util.List;

import pl.droidsonroids.gif.GifDrawable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ControlerSplashScreen {
    private ActivitySplashScreen activitySplashScreen;
    private Thread gif,load;
    private Boolean threadWhile;
    private long start;
    private boolean launchOtherAcitivity;
    public ControlerSplashScreen(ActivitySplashScreen activitySplashScreen){
        this.activitySplashScreen = activitySplashScreen;
        this.threadWhile = true;
        this.launchOtherAcitivity = false;
        createThread();
    }

    private void createThread() {
        this.gif = new Thread(new Runnable() {
            public void run() {
                GifDrawable gifDrawable = (GifDrawable) activitySplashScreen.getGifImageView().getDrawable();
                gifDrawable.reset();
                while(threadWhile()){}
                gifDrawable.stop();
                activitySplashScreen.otherActivity();
            }
        });
        this.load = new Thread(new Runnable() {
            public void run() {
                int i=0;
                while(threadWhile()){
                    long start = System.currentTimeMillis();
                    activitySplashScreen.loading(i);
                    while(System.currentTimeMillis()-start<1000);
                    i++;
                    i%=4;
                }
            }
        });
    }
    public void launchGif(){
        this.start = System.currentTimeMillis();
        this.gif.start();
        this.load.start();
    }
    public void getApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ns202518.ovh.net/mehdi/api/pizza/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);
        Call<List<Pizza>> call = api.get();
        call.enqueue(new Callback<List<Pizza>>() {
            public void onResponse(Call<List<Pizza>> call, Response<List<Pizza>> response) {
                if(!response.isSuccessful()){
                    threadWhile = false;
                    activitySplashScreen.error("Error:"+response.code());
                    return;
                }
                savingTheData(response.body());
                threadWhile = false;
                launchOtherAcitivity = true;
            }
            public void onFailure(Call<List<Pizza>> call, Throwable t) {
                threadWhile = false;
                activitySplashScreen.error(t.getMessage());
            }
        });
    }

    private void savingTheData(List<Pizza> lists) {
        Pizza.setListPizza(this.activitySplashScreen.getSharedPreferences("pizza",this.activitySplashScreen.MODE_PRIVATE),lists);
    }
    private boolean threadWhile(){
        return this.threadWhile || System.currentTimeMillis()-this.start<4000;
    }
}
