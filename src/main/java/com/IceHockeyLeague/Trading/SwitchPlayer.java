package com.IceHockeyLeague.Trading;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;

public class SwitchPlayer implements ISwitchPlayer {
    private final ILeagueManagerFactory leagueManagerFactory;

    public SwitchPlayer() {
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
    }

    @Override
    public IFreeAgent teamToFreeTrade(ITeamPlayer player, int leagueID) {
        IFreeAgent agent = leagueManagerFactory.createFreeAgent();
        agent.setLeagueId(leagueID);
        agent.setPlayerName(player.getPlayerName());
        agent.setInjuredStatus(player.getInjuredStatus());
        agent.setRetiredStatus(player.getRetiredStatus());
        agent.setPlayerStats(player.getPlayerStats());
        agent.setPlayerAgeInfo(player.getPlayerAgeInfo());
        return agent;
    }

    @Override
    public ITeamPlayer freeToTeamTrade(IFreeAgent freeAgent, int teamId) {
        ITeamPlayer player = leagueManagerFactory.createTeamPlayer();
        player.setTeamId(teamId);
        player.setIsCaptain(false);
        player.setPlayerName(freeAgent.getPlayerName());
        player.setInjuredStatus(freeAgent.getInjuredStatus());
        player.setRetiredStatus(freeAgent.getRetiredStatus());
        player.setPlayerStats(freeAgent.getPlayerStats());
        player.setPlayerAgeInfo(freeAgent.getPlayerAgeInfo());
        return player;
    }
}
