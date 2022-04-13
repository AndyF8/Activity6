package com.example.lite.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lite.MainActivity;
import com.example.lite.R;
import com.example.lite.database.DBController;
import com.example.lite.database.teman;
import com.example.lite.edit_Teman;

import java.util.ArrayList;
import java.util.HashMap;

public class TemanAdapter extends RecyclerView.Adapter<TemanAdapter.TemanViewHolder> {
    private ArrayList<teman> listData;

    private Context control;

    public TemanAdapter(ArrayList<teman> listData) {
        this.listData = listData;

    }



    @Override
    public TemanAdapter.TemanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInf = LayoutInflater.from(parent.getContext());
        View view = layoutInf.inflate(R.layout.row_data_teman,parent, false);
        control = parent.getContext();
        return new TemanViewHolder(view);


    }

    @Override
    public void onBindViewHolder(TemanAdapter.TemanViewHolder holder, int position) {

        String nm,tlp,id;

        id = listData.get(position).getid();
        nm = listData.get(position).getNama();
        tlp = listData.get(position).getTelpon();
        DBController db = new DBController(control);

        holder.namaTxt.setText(nm);
        holder.namaTxt.setTextColor(Color.BLUE);
        holder.namaTxt.setTextSize(20);
        holder.telponTxt.setText(tlp);

        holder.cardku.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View w) {
                PopupMenu ps= new PopupMenu(control, holder.cardku);
                ps.inflate(R.menu.menu);
                ps.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
                    @Override
                    public boolean onMenuItemClick(MenuItem z) {
                        switch (z.getItemId()){
                            case R.id.mnEdit:
                                Intent b = new Intent(control, edit_Teman.class);
                                b.putExtra("id",id);
                                b.putExtra("nama",nm);
                                b.putExtra("telpon",tlp);
                                control.startActivity(b);
                                break;

                            case R.id.mnHapus:
                                HashMap<String,String> values = new HashMap<>();
                                values.put("id",id);
                                db. DeleteData(values);
                                Intent j = new Intent(control, MainActivity.class);
                                control.startActivity(j);
                                break;
                        }
                        return  true;


                    }
                });
                ps.show();
                return false;

            }
        });


    }

    @Override
    public int getItemCount() {
        return (listData !=null)?listData.size() : 0  ;
    }

    public class TemanViewHolder extends RecyclerView.ViewHolder {
        private CardView cardku;
        private TextView namaTxt,telponTxt;
        public  TemanViewHolder(View view) {
            super(view);
            cardku = (CardView) view.findViewById(R.id.kartuku);
            namaTxt =(TextView) view.findViewById(R.id.textNama);
            telponTxt = (TextView) view.findViewById(R.id.textTelpon);
        }

    }


}
