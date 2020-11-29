package com.IceHockeyLeague.LeagueManager.Coach;

import java.util.List;

public class Coach implements ICoach {
    private int coachId;
    private int teamId;
    private int leagueId;
    private String coachName;
    private ICoachStats coachStats;

    public Coach() {
        setDefaults();
    }

    private void setDefaults() {
        coachId = -1;
        teamId = -1;
        leagueId = -1;
    }

    @Override
    public int getCoachId() {
        return coachId;
    }

    @Override
    public void setCoachId(int id) {
        coachId = id;
    }

    @Override
    public String getCoachName() {
        return coachName;
    }

    @Override
    public void setCoachName(String name) {
        coachName = name;
    }

    @Override
    public int getTeamId() {
        return teamId;
    }

    @Override
    public void setTeamId(int id) {
        teamId = id;
    }

    @Override
    public int getLeagueId() {
        return leagueId;
    }

    @Override
    public void setLeagueId(int id) {
        leagueId = id;
    }

    @Override
    public ICoachStats getCoachStats() {
        return coachStats;
    }

    @Override
    public void setCoachStats(ICoachStats stats) {
        if (stats == null) {
            throw new IllegalArgumentException();
        }
        coachStats = stats;
    }

    @Override
    public boolean isValid() {
        boolean valid = true;
        if (isNullOrEmpty(coachName)) {
            valid = false;
        }
        return valid;
    }

    @Override
    public boolean isCoachNameExist(List<ICoach> coaches, String coachName) {
        boolean isExist = false;
        for (ICoach coach : coaches) {
            if (coach.getCoachName().equalsIgnoreCase(coachName)) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }

    @Override
    public boolean isNullOrEmpty(String coachName) {
        return (coachName == null || coachName.equals(""));
    }

    @Override
    public boolean saveTeamCoach(ICoachPersistence coachDB) {
        return coachDB.saveTeamCoach(this);
    }

    @Override
    public boolean saveLeagueCoach(ICoachPersistence coachDB) {
        return coachDB.saveLeagueCoach(this);
    }

    @Override
    public boolean loadTeamCoach(ICoachPersistence coachDB, ICoach coach) {
        return coachDB.loadTeamCoach(teamId, this);
    }

}
