package com.example.leaguemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import com.example.leaguemanager.Model.Matches;
import com.example.leaguemanager.adapter.MatchesAdapter;
import com.example.leaguemanager.adapter.ViewPagerAdapter;
import com.example.leaguemanager.localDB.DB;

import java.util.ArrayList;

public class FixturesActivity extends AppCompatActivity {

    ViewPager2 viewpager;
    ViewPagerAdapter viewPagerAdapter;

    FragmentStateAdapter fragmentStateAdapter;
    MatchesAdapter matchesAdapter;
    ArrayList<Matches> myMatches;
    DB DATABASE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixtures);

        //all init
        DATABASE = new DB(this);
        myMatches = new ArrayList<Matches>(DATABASE.getAllMatchesFromDB());
        matchesAdapter = new MatchesAdapter(this,myMatches);


        viewpager = findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(myMatches,(myMatches.size()/14), matchesAdapter,this); //14 lük gruplara ayrılmış listelere ait sayfa sayısı => size / 14
        viewpager.setAdapter(viewPagerAdapter);

        //passing animation
        CompositePageTransformer transformer = new CompositePageTransformer();
        transformer.addTransformer(new MarginPageTransformer(8));

        transformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float v = 1-Math.abs(position);
                page.setScaleY(0.8f + v*0.2f);
            }
        });

        viewpager.setPageTransformer(transformer);

        //All matches have to set and display.








    }
}