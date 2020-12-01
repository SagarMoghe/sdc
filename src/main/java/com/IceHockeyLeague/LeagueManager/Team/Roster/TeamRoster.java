package com.IceHockeyLeague.LeagueManager.Team.Roster;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerStats;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Player.PlayerPosition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TeamRoster implements ITeamRoster {
    private final Logger LOGGER = LogManager.getLogger(TeamRoster.class);
    private final ILeagueManagerFactory leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
    private List<ITeamPlayer> players;
    private List<IFreeAgent> agents;
    private int numberOfSkatersNeeded = 26;
    private int numberOfKeepersNeeded = 4;
    private final String goalie = PlayerPosition.GOALIE.toString();
    private final String forward = PlayerPosition.FORWARD.toString();
    private final String defence = PlayerPosition.DEFENSE.toString();
    private final IActiveRoster activeRoster = leagueManagerFactory.createActiveRoster();
    private final IInactiveRoster inactiveRoster = leagueManagerFactory.createInactiveRoster();

    public List<ITeamPlayer> getActiveRoster() {
        return activeRoster.getActivePlayers();
    }

    public List<ITeamPlayer> getInactiveRoster() {
        return inactiveRoster.getInactivePlayers();
    }

    public void setPlayers(List<ITeamPlayer> players) {
        this.players = players;
    }

    @Override
    public List<ITeamPlayer> getPlayers() {
        return players;
    }

    public void setAgents(List<IFreeAgent> agents) {
        this.agents = agents;
    }

    private IFreeAgent getBestAgentWithPosition(String position, List<IFreeAgent> agents) {
        IFreeAgent bestAgent = null;
        float strength = 0;
        for (IFreeAgent agent : agents) {
            if (agent.getInjuredStatus()) {
            } else {
                if (agent.getPlayerStats().getPosition().equalsIgnoreCase(position)) {
                    if (agent.getPlayerStats().getStrength() > strength) {
                        bestAgent = agent;
                        strength = agent.getPlayerStats().getStrength();
                    }
                }
            }

        }
        return bestAgent;
    }

    private ITeamPlayer getWorseTeamPlayerWithPosition(String position, List<ITeamPlayer> players) {
        ITeamPlayer worseTeamPlayer = null;
        float strength = Float.MAX_VALUE;
        for (ITeamPlayer player : players) {
            if (player.getPlayerStats().getPosition().equalsIgnoreCase(position)) {
                if (player.getPlayerStats().getStrength() < strength) {
                    worseTeamPlayer = player;
                    strength = player.getPlayerStats().getStrength();
                }
            }

        }
        return worseTeamPlayer;
    }

    public void validateRoster(List<IFreeAgent> agents) {
        int numberOfSkaters = 0;
        int numberOfKeepers = 0;

        for (ITeamPlayer player : this.players) {
            if (player.getPlayerStats().getPosition().equalsIgnoreCase(goalie)) {
                numberOfKeepers++;
            } else {
                numberOfSkaters++;
            }
        }

        if (numberOfKeepers > numberOfKeepersNeeded) {
            int difference = numberOfKeepers - numberOfKeepersNeeded;
            for (int i = 0; i < difference; i++) {
                ITeamPlayer player = getWorseTeamPlayerWithPosition(goalie, this.players);
                this.players.remove(player);
                IFreeAgent agent = leagueManagerFactory.createFreeAgent();
                IPlayerStats stats = leagueManagerFactory.createPlayerStats();
                agent.setPlayerStats(stats);
                agent = player.convertToFreeAgent(agent);
                agents.add(agent);
            }
        }

        if (numberOfSkaters > numberOfSkatersNeeded) {
            int difference = numberOfSkaters - numberOfSkatersNeeded;
            for (int i = 0; i < difference; i++) {
                ITeamPlayer player1 = getWorseTeamPlayerWithPosition(forward, this.players);
                ITeamPlayer player2 = getWorseTeamPlayerWithPosition(defence, this.players);
                ITeamPlayer player;
                if (player1.getPlayerStats().getStrength() < player2.getPlayerStats().getStrength()) {
                    player = player1;
                } else {
                    player = player2;
                }
                this.players.remove(player);
                IFreeAgent agent = leagueManagerFactory.createFreeAgent();
                IPlayerStats stats = leagueManagerFactory.createPlayerStats();
                agent.setPlayerStats(stats);
                agent = player.convertToFreeAgent(agent);
                agents.add(agent);
            }
        }

        if (numberOfKeepers < numberOfKeepersNeeded) {
            int difference = numberOfKeepersNeeded - numberOfKeepers;
            for (int i = 0; i < difference; i++) {
                IFreeAgent agent = getBestAgentWithPosition(goalie, agents);
                ITeamPlayer player = leagueManagerFactory.createTeamPlayer();
                IPlayerStats stats = leagueManagerFactory.createPlayerStats();
                player.setPlayerStats(stats);
                player = agent.convertToTeamPlayer(player);
                agents.remove(agent);
                this.players.add(player);
            }
        }

        if (numberOfSkaters < numberOfSkatersNeeded) {
            int difference = numberOfSkatersNeeded - numberOfSkaters;
            for (int i = 0; i < difference; i++) {
                IFreeAgent agent1 = getBestAgentWithPosition(forward, agents);
                IFreeAgent agent2 = getBestAgentWithPosition(defence, agents);
                IFreeAgent agent;
                if (agent1.getPlayerStats().getStrength() < agent2.getPlayerStats().getStrength()) {
                    agent = agent1;
                } else {
                    agent = agent2;
                }
                ITeamPlayer player = leagueManagerFactory.createTeamPlayer();
                IPlayerStats stats = leagueManagerFactory.createPlayerStats();
                player.setPlayerStats(stats);
                player = agent.convertToTeamPlayer(player);
                agents.remove(agent);
                this.players.add(player);
            }
        }
        this.setNewRoster(agents);

    }

    private void setNewRoster(List<IFreeAgent> agents) {
        List<ITeamPlayer> skaters = new ArrayList<>();
        List<ITeamPlayer> goalies = new ArrayList<>();

        for (ITeamPlayer player : this.players) {
            if (player.getPlayerStats().getPosition().equalsIgnoreCase(goalie)) {
                goalies.add(player);
            } else {
                skaters.add(player);
            }
        }

        List<ITeamPlayer> bestSkaters = new ArrayList<>();
        List<ITeamPlayer> bestGoalies = new ArrayList<>();

        while (bestSkaters.size() < 18) {
            ITeamPlayer bestSkater = null;
            float strength = 0;
            for (ITeamPlayer player : skaters) {
                if (player.getPlayerStats().getStrength() > strength) {
                    bestSkater = player;
                    strength = player.getPlayerStats().getStrength();
                }
            }
            bestSkaters.add(bestSkater);
            skaters.remove(bestSkater);
        }

        while (bestGoalies.size() < 2) {
            ITeamPlayer bestGoalie = null;
            float strength = 0;
            for (ITeamPlayer player : goalies) {
                if (player.getPlayerStats().getStrength() > strength) {
                    bestGoalie = player;
                    strength = player.getPlayerStats().getStrength();
                }
            }
            bestGoalies.add(bestGoalie);
            goalies.remove(bestGoalie);
        }

        List<ITeamPlayer> activePlayers = new ArrayList<>();
        List<ITeamPlayer> inactivePlayers = new ArrayList<>();
        activePlayers.addAll(bestSkaters);
        activePlayers.addAll(bestGoalies);
        inactivePlayers.addAll(skaters);
        inactivePlayers.addAll(goalies);
        activeRoster.setActivePlayers(activePlayers);
        inactiveRoster.setInactivePlayers(inactivePlayers);
        inactiveRoster.setAgents(agents);
        activeRoster.validateActiveRoster(inactiveRoster);
    }
}
