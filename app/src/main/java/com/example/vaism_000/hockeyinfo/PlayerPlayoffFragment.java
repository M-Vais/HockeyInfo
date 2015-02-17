package com.example.vaism_000.hockeyinfo;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class PlayerPlayoffFragment extends android.support.v4.app.Fragment {
    TableLayout tl;
    public PlayerCrawler player;

    public static PlayerPlayoffFragment newInstance(PlayerCrawler player) {
        PlayerPlayoffFragment fragment = new PlayerPlayoffFragment();
        Bundle args = new Bundle();
        args.putParcelable("player", player);
        fragment.setArguments(args);
        return fragment;
    }

    public PlayerPlayoffFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.player = getArguments().getParcelable("player");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_player_playoff, container, false);
        tl = (TableLayout) view.findViewById(R.id.playoffTable);
        TableRow tableRows[] = new TableRow[player.playoffStats.size() + 1];
        TableRow.LayoutParams textParam = new TableRow.LayoutParams(0,
                TableRow.LayoutParams.WRAP_CONTENT);
        textParam.gravity = Gravity.CENTER;
        textParam.weight = 1.0f;

        TableRow.LayoutParams lineParam = new TableRow.LayoutParams
                (TableRow.LayoutParams.MATCH_PARENT,
                        1);
        lineParam.span = 2;

        TableRow.LayoutParams tableRowParam = new TableRow.LayoutParams
                (TableRow.LayoutParams.MATCH_PARENT,
                        1);

        // Basic Header Info
        tableRows[0] = new TableRow(getActivity());
        TextView seasonView = new TextView(getActivity());
        TextView teamView = new TextView(getActivity());
        TextView gamesPlayedView = new TextView(getActivity());
        TextView goalsView = new TextView(getActivity());
        TextView assistsView = new TextView(getActivity());
        TextView pointsView = new TextView(getActivity());

        seasonView.setText("Season");
        seasonView.setGravity(Gravity.CENTER);
        seasonView.setLayoutParams(textParam);

        teamView.setText("Team");
        teamView.setGravity(Gravity.CENTER);
        teamView.setLayoutParams(textParam);

        gamesPlayedView.setText("GP");
        gamesPlayedView.setGravity(Gravity.CENTER);
        gamesPlayedView.setLayoutParams(textParam);

        goalsView.setText("G");
        goalsView.setGravity(Gravity.CENTER);
        goalsView.setLayoutParams(textParam);

        assistsView.setText("A");
        assistsView.setGravity(Gravity.CENTER);
        assistsView.setLayoutParams(textParam);

        pointsView.setText("P");
        pointsView.setGravity(Gravity.CENTER);
        pointsView.setLayoutParams(textParam);

        TableRow trView = new TableRow(getActivity());
        TextView lineView = new TextView(getActivity());
        lineView.setText("");
        lineView.setLayoutParams(lineParam);
        trView.setLayoutParams(tableRowParam);
        trView.setBackgroundColor(getResources().getColor(R.color.line));

        trView.addView(lineView);
        tableRows[0].addView(seasonView);
        tableRows[0].addView(teamView);
        tableRows[0].addView(gamesPlayedView);
        tableRows[0].addView(goalsView);
        tableRows[0].addView(assistsView);
        tableRows[0].addView(pointsView);
        tableRows[0].setPadding(0, 10, 0, 10);
        tl.addView(tableRows[0]);
        tl.addView(trView);

        for (int i = 1; i < tableRows.length; i++) {
            tableRows[i] = new TableRow(getActivity());
            tableRows[i].setPadding(0, 10, 0, 10);

            TextView sView = new TextView(getActivity());
            sView.setText(this.player.playoffStats.get(i-1)[0]);
            sView.setGravity(Gravity.CENTER);
            sView.setLayoutParams(textParam);

            TextView tView = new TextView(getActivity());
            tView.setText(this.player.playoffStats.get(i-1)[2]);
            tView.setGravity(Gravity.CENTER);
            tView.setLayoutParams(textParam);

            TextView gPView = new TextView(getActivity());
            gPView.setText(this.player.playoffStats.get(i-1)[5]);
            gPView.setGravity(Gravity.CENTER);
            gPView.setLayoutParams(textParam);

            TextView gView = new TextView(getActivity());
            gView.setText(this.player.playoffStats.get(i-1)[6]);
            gView.setGravity(Gravity.CENTER);
            gView.setLayoutParams(textParam);

            TextView aView = new TextView(getActivity());
            aView.setText(this.player.playoffStats.get(i-1)[7]);
            aView.setGravity(Gravity.CENTER);
            aView.setLayoutParams(textParam);

            TextView pView = new TextView(getActivity());
            pView.setText(this.player.playoffStats.get(i-1)[8]);
            pView.setGravity(Gravity.CENTER);
            pView.setLayoutParams(textParam);

            TableRow trSView = new TableRow(getActivity());
            TextView lineSView = new TextView(getActivity());
            lineSView.setText("");
            lineSView.setLayoutParams(lineParam);
            trSView.setLayoutParams(tableRowParam);
            trSView.setBackgroundColor(getResources().getColor(R.color.line));
            trSView.addView(lineSView);

            tableRows[i].addView(sView);
            tableRows[i].addView(tView);
            tableRows[i].addView(gPView);
            tableRows[i].addView(gView);
            tableRows[i].addView(aView);
            tableRows[i].addView(pView);
            tl.addView(tableRows[i]);
            tl.addView(trSView);
        }
        return view;
    }
}
