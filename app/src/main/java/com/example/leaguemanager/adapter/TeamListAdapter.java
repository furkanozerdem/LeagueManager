package com.example.leaguemanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leaguemanager.Model.Team;
import com.example.leaguemanager.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Team> teamList;

    public TeamListAdapter(Context context, ArrayList<Team> list) {
        this.context = context;
        this.teamList = list;
    }

    public void setTeamList(ArrayList<Team> newList) {
        this.teamList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TeamListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.team_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.teamName.setText(this.teamList.get(position).getTeamName());
        holder.teamCode.setText(this.teamList.get(position).getTeamCode());
        Picasso.get().load(this.teamList.get(position).getPhotoURL()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (this.teamList != null) {
            Team.numberOfTeam = teamList.size();
            return teamList.size();
        }
        return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView teamName, teamCode;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            teamName = itemView.findViewById(R.id.teamName);
            teamCode = itemView.findViewById(R.id.teamCode);
            imageView = itemView.findViewById(R.id.profile_image);
        }
    }

}
