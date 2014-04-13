package com.allstarxi.app.model;

/**
 * Created by liam on 4/13/14.
 */
public class LeagueData
{
    public String name;
    public String rank;
    public String teams;

    public LeagueData() {
        super();
    }

    public LeagueData(String name, String rank, String teams)
    {
        super();

        this.name  = name;
        this.rank  = rank;
        this.teams = teams;

    }
}
