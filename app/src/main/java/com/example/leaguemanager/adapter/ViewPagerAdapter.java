package com.example.leaguemanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.leaguemanager.Model.Matches;
import com.example.leaguemanager.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {
    ArrayList<Matches> matchesListFull;
    int pageSize;
    MatchesAdapter matchesAdapter;
    Context ctx;

    public ViewPagerAdapter(ArrayList<Matches> myList, int pageSize, MatchesAdapter matchesAdapter, Context context) {
        this.pageSize = pageSize;
        this.matchesListFull = myList;
        this.matchesAdapter = matchesAdapter;
        this.ctx= context;
    }



    @NonNull
    @Override
    public ViewPagerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_container,parent,false);

        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewPagerAdapter.ViewHolder holder, int position) {
        //System.out.println("======>>>>>>" + position);
        holder.recyclerViewMatches.setLayoutManager(new GridLayoutManager(ctx,1));
        holder.recyclerViewMatches.setAdapter(this.matchesAdapter);
        this.matchesAdapter.setMatches(position);


    }


    @Override
    public int getItemCount() {
        return this.pageSize;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerViewMatches;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
          recyclerViewMatches = itemView.findViewById(R.id.recyclerViewMatches);
        }
    }
}
