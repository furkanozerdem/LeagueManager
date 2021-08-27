package com.example.leaguemanager.localDB;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.leaguemanager.Model.Matches;
import com.example.leaguemanager.Model.Team;

import java.util.ArrayList;

public class DB extends SQLiteOpenHelper {
    private Context context;
    private static final String DB_NAME = "LeagueManager.db";
    private static final int DB_VERSION = 2;

    private static final String TEAM_TABLE = "Teams";

    private static final String TEAM_ID = "teamID";
    private static final String TEAM_NAME = "teamName";
    private static final String TEAM_CODE = "teamCode";
    private static final String TEAM_PHOTO = "photoURL";


    private static final String MATCHES_TABLE = "Matches";
    private static final String MATCHES_ID = "matchesID";
    private static final String COLUMN_TEAM1_NAME = "teamOneName";
    private static final String COLUMN_TEAM2_NAME = "teamTwoName";
    private static final String COLUMN_TEAM1_URL = "teamOnePhotoURL";
    private static final String COLUMN_TEAM2_URL = "teamTwoPhotoURL";
    private static final String COLUMN_TEAM1_CODE = "teamOneCode";
    private static final String COLUMN_TEAM2_CODE = "teamTwoCode";
    private static final String DATE = "date";


    public DB(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query1 =
                "CREATE TABLE " + MATCHES_TABLE + " (" +
                        COLUMN_TEAM1_NAME + " TEXT, " +
                        COLUMN_TEAM2_NAME + " TEXT, " +
                        COLUMN_TEAM1_CODE + " TEXT, " +
                        COLUMN_TEAM2_CODE + " TEXT, " +
                        COLUMN_TEAM1_URL + " TEXT, " +
                        COLUMN_TEAM2_URL + " TEXT, " +
                        DATE + " TEXT)";

        String query2 =
                "CREATE TABLE " + TEAM_TABLE + " (" + TEAM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        TEAM_NAME + " TEXT, " +
                        TEAM_CODE + " TEXT, " +
                        TEAM_PHOTO + " TEXT)";


        sqLiteDatabase.execSQL(query1);
        sqLiteDatabase.execSQL(query2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MATCHES_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TEAM_TABLE);

        onCreate(sqLiteDatabase);
    }


    public void addTeam(Team team) {
        // String query = "INSERT INTO " + TEAM_TABLE + " (" + TEAM_ID + ", " + TEAM_NAME + ", " + TEAM_CODE + ", " + TEAM_PHOTO  + ")" +
        //         " VALUES " + "(" + team.getId() + ", " +   team.getTeamName() + ", " +  team.getTeamCode() + ", " +  team.getPhotoURL() + ")";

        ContentValues contentValues = new ContentValues();
        contentValues.put(TEAM_ID, team.getId());
        contentValues.put(TEAM_CODE, team.getTeamCode());
        contentValues.put(TEAM_NAME, team.getTeamName());
        contentValues.put(TEAM_PHOTO, team.getPhotoURL());

        getWritableDatabase().insertOrThrow(TEAM_TABLE, null, contentValues);
        getWritableDatabase().close();


        //  this.getWritableDatabase().execSQL(query);

    }

    public ArrayList<Team> getAllTeamFromDB() {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM " + TEAM_TABLE, null);
        ArrayList<Team> result = new ArrayList<>();


        while (cursor.moveToNext()) {
            System.out.println(cursor.getString(0) + " " + cursor.getString(1));
            Team tmp = new Team(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            result.add(tmp);
        }

        return result;
    }


    public void addMatch(Matches matches) {

        ContentValues cValues = new ContentValues();

        cValues.put(COLUMN_TEAM1_NAME, matches.getTeam1().getTeamName());
        cValues.put(COLUMN_TEAM2_NAME, matches.getTeam2().getTeamName());
        cValues.put(COLUMN_TEAM1_CODE, matches.getTeam1().getTeamCode());
        cValues.put(COLUMN_TEAM2_CODE, matches.getTeam2().getTeamCode());
        cValues.put(COLUMN_TEAM1_URL, matches.getTeam1().getPhotoURL());
        cValues.put(COLUMN_TEAM2_URL, matches.getTeam2().getPhotoURL());
        cValues.put(DATE, matches.getDate());


            getWritableDatabase().insertOrThrow(MATCHES_TABLE, null, cValues);
            getWritableDatabase().close();


    }

    public void deleteAllTeam() {
        this.getWritableDatabase().execSQL("DELETE FROM " + TEAM_TABLE);
    }

    public void deleteAllMatches() {
        this.getWritableDatabase().execSQL("DELETE FROM " + MATCHES_TABLE);
    }

    public ArrayList<Matches> getAllMatchesFromDB() {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM " + MATCHES_TABLE, null);
        ArrayList<Matches> result = new ArrayList<>();


        while (cursor.moveToNext()) {
            System.out.println(cursor.getString(0) + " " + cursor.getString(1));
            Team tm1 = new Team(cursor.getString(0), cursor.getString(2), cursor.getString(4));
            Team tm2 = new Team(cursor.getString(1), cursor.getString(3), cursor.getString(5));
            String date = cursor.getString(6);
            Matches m = new Matches(tm1, tm2, date);
            result.add(m);
        }

        return result;

    }

}
