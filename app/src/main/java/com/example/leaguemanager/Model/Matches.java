package com.example.leaguemanager.Model;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class Matches {


    public Matches(Team t1, Team t2, String date) {
        this.team1 = t1;
        this.team2 = t2;
        this.date = date;
    }
    public Matches() {

    }


    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    Team team1, team2;
    String date;



}
