package com.DatabaseTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class FreeAgentPersistenceMock implements IFreeAgentPersistence {
    private final ILeagueManagerFactory leagueManagerFactory;

    public FreeAgentPersistenceMock() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
    }

    @Override
    public boolean saveFreeAgent(IFreeAgent freeAgent) {
        IPlayerStats stats = leagueManagerFactory.createPlayerStats();
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();

        freeAgent.setLeagueID(1);
        freeAgent.setFreeAgentID(1);
        freeAgent.setPlayerName("Mike Two");
        freeAgent.setInjuredStatus(false);
        freeAgent.setDaysInjured(0);
        freeAgent.setInjuryDate(null);
        freeAgent.setRetiredStatus(false);
        freeAgent.setRetirementDate(null);

        stats.setPosition("forward");
        stats.setShooting(10);
        stats.setChecking(2);
        stats.setSaving(14);
        stats.setSkating(18);
        stats.setStrength(29);
        freeAgent.setPlayerStats(stats);

        playerAgeInfo.setBirthDate(LocalDate.of(1996, Month.JULY, 16));
        playerAgeInfo.setAgeInYears(24);
        playerAgeInfo.setElapsedDaysFromLastBDay(123);
        freeAgent.setPlayerAgeInfo(playerAgeInfo);

        return true;
    }

    @Override
    public boolean loadFreeAgents(int leagueId, List<IFreeAgent> freeAgents) {
        IFreeAgent freeAgent = leagueManagerFactory.createFreeAgent();
        IPlayerStats stats = leagueManagerFactory.createPlayerStats();
        IPlayerAgeInfo freeAgentAgeInfo = leagueManagerFactory.createPlayerAgeInfo();

        freeAgent.setFreeAgentID(1);
        freeAgent.setLeagueID(1);
        freeAgent.setPlayerName("Fred One");
        freeAgent.setInjuredStatus(false);
        freeAgent.setDaysInjured(0);
        freeAgent.setInjuryDate(null);
        freeAgent.setRetiredStatus(false);
        freeAgent.setRetirementDate(null);

        stats.setPosition("forward");
        stats.setShooting(10);
        stats.setChecking(2);
        stats.setSaving(14);
        stats.setSkating(18);
        stats.setStrength(29);
        freeAgent.setPlayerStats(stats);

        freeAgentAgeInfo.setBirthDate(LocalDate.of(1997, Month.APRIL, 18));
        freeAgentAgeInfo.setAgeInYears(23);
        freeAgentAgeInfo.setElapsedDaysFromLastBDay(212);
        freeAgent.setPlayerAgeInfo(freeAgentAgeInfo);

        IFreeAgent freeAgent1 = leagueManagerFactory.createFreeAgent();
        IPlayerStats stats1 = leagueManagerFactory.createPlayerStats();
        IPlayerAgeInfo freeAgentAgeInfo1 = leagueManagerFactory.createPlayerAgeInfo();

        freeAgent1.setFreeAgentID(2);
        freeAgent1.setLeagueID(1);
        freeAgent1.setPlayerName("Mike One");
        freeAgent1.setInjuredStatus(true);
        freeAgent1.setDaysInjured(23);
        freeAgent1.setInjuryDate(LocalDate.of(2020, 10, 20));
        freeAgent1.setRetiredStatus(false);
        freeAgent1.setRetirementDate(null);

        stats1.setPosition("defense");
        stats1.setShooting(12);
        stats1.setChecking(18);
        stats1.setSaving(16);
        stats1.setSkating(11);
        stats1.setStrength(35);
        freeAgent1.setPlayerStats(stats1);

        freeAgentAgeInfo1.setBirthDate(LocalDate.of(1993, Month.OCTOBER, 25));
        freeAgentAgeInfo1.setAgeInYears(27);
        freeAgentAgeInfo1.setElapsedDaysFromLastBDay(22);
        freeAgent1.setPlayerAgeInfo(freeAgentAgeInfo1);

        IFreeAgent freeAgent2 = leagueManagerFactory.createFreeAgent();
        IPlayerStats stats2 = leagueManagerFactory.createPlayerStats();
        IPlayerAgeInfo freeAgentAgeInfo2 = leagueManagerFactory.createPlayerAgeInfo();

        freeAgent2.setFreeAgentID(3);
        freeAgent2.setLeagueID(1);
        freeAgent2.setPlayerName("Fred Two");
        freeAgent2.setInjuredStatus(false);
        freeAgent2.setDaysInjured(0);
        freeAgent2.setInjuryDate(null);
        freeAgent2.setRetiredStatus(false);
        freeAgent2.setRetirementDate(null);

        stats2.setPosition("forward");
        stats2.setShooting(19);
        stats2.setChecking(13);
        stats2.setSaving(16);
        stats2.setSkating(18);
        stats2.setStrength(43.5f);
        freeAgent2.setPlayerStats(stats2);

        freeAgentAgeInfo.setBirthDate(LocalDate.of(1991, Month.APRIL, 18));
        freeAgentAgeInfo2.setAgeInYears(29);
        freeAgentAgeInfo2.setElapsedDaysFromLastBDay(212);

        freeAgents.add(freeAgent);
        freeAgents.add(freeAgent1);
        freeAgents.add(freeAgent2);

        return true;
    }
}
