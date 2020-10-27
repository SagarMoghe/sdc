package com.IceHockeyLeague.LeagueManager.Manager;

import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.Player.IFreeAgent;

import java.util.List;

public class Manager implements IManager {
    private int managerID;
    private String managerName;
    private int teamID;
    private int leagueID;

    public Manager() {
        setDefaults();
    }

    @Override
    public int getManagerID() {
        return managerID;
    }

    @Override
    public void setManagerID(int id) {
        managerID = id;
    }

    @Override
    public String getManagerName() {
        return managerName;
    }

    @Override
    public void setManagerName(String name) {
        managerName = name;
    }

    @Override
    public int getTeamID() {
        return teamID;
    }

    @Override
    public void setTeamID(int id) {
        teamID = id;
    }

    @Override
    public int getLeagueID() {
        return leagueID;
    }

    @Override
    public void setLeagueID(int id) {
        leagueID = id;
    }

    private void setDefaults() {
        managerID = -1;
        teamID = -1;
        leagueID = -1;
    }

    @Override
    public boolean saveTeamManager(IManagerPersistence managerDB) {
        return managerDB.saveTeamManager(this);
    }

    @Override
    public boolean saveLeagueManager(IManagerPersistence managerDB) {
        return managerDB.saveLeagueManager(this);
    }

    @Override
    public boolean loadTeamManager(IManagerPersistence managerDB, IManager manager) {
        return managerDB.loadTeamManager( teamID,this);
    }

    @Override
    public boolean isNullOrEmpty(String managerName) {
        if(managerName == null || managerName.equals("")){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean isManagerNameExist(List<IManager> managers, String managerName) {
        boolean isExist = false;
        for(IManager m : managers){
            if(m.getManagerName().equalsIgnoreCase(managerName)){
                isExist = true;
                break;
            }
        }
        return isExist;
    }
}
