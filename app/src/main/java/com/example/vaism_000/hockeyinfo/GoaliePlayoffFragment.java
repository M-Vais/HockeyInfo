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

public class GoaliePlayoffFragment extends android.support.v4.app.Fragment {
    public PlayerCrawler player;


    public static GoaliePlayoffFragment newInstance(PlayerCrawler player) {
        GoaliePlayoffFragment fragment = new GoaliePlayoffFragment();
        Bundle args = new Bundle();
        args.putParcelable("player", player);
        fragment.setArguments(args);
        return fragment;
    }

    public GoaliePlayoffFragment() {
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
        View view = inflater.inflate(R.layout.fragment_goalie_playoff, container, false);

        TableLayout table = (TableLayout) view.findViewById(R.id.playoffTable);
        TableRow tableRows[] = new TableRow[player.playoffStats.size() + 1];

        // Layout Params
        TableRow.LayoutParams textParam = new TableRow.LayoutParams(0,
                TableRow.LayoutParams.WRAP_CONTENT);
        textParam.gravity = Gravity.CENTER;
        textParam.weight = 2.0f;

        TableRow.LayoutParams textParam2 = new TableRow.LayoutParams(0,
                TableRow.LayoutParams.WRAP_CONTENT);
        textParam2.gravity = Gravity.CENTER;
        textParam2.weight = 1f;


        TableRow.LayoutParams dividerParam = new TableRow.LayoutParams
                (TableRow.LayoutParams.MATCH_PARENT, 1);
        dividerParam.span = 2;

        TableRow.LayoutParams rowParam = new TableRow.LayoutParams
                (TableRow.LayoutParams.MATCH_PARENT, 1);

        // Table Header
        tableRows[0] = new TableRow(getActivity());
        TextView seasonView = new TextView(getActivity());
        TextView teamView = new TextView(getActivity());
        TextView gpView = new TextView(getActivity());
        //TextView gsView = new TextView(getActivity());
        TextView winsView = new TextView(getActivity());
        TextView lossesView = new TextView(getActivity());
        TextView tiesView = new TextView(getActivity());
        TextView gaaView = new TextView(getActivity());
        TextView saveView = new TextView(getActivity());

        seasonView.setText("Season");
        seasonView.setGravity(Gravity.CENTER);
        seasonView.setLayoutParams(textParam);

        teamView.setText("Team");
        teamView.setGravity(Gravity.CENTER);
        teamView.setLayoutParams(textParam2);

        gpView.setText("GP");
        gpView.setGravity(Gravity.CENTER);
        gpView.setLayoutParams(textParam2);

        //gsView.setText("GS");
        //gsView.setGravity(Gravity.CENTER);
        //gsView.setLayoutParams(textParam);

        winsView.setText("W");
        winsView.setGravity(Gravity.CENTER);
        winsView.setLayoutParams(textParam2);

        lossesView.setText("L");
        lossesView.setGravity(Gravity.CENTER);
        lossesView.setLayoutParams(textParam2);

        tiesView.setText("T/OT");
        tiesView.setGravity(Gravity.CENTER);
        tiesView.setLayoutParams(textParam2);

        gaaView.setText("GAA");
        gaaView.setGravity(Gravity.CENTER);
        gaaView.setLayoutParams(textParam2);

        saveView.setText("SV%");
        saveView.setGravity(Gravity.CENTER);
        saveView.setLayoutParams(textParam2);

        tableRows[0].addView(seasonView);
        tableRows[0].addView(teamView);
        tableRows[0].addView(gpView);
        //tableRows[0].addView(gsView);
        tableRows[0].addView(winsView);
        tableRows[0].addView(lossesView);
        tableRows[0].addView(tiesView);
        tableRows[0].addView(gaaView);
        tableRows[0].addView(saveView);
        tableRows[0].setPadding(0, 10, 0, 10);

        TableRow trView = new TableRow(getActivity());
        TextView lineView = new TextView(getActivity());
        lineView.setText("");
        lineView.setLayoutParams(dividerParam);
        trView.setLayoutParams(rowParam);
        trView.setBackgroundColor(getResources().getColor(R.color.line));
        trView.addView(lineView);

        table.addView(tableRows[0]);
        table.addView(trView);

        for (int i = 1; i < tableRows.length; i++) {
            tableRows[i] = new TableRow(getActivity());
            tableRows[i].setPadding(0, 10, 0, 10);

            TextView sView = new TextView(getActivity());
            sView.setText(player.playoffStats.get(i - 1)[0]);
            sView.setGravity(Gravity.CENTER);
            sView.setLayoutParams(textParam);

            TextView tView = new TextView(getActivity());
            tView.setText(player.playoffStats.get(i - 1)[2]);
            tView.setGravity(Gravity.CENTER);
            tView.setLayoutParams(textParam2);

            TextView gpView2 = new TextView(getActivity());
            gpView2.setText(player.playoffStats.get(i - 1)[5]);
            gpView2.setGravity(Gravity.CENTER);
            gpView2.setLayoutParams(textParam2);

            //gsView.setText("GS");
            //gsView.setGravity(Gravity.CENTER);
            //gsView.setLayoutParams(textParam);

            TextView wView = new TextView(getActivity());
            wView.setText(player.playoffStats.get(i - 1)[7]);
            wView.setGravity(Gravity.CENTER);
            wView.setLayoutParams(textParam2);

            TextView lView = new TextView(getActivity());
            lView.setText(player.playoffStats.get(i - 1)[8]);
            lView.setGravity(Gravity.CENTER);
            lView.setLayoutParams(textParam2);

            TextView tiView = new TextView(getActivity());
            tiView.setText(player.playoffStats.get(i - 1)[9]);
            tiView.setGravity(Gravity.CENTER);
            tiView.setLayoutParams(textParam2);

            TextView gaView = new TextView(getActivity());
            gaView.setText(player.playoffStats.get(i - 1)[14]);
            gaView.setGravity(Gravity.CENTER);
            gaView.setLayoutParams(textParam2);

            TextView saView = new TextView(getActivity());
            saView.setText(player.playoffStats.get(i - 1)[13]);
            saView.setGravity(Gravity.CENTER);
            saView.setLayoutParams(textParam2);

            TableRow trView2 = new TableRow(getActivity());
            TextView lineView2 = new TextView(getActivity());
            lineView2.setText("");
            lineView2.setLayoutParams(dividerParam);
            trView2.setLayoutParams(rowParam);
            trView2.setBackgroundColor(getResources().getColor(R.color.line));
            trView2.addView(lineView2);

            tableRows[i].addView(sView);
            tableRows[i].addView(tView);
            tableRows[i].addView(gpView2);
            //tableRows[0].addView(gsView);
            tableRows[i].addView(wView);
            tableRows[i].addView(lView);
            tableRows[i].addView(tiView);
            tableRows[i].addView(gaView);
            tableRows[i].addView(saView);

            table.addView(tableRows[i]);
            table.addView(trView2);
        }

        return view;
    }
}
