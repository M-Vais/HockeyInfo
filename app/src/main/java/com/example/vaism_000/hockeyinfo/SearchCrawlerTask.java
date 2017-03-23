package com.example.vaism_000.hockeyinfo;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.Gravity;
import android.widget.Toast;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by vaism_000 on 2014-12-29.
 */
public class SearchCrawlerTask extends AsyncTask<String, Void, Document> {
    private String SEARCHURL = "http://www.hockey-reference.com/search/search.fcgi?search=";
    private String PLAYER = "/players/";
    public Document doc;
    private PlayerCrawler player = null;
    private HashMap<String, String> searchResult = new HashMap<>();;
    public Context context;

    public SearchCrawlerTask(Context context) {
        this.context = context;
    }

    @Override
    protected Document doInBackground(String... searchInput) {
        String searchURL;
        // Checks if searchInput passed is a player Search or a URL
        searchURL = getSearchURL(searchInput);

        // Connection to site

        System.out.println("URL: " + searchURL);

        try {
            this.doc = Jsoup.connect(searchURL).get();
        } catch (IOException e) {
            Toast.makeText(context, "Search Failed. Try Again", Toast.LENGTH_LONG);
            //e.printStackTrace();
        }
        if (isPlayerPage()) {
            // When it connects to a player page
            this.player = new PlayerCrawler(this.doc, searchInput[0]);
            this.player.extractInfo();
        }
        else if (isSearchResult()) {
            // When multiple search queries are returned
            this.searchResult = getSearchResult();
        }
        return this.doc;
    }

    @Override
    protected void onPostExecute(Document document) {
        super.onPostExecute(document);
        // Player Page
        if (isPlayerPage()) {
            Intent intent = new Intent(context, PlayerInfoActivity.class);
            intent.putExtra("player", player);
            context.startActivity(intent);
        }
        // Empty Search Results
        else if (!isSearchResult()) {
            Toast toast;
            toast = Toast.makeText(context, "No Search Results. Try Again", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
        }
        // Possible results
        else {
            Intent intent = new Intent(context, SearchActivity.class);
            intent.putExtra("searchResult", searchResult);
            context.startActivity(intent);
        }
    }

    // Helper Methods
    private String searchFormat(String searchInput) {
        // Helps format string for search query
        return searchInput.replace(" ", "+");
    }

    private boolean isPlayerPage() {
        // Returns true iff it connects to a player page
        return doc.location().contains(PLAYER);
    }

    private boolean isSearchResult() {
        // Returns true iff the result returns search results
        Elements searchResult = this.doc.select("#players");

        if(searchResult.isEmpty()) {
            return false;
        }
        else {
            return true;
        }
    }

    private HashMap<String, String> getSearchResult() {
        Elements search = doc.select("#players").first().select(".search-item");
        for (Element el : search) {
            String playerName = el.select("a").first().text().trim();
            String playerURL = el.select("a").first().attr("href").trim();
            searchResult.put(playerName, playerURL);
        }
        return searchResult;
    }

    private String getSearchURL(String[] searchInput) {
        if (searchInput[1].equals("Player")) {
            return SEARCHURL + searchFormat(searchInput[0]);
        }
        else {
            return  "http://www.hockey-reference.com" + searchInput[0];
        }
    }
}
