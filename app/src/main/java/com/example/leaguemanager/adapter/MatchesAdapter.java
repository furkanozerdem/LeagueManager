package com.example.leaguemanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leaguemanager.Model.Matches;
import com.example.leaguemanager.Model.Team;
import com.example.leaguemanager.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Matches> matchesList, matchesListFull;

    public MatchesAdapter(Context context, ArrayList<Matches> list) {
        this.context = context;
        this.matchesList = list;
        this.matchesListFull = list;
    }

    public ArrayList<Matches> setMatches(int indexNum) {
        //14 matches per week
       this.matchesList = new ArrayList<>(this.matchesListFull.subList(indexNum*14,(indexNum+1)*14));
        return matchesList;
    }



    @NonNull
    @Override
    public MatchesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_matches, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.team1Name.setText(this.matchesList.get(position).getTeam1().getTeamName());
        holder.team2Name.setText(this.matchesList.get(position).getTeam2().getTeamName());
        holder.team1Code.setText(this.matchesList.get(position).getTeam1().getTeamCode());
        holder.team2Code.setText(this.matchesList.get(position).getTeam2().getTeamCode());
        holder.date.setText(this.matchesList.get(position).getDate());

        Picasso.get().load(this.matchesList.get(position).getTeam1().getPhotoURL()).into(holder.team1URL);
        Picasso.get().load(this.matchesList.get(position).getTeam2().getPhotoURL()).into(holder.team2URL);

    }

    @Override
    public int getItemCount() {
        if (this.matchesList != null) {
            Team.numberOfTeam = matchesList.size();
            return matchesList.size();
        }
        return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView team1Name, team2Name, date, team1Code, team2Code;
    ImageView team1URL, team2URL;
        public MyViewHolder(View itemView) {
            super(itemView);
            team1Name = itemView.findViewById(R.id.teamOneName);
            team2Name = itemView.findViewById(R.id.teamTwoName);
            date = itemView.findViewById(R.id.matchDate);
            team1URL = itemView.findViewById(R.id.teamOnePic);
            team2URL = itemView.findViewById(R.id.teamTwoPic);
            team1Code =itemView.findViewById(R.id.teamOneCode);
            team2Code = itemView.findViewById(R.id.teamTwoCode);

        }
    }

}
