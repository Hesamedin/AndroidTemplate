package com.allstarxi.app.model;

/**
 * Created by liam on 4/13/14.
 */
public class MatchData
{
    String leftTeam;
    String rightTeam;
    String time;
    String date;

    public MatchData() { super (); }

    public MatchData(String leftTeam, String rightTeam, String time, String date)
    {
        super();
        this.leftTeam = leftTeam;
        this.rightTeam = rightTeam;
        this.time = time;
        this.date = date;
    }
}

