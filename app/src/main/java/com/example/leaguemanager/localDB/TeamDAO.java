package com.example.leaguemanager.localDB;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.leaguemanager.Model.Team;

public class TeamDAO {

    public void addTeam(Team team, DB db) {
        SQLiteDatabase database = db.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("teamID", team.getId());
       // values.put();


    }

}
