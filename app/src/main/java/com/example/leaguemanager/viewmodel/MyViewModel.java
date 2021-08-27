package com.example.leaguemanager.viewmodel;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.leaguemanager.Model.Team;
import com.example.leaguemanager.calculate.Calculation;
import com.example.leaguemanager.network.APIService;
import com.example.leaguemanager.network.RetroInstance;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Team>> teamList;


    public MyViewModel() {
        teamList = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<Team>> getTeamList() {
        return teamList;
    }


    public void makeGetRequest() {
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        Call<ArrayList<Team>> call = apiService.getTeamList();

        call.enqueue(new Callback<ArrayList<Team>>() {
            @Override
            public void onResponse(Call<ArrayList<Team>> call, Response<ArrayList<Team>> response) {
                if (response.isSuccessful()) {
                    teamList.postValue(response.body());
                    System.out.println(response.code());//200
                    //RECORD LOCAL DATABASE

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Team>> call, Throwable t) {
                System.out.println(t.getMessage());
                teamList.postValue(null);
            }
        });

    }



}
