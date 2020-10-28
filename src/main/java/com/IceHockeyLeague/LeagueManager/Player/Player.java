package com.IceHockeyLeague.LeagueManager.Player;

import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IInjuryConfig;

import java.time.LocalDate;

public class Player implements IPlayer {
    private String name;
    private int age;
    private IPlayerStats playerStats;
    private boolean isInjured;
    private int daysInjured;
    private LocalDate injuryDate;
    private boolean isRetired;
    private LocalDate retirementDate;

    public Player() {
        configureDefaults();
    }

    private void configureDefaults() {
        isInjured = false;
        isRetired = false;
    }

    @Override
    public String getPlayerName() {
        return name;
    }

    @Override
    public void setPlayerName(String name) {
        this.name = name;
    }

    @Override
    public int getPlayerAge() {
        return age;
    }

    @Override
    public void setPlayerAge(int age) {
        this.age = age;
    }

    @Override
    public boolean getInjuredStatus() {
        return isInjured;
    }

    @Override
    public void setInjuredStatus(boolean isInjured) {
        this.isInjured = isInjured;
    }

    @Override
    public int getDaysInjured() {
        return daysInjured;
    }

    @Override
    public void setDaysInjured(int days) {
        daysInjured = days;
    }

    @Override
    public LocalDate getInjuryDate() {
        return injuryDate;
    }

    @Override
    public void setInjuryDate(LocalDate injuryDate) {
        this.injuryDate = injuryDate;
    }

    @Override
    public LocalDate getRetirementDate() {
        return retirementDate;
    }

    @Override
    public void setRetirementDate(LocalDate retirementDate) {
        this.retirementDate = retirementDate;
    }

    @Override
    public boolean getIsRetired() {
        return isRetired;
    }

    @Override
    public void setIsRetired(boolean isRetired) {
        this.isRetired = isRetired;
    }

    @Override
    public IPlayerStats getPlayerStats() {
        return playerStats;
    }

    @Override
    public void setPlayerStats(IPlayerStats playerStats) {
        this.playerStats = playerStats;
    }

    @Override
    public float calculateStrength(IPlayerStats stats) {
        return stats.calculateStrength();
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public boolean isInjured(IPlayerInjuryManager playerInjuryManager, IInjuryConfig injuryConfig, LocalDate currentDate) {
        return playerInjuryManager.isInjured(this, injuryConfig, currentDate);
    }

    @Override
    public boolean isRecovered(IPlayerInjuryManager playerInjuryManager, LocalDate currentDate) {
        return playerInjuryManager.isRecovered(this, currentDate);
    }

}
