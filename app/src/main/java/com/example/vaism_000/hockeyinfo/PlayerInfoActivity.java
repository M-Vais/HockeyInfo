package com.example.vaism_000.hockeyinfo;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;


public class PlayerInfoActivity extends FragmentActivity {
    private FragmentPagerAdapter adapterViewPager;
    private PlayerCrawler player;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_info);
        Intent intent = getIntent();
        player = intent.getExtras().getParcelable("player");

        // Initialize the ViewPager and set an adapter
        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new PlayerStatsAdapter(getSupportFragmentManager(), player);
        vpPager.setAdapter(adapterViewPager);

        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(vpPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_player_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class PlayerStatsAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;
        private PlayerCrawler pl;

        public PlayerStatsAdapter(FragmentManager fm, PlayerCrawler pl) {
            super(fm);
            this.pl = pl;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return PlayerInfoFragment.newInstance(this.pl);
                case 1:
                    if (this.pl.position.equals("G")) {
                        return GoalieSeasonFragment.newInstance(this.pl);
                    } else {
                        return PlayerSeasonFragment.newInstance(this.pl);
                    }
                case 2:
                    if (this.pl.position.equals("G")) {
                        return GoaliePlayoffFragment.newInstance(this.pl);
                    }
                    return PlayerPlayoffFragment.newInstance(this.pl);
                default:
                    return null;
            }
        }

        public CharSequence getPageTitle(int position) {
            switch(position) {
                case 0:
                    return "Info";
                case 1:
                    return "Season";
                case 2:
                    return "Playoff";
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }
    }
}
