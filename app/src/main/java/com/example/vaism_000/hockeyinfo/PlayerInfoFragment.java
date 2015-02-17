package com.example.vaism_000.hockeyinfo;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

public class PlayerInfoFragment extends android.support.v4.app.Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String name;
    private String draft;
    private PlayerCrawler player;

    public static PlayerInfoFragment newInstance(PlayerCrawler player) {
        PlayerInfoFragment fragment = new PlayerInfoFragment();
        Bundle args = new Bundle();
        args.putParcelable("player", player);
        //args.putString(ARG_PARAM2, draft);
        fragment.setArguments(args);
        return fragment;
    }

    public PlayerInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.player = getArguments().getParcelable("player");
            //this.draft = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_player_info, container, false);
        TextView nameView = (TextView) view.findViewById(R.id.name);
        TextView draftView = (TextView) view.findViewById(R.id.draft);
        TextView positionView = (TextView) view.findViewById(R.id.position);
        TextView heightWeightView = (TextView) view.findViewById(R.id.heightAndWeight);
        TextView birthView = (TextView) view.findViewById(R.id.birthdate);
        TextView shootCatch = (TextView) view.findViewById(R.id.shoots);
        TextView hofView = (TextView) view.findViewById(R.id.hallOfFame);
        TableRow hofRow = (TableRow) view.findViewById(R.id.hofRow);

        nameView.setText(this.player.playerName);
        draftView.setText(this.player.drafted);
        positionView.setText(this.player.position);
        shootCatch.setText(this.player.shoots);
        birthView.setText(this.player.birthdate);
        heightWeightView.setText(this.player.height + "/" + this.player.weight);
        if (!this.player.hallOfFame.equals("")) {
            hofView.setText("Hall of Fame");
        } else {
            hofRow.removeView(hofView);
            nameView.setPadding(0, 0, 0, 10);
        }
        return view;
    }
}
