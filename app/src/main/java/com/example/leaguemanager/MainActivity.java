package com.example.leaguemanager;

import static com.example.leaguemanager.calculate.Calculation.Calculate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.leaguemanager.Model.Matches;
import com.example.leaguemanager.Model.Team;
import com.example.leaguemanager.adapter.TeamListAdapter;
import com.example.leaguemanager.calculate.Calculation;
import com.example.leaguemanager.localDB.DB;
import com.example.leaguemanager.viewmodel.MyViewModel;
import com.google.android.gms.common.util.CollectionUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button drawFixtures;
    private TeamListAdapter myAdapter;
    private ArrayList<Team> myTeamList;
    private MyViewModel myViewModel;
    private ProgressBar progressBar;
    private DB DATABASE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawFixtures = findViewById(R.id.drawFixtures);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new TeamListAdapter(this, myTeamList);
        recyclerView.setAdapter(myAdapter);
        progressBar = findViewById(R.id.progressBar);
        myTeamList = new ArrayList<>();
        DATABASE = new DB(this);

        //DATABASE.deleteAllTeam();
        //DATABASE.deleteAllMatches();
        //DATABASE.getAllTeamFromDB();


        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        myViewModel.getTeamList().observe(this, new Observer<ArrayList<Team>>() {
            @Override
            public void onChanged(ArrayList<Team> teams) {
                if (myTeamList != null) {
                    myTeamList = teams;
                    myAdapter.setTeamList(myTeamList);
                   //Toast.makeText(getApplicationContext(), "tak??m sayisi : " + myTeamList.size(), Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);


                    if (DATABASE.getAllTeamFromDB().size() == 0) {
                        //yerel veritabanu bo??sa (veriler ilk defa eklenecek)
                        Toast.makeText(getApplicationContext(), "Veriler, ilk defa ekleniyor", Toast.LENGTH_LONG).show();
                        for (int i = 0; i < myTeamList.size(); i++) {
                            DATABASE.addTeam(myTeamList.get(i));
                            System.out.println(i + ". tak??m veritaban??na eklendi.");
                        }
                        //hesaplama burada yap??lacak

                      ArrayList<Matches> matchesList = new ArrayList<>(Calculation.Calculate(myTeamList));
                        for(Matches m : matchesList)
                        DATABASE.addMatch(m);



                    } else {
                        //veritaban?? bo?? de??ilse
                        ArrayList<String> teamNameList1 = new ArrayList<>();
                        ArrayList<String> teamNameList2 = new ArrayList<>();

                        for(int i=0;i<myTeamList.size();i++) {
                            teamNameList1.add(myTeamList.get(i).getTeamName());
                            teamNameList2.add(DATABASE.getAllTeamFromDB().get(i).getTeamName());
                        } //verileri k??yaslamak i??in gereken arraylistlere eklemeler yap??ld??

                            if (teamNameList1.equals(teamNameList2)) { //veriler daha ??nce ??ekilen verilerle ayn?? ise
                                Toast.makeText(getApplicationContext(), "Veriler, daha ??nce ??ekilen verilerle uyu??uyor.", Toast.LENGTH_LONG).show();
                                //e??le??melere ait veriler burda ??ekilecek.

                            }

                            else { //daha ??nce ??ekilen verilerden farkl?? veriler gelmi??se
                                DATABASE.deleteAllTeam(); //veritaban??n?? temizle
                                DATABASE.deleteAllMatches();
                                for (int i = 0; i < myTeamList.size(); i++) { //verileri yeniden ekle
                                    DATABASE.addTeam(myTeamList.get(i));
                                }
                                System.out.println("Yeni Veriler eklendi.");
                                //yeni hesaplama i??lemi yap??lacak.
                            }
                    }
                }
            }
        });

        myViewModel.makeGetRequest();

        drawFixtures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, FixturesActivity.class);
                startActivity(intent);

            }
        });

    }
}