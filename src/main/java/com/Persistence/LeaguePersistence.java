package com.Persistence;

import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.SerializeDeserializeLeagueObject.ISerialize;
import com.IceHockeyLeague.SerializeDeserializeLeagueObject.Serialize;

import java.util.List;

public class LeaguePersistence implements ILeaguePersistence {
    public boolean saveLeague(ILeague league){
        ISerialize s = new Serialize();
        String teamName = "";
        List<IConference> conferences;
        List<IDivision> divisions;
        List<ITeam> teams;
        conferences = league.getConferences();
        for(IConference c : conferences){
            divisions = c.getDivisions();
            for(IDivision d : divisions){
                teams = d.getTeams();
                for(ITeam t : teams){
                    if(t.getIsUserCreated()){
                        teamName = t.getTeamName();
                        break;
                    }
                }
            }
        }
        s.serializeLeagueObject(league,teamName);
        return true;
    }
    public boolean loadLeague(){
        return true;
    }
}
