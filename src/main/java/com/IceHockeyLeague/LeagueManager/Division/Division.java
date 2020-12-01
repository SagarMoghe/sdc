package com.IceHockeyLeague.LeagueManager.Division;

import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.ArrayList;
import java.util.List;

public class Division implements IDivision {
    private int divisionID;
    private String divisionName;
    private int conferenceID;
    private List<ITeam> teams;

    public Division() {
        setDefaults();
    }

    private void setDefaults() {
        divisionID = -1;
        conferenceID = -1;
        teams = new ArrayList<>();
    }

    @Override
    public int getDivisionID() {
        return divisionID;
    }

    @Override
    public void setDivisionID(int id) {
        divisionID = id;
    }

    @Override
    public String getDivisionName() {
        return divisionName;
    }

    @Override
    public void setDivisionName(String name) {
        divisionName = name;
    }

    @Override
    public int getConferenceID() {
        return conferenceID;
    }

    @Override
    public void setConferenceID(int id) {
        conferenceID = id;
    }

    @Override
    public ITeam getTeamById(int id) {
        return null;
    }

    @Override
    public void addTeam(ITeam team) {
        teams.add(team);
    }

    @Override
    public List<ITeam> getTeams() {
        return teams;
    }

    @Override
    public void setTeams(List<ITeam> teams) {
        this.teams = teams;
    }

    @Override
    public boolean isNullOrEmpty(String divisionName) {
        return (divisionName == null || divisionName.equals(""));
    }

    @Override
    public boolean isDivisionNameExist(List<IDivision> divisions,String divisionName) {
        boolean isExist = false;
        for(IDivision d : divisions){
            if(d.getDivisionName().equalsIgnoreCase(divisionName)){
                isExist = true;
                break;
            }
        }
        return isExist;
    }
}
