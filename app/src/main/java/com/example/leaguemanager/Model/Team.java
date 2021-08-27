package com.example.leaguemanager.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Team {
    public Team(String id, String name, String code, String URL) {
       this.id = id;
       this.teamName = name;
       this.teamCode = code;
       this.PhotoURL = URL;
    }

    public Team(String name, String code, String URL) {
        this.teamName = name;
        this.teamCode = code;
        this.PhotoURL = URL;
    }


    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }

    public String getPhotoURL() {
        return PhotoURL;
    }

    public void setPhotoURL(String photoURL) {
        PhotoURL = photoURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @SerializedName("TeamName")
    public String teamName;
    @SerializedName("TeamCode")
    public String teamCode;
    @SerializedName("PhotoURL")
    public String PhotoURL;
    public String id;

    public static int numberOfTeam;

    //   public static int numberOfTeam;


}

