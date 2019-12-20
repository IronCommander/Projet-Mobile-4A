package com.github.natha.pizza.controler.recyclerView;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.github.natha.pizza.R;

import java.util.ArrayList;

public class DetailRecyclerViewAdapteur extends RecyclerView.Adapter<DetailRecyclerViewAdapteur.MyViewHolderDetail> {
    private ArrayList<String> arrayList;
    private String typeOfAdapteur;
    public DetailRecyclerViewAdapteur(String typeOfAdapteur, ArrayList<String> arrayList) {
        this.arrayList = arrayList;
        this.typeOfAdapteur = typeOfAdapteur;
    }
    public MyViewHolderDetail onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (this.typeOfAdapteur) {
            case "video":   return new MyViewHolderDetail(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_detail_only_web_view,parent,false));
            default:        return new MyViewHolderDetail(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_detail_only_text,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderDetail holder, int position) {
        switch (this.typeOfAdapteur.toLowerCase()){
            case "video":holder.displayVideo(this.arrayList.get(position));break;
            case "ingredient":holder.displayIngredient(this.arrayList.get(position));break;
            case "recette":holder.displayRecette(this.arrayList.get(position));break;
        }
    }
    public int getItemCount() {
        return this.arrayList.size();
    }
    // Class or interface for each element
    class MyViewHolderDetail extends RecyclerView.ViewHolder {
        private WebView webView;
        private TextView textView1,textView2;
        private MyViewHolderDetail(View itemView) {
            super(itemView);
            switch (typeOfAdapteur){
                case "video" :
                    this.webView = itemView.findViewById(R.id.recyclerView_only_WebView);
                    this.webView.setWebViewClient(new WebViewClient());
                    this.webView.getSettings().setAppCacheEnabled(true);
                    this.webView.getSettings().setJavaScriptEnabled(true);
                    break;
                case "ingredient":
                    this.textView1 = itemView.findViewById(R.id.recyclerView_only_TextView1);
                    this.textView1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    this.textView1.setTypeface(this.textView1.getTypeface(), Typeface.BOLD);
                    this.textView2 = itemView.findViewById(R.id.recyclerView_only_TextView2);
                    this.textView2.setVisibility(View.GONE);
                    break;
                default:
                    this.textView1 = itemView.findViewById(R.id.recyclerView_only_TextView1);
                    this.textView1.setTypeface(this.textView1.getTypeface(), Typeface.BOLD_ITALIC);
                    this.textView2 = itemView.findViewById(R.id.recyclerView_only_TextView2);
            }
        }
        public void displayVideo(String s) {
            this.webView.loadUrl(s);
        }
        public void displayIngredient(String s) {
            this.textView1.setText(s);
        }
        public void displayRecette(String s) {
            String message1,message2;

            message1 = s.split(":")[0]+":";
            message2 = "";
            for(char element:message1.toCharArray()){
                message2+=" ";
            }
            message2+=s.split(":")[1]+"\n";

            this.textView1.setText(message1);
            this.textView2.setText(message2);
        }
    }

}
