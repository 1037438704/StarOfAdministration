package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarLayout appBarLayout;
    private ImageView view;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        appBarLayout = findViewById(R.id.appBarLayout);
        view = findViewById(R.id.view);
        recyclerView = findViewById(R.id.recyclerView);
        init();
    }

    private void init(){
        List<String> list = new ArrayList<>();
        for(int i=1;i<=30;i++){
            list.add(i + "");
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        MyAdapter adapter = new MyAdapter(list);
        recyclerView.setAdapter(adapter);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float scale = 1.0f - (float) (-verticalOffset) / (appBarLayout.getMeasuredHeight());
                Log.e("<<<<<<<<<<<<",scale + "");
                view.setAlpha(scale);
            }
        });
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

        private List<String> list;

        public MyAdapter(List<String> list){
            this.list = list;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.item,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.textView.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyViewHolder extends  RecyclerView.ViewHolder{

            TextView textView;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.textView);
            }
        }

    }

}
