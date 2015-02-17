package com.example.vaism_000.hockeyinfo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class SearchActivity extends ActionBarActivity {
    HashMap<String, String> searchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_search);
        Intent intent = getIntent();

        // Retrieve Search Query
        searchResult = (HashMap<String, String>)intent.getSerializableExtra("searchResult");
        String[] searchNames = searchResult.keySet().toArray(new String[searchResult.size()]);

        ListAdapter searchAdapter = new ArrayAdapter<String>(this,
                                    android.R.layout.simple_list_item_1, searchNames);
        ListView searchList = (ListView) findViewById(R.id.searchList);
        searchList.setAdapter(searchAdapter);

        // When item is selected go to appropriate user page
        searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Get player name and call SearchCrawlerTask
                String player = String.valueOf(adapterView.getItemAtPosition(position));
                new SearchCrawlerTask(SearchActivity.this).execute(searchResult.get(player), "URL");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_search, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
