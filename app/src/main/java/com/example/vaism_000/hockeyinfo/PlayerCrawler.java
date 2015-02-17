package com.example.vaism_000.hockeyinfo;

import android.os.Parcel;
import android.os.Parcelable;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by vaism_000 on 2014-12-30.
 */
public class PlayerCrawler implements Parcelable {
    public String playerName = "";
    public String position = "Unknown";
    public String shoots = "Unknown";
    public String height = "Unknown";
    public String weight = "Unknown";
    public String birthdate = "Unknown";
    public String died = "";
    public String drafted = "Undrafted";
    public String hallOfFame = "";
    public ArrayList<String[]> seasonStats = new ArrayList();
    public ArrayList<String[]> playoffStats = new ArrayList();
    public Document doc;



    public PlayerCrawler(Document doc, String playerName) {
        this.doc = doc;
        this.playerName = playerName;
    }

    public PlayerCrawler(Parcel in) {
        this.playerName = in.readString();
        this.position = in.readString();
        this.shoots = in.readString();
        this.height = in.readString();
        this.weight = in.readString();
        this.birthdate = in.readString();
        this.died = in.readString();
        this.drafted = in.readString();
        this.hallOfFame = in.readString();
        this.seasonStats = (ArrayList<String[]>) in.readSerializable();
        this.playoffStats =(ArrayList<String[]>) in.readSerializable();
    }

    public void extractInfo() {
        getPersonalInfo();
        getSeasonStats();
        getPlayoffStats();
    }

    private void getPersonalInfo() {
        Elements player = doc.select("div.clear_left");
        String[] stats = player.text().split("(?=Position:|Shoots:|Height:|Weight:|Born:|Died:|"
                + "Hall of Fame:|Draft:|Contract:|Catches:|As Coach:)");

        this.playerName = getName(stats[0]);
        for (int i = 1; i < stats.length; i++) {
            if (stats[i].contains("Position")) {
                this.position = getPosition(stats[i]);
            } if (stats[i].contains("Shoots")) {
                this.shoots = getShoot(stats[i]);
            } if (stats[i].contains("Height")) {
                this.height = getHeight(stats[i]);
            } if (stats[i].contains("Weight")) {
                this.weight = getWeight(stats[i]);
            } if (stats[i].contains("Draft")) {
                this.drafted = getDraft(stats[i]);
            } if (stats[i].contains("Hall of Fame")) {
                this.hallOfFame = getHOF(stats[i]);
            } if (stats[i].contains("Died")) {
                this.died = getDeath(stats[i]);
            } if (stats[i].contains("Born")) {
                this.birthdate = getBirth(stats[i]);
            } if (stats[i].contains("Catches")) {
                this.shoots = getShoot(stats[i]);
            }
        }

        //if (this.drafted.equals("")) {
        //    this.drafted = "Undrafted";
        //}
    }

    // Removes unwanted characters from string
    private String cleanString(String statToClean) {
        return statToClean.replaceAll("[^\\x00-\\x7F]", "").replaceAll("(\\\\)(.*)", "").trim();
    }

    /* String Format Functions that formats to store */
    private String getName(String name) {
        return cleanString(name);
    }

    private String getPosition(String position) {
        return cleanString(position).replaceAll(" ", "").split(":")[1];
    }

    private String getShoot(String shoot) {
        return cleanString(shoot).replaceAll(" ", "").split(":")[1];
    }

    private String getHeight(String height) {
        return cleanString(height).replaceAll(" ", "").split(":")[1];
    }
    public String getDraft(String draft) {
        String[] draftInfo = cleanString(draft).replaceFirst(" ", "").split(":|,");
        return draftInfo[1].trim() + " - " + draftInfo[2].trim();
    }
    public String getHOF(String HOF) {
        return cleanString(HOF).replaceFirst(" ", "").split(": |\\(")[1];
    }
    public String getDeath(String death) {
        return cleanString(death).replaceFirst(" ", "").split(":")[1];
    }

    public String getBirth(String birth) {
        return cleanString(birth).replaceFirst(" ", "").split(":|in")[1];
    }
    public String getWeight(String weight) {
        return cleanString(weight).replaceFirst(" ", "").split(":")[1];
    }


    private void getSeasonStats() {
        if (!this.position.equals("G")) {
            getPlayerSeasonStats();
        }
        else {
            getGoalieSeasonStats();
        }
    }

    private void getPlayoffStats() {
        if(!this.position.equals("G")) {
            getPlayerPlayoffStats();
        }
        else {
            getGoaliePlayoffStats();
        }
    }

    private void getGoaliePlayoffStats() {
        String[] statsRow; // Temporary array holding values of stats per row
        int count;

        Elements playoff = doc.select("#stats_playoffs_nhl > tbody > tr");
        // Iterate through each row for each season
        for (Element row : playoff) {
            count = 0;
            statsRow = new String[row.select("td").size()];
            // Iterate through the td
            // If empty add "-" to statsRow else add the element
            for (Element data : row.select("td")) {
                if (data.text().equals("")) {
                    statsRow[count] = "-";
                }
                else {
                    statsRow[count] = data.text();
                }
                count++;
            }
            this.playoffStats.add(statsRow);
        }
    }

    private void getPlayerPlayoffStats() {
        String[] statsRow; // Temporary array holding values of stats per row
        int count;

        Elements playoff = doc.select("#stats_playoffs_nhl > tbody > tr");
        // Iterate through each row for each season
        for (Element row : playoff) {
            count = 0;
            statsRow = new String[row.select("td").size()];
            // Iterate through the td
            // If empty add "-" to statsRow else add the element
            for (Element data : row.select("td")) {
                if (data.text().equals("")) {
                    statsRow[count] = "-";
                }
                else {
                    statsRow[count] = data.text();
                }
                count++;
            }
            this.playoffStats.add(statsRow);
        }
    }

    private void getGoalieSeasonStats() {
        String[] statsRow; // Temporary array holding values of stats per row
        int count;

        Elements season = doc.select("#stats_basic_nhl > tbody > tr");
        // Iterate through each row for each season
        for (Element row : season) {
            count = 0;
            statsRow = new String[row.select("td").size()];
            // Iterate through the td
            // If empty add "-" to statsRow else add the element
            for (Element data : row.select("td")) {
                if (data.text().equals("")) {
                    statsRow[count] = "-";
                }
                else {
                    statsRow[count] = data.text();
                }
                count++;
            }
            this.seasonStats.add(statsRow);
        }
    }


    private void getPlayerSeasonStats() {
        Elements season = doc.select("#stats_basic_nhl > tbody > tr");
        for (Element row : season) {
            this.seasonStats.add(row.text().split(" "));
        }
    }

    /* Functions below to make PlayerCrawler parceable */

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(playerName);
        dest.writeString(position);
        dest.writeString(shoots);
        dest.writeString(height);
        dest.writeString(weight);
        dest.writeString(birthdate);
        dest.writeString(died);
        dest.writeString(drafted);
        dest.writeString(hallOfFame);
        dest.writeSerializable(seasonStats);
        dest.writeSerializable(playoffStats);
    }

    public static Creator<PlayerCrawler> CREATOR = new Parcelable.Creator<PlayerCrawler>() {

                @Override
                public PlayerCrawler createFromParcel(Parcel in) {
                    return new PlayerCrawler(in);
                }

                @Override
                public PlayerCrawler[] newArray(int size) {
                    return new PlayerCrawler[0];
                }
    };
}
