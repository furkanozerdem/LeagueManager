package com.example.leaguemanager.network;

import com.example.leaguemanager.Model.Team;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("teams")
    Call<ArrayList<Team>> getTeamList();
}
