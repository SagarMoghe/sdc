package com.IceHockeyLeague.LeagueManager.FreeAgent;

public interface IFreeAgent {
    int getID();
    void setID(int id);

    String getName();
    void setName(String name);

    String getPosition();
    void setPosition(String name);

    int getLeagueID();
    void setLeagueID(int id);

    int getAge();
    void setAge(int age);

    int getSkating();
    void setSkating(int value);

    int getShooting();
    void setShooting(int value);

    int getChecking();
    void setChecking(int value);

    int getSaving();
    void setSaving(int value);

    boolean isValid();
}
