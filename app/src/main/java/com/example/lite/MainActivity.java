 package com.example.lite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.lite.adapter.TemanAdapter;
import com.example.lite.database.DBController;
import com.example.lite.database.teman;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

 public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TemanAdapter adapter;
    private ArrayList<teman> temanArrayList;
    DBController controller = new DBController(this);
    String id,nm,tlp;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.floatingBtn);
        BacaData();
        adapter = new TemanAdapter(temanArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View x) {
                Intent c = new Intent(MainActivity.this,TemanBaru.class);
                startActivity(c);

            }
        });
    }

    public void BacaData(){
        ArrayList<HashMap<String,String>> daftarteman = controller.getAllteman();
        temanArrayList = new ArrayList<>();

        //memindah dari hasil query kedalam teman
        for(int i=0;i<daftarteman.size();i++){
            teman Teman = new teman();

            Teman.setId(daftarteman.get(i).get("id").toString());
            Teman.setNama(daftarteman.get(i).get("nama").toString());
            Teman.setTelpon(daftarteman.get(i).get("telpon").toString());

            //pindahkan dari teman kedalam Arraylist teman di adapter
            temanArrayList.add(Teman);
        }
    }
}