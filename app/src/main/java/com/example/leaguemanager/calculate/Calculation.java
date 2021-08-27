package com.example.leaguemanager.calculate;

import android.content.Context;

import com.example.leaguemanager.Model.Matches;
import com.example.leaguemanager.Model.Team;
import com.example.leaguemanager.localDB.DB;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Calculation {


    public static ArrayList<Matches> Calculate(ArrayList<Team> teamList) {
        //this function will set up fixtures.
        ArrayList<Matches> teamMatches = new ArrayList<>();

        LocalDate today = LocalDate.now();
        System.out.println("Current date: " + today);

        //add 2 week to the current date
      //  LocalDate next2Week = today.plus(1, ChronoUnit.WEEKS);
      //  System.out.println("Next week: " + next2Week);


        //14 matches per week
        for(int i=0;i<teamList.size()-1;i++) {
            for(int k=i+1;k<teamList.size();k++) {
                Matches match = new Matches();
                match.setTeam1(teamList.get(i));
                match.setTeam2(teamList.get(k));
                teamMatches.add(match);
            }
        }

        Collections.shuffle(teamMatches); //mix the arrayList
        teamMatches.addAll(teamMatches); //add same elements for 2. half of fixture


        //14 matches per week
        for(int i=0;i<teamMatches.size();i++) {
            teamMatches.get(i).setDate(today.plus(i/14, ChronoUnit.WEEKS).toString());
        }

        System.out.println("Team matches size : " + teamMatches.size());
        return teamMatches;
    }
}
