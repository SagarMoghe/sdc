package com.IceHockeyLeague.StateMachine.States;

import com.AbstractAppFactory;
import com.IO.IAppOutput;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Player.IRandomChance;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueScheduler.ISchedule;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;

public class SimulateGameState extends AbstractState {

    private final IAppOutput appOutput;
    private final IStateMachineFactory stateMachineFactory;
    private final ILeagueManagerFactory leagueManagerFactory;

    public SimulateGameState(IAppOutput appOutput) {
        this.appOutput = appOutput;
        stateMachineFactory = AbstractAppFactory.getStateMachineFactory();
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
    }

    @Override
    public AbstractState onRun() {
        ILeague league = getLeague();
        IConference winningTeamConference;
        IDivision winningTeamDivision;
        ITeam winningTeam;
        IConference losingTeamConference;
        IDivision losingTeamDivision;
        ITeam losingTeam;

        ISchedule schedule = league.getScheduleSystem().getScheduledMatchOnThisDate(league.getLeagueDate());
        ITeam teamA = schedule.getFirstTeam();
        ITeam teamB = schedule.getSecondTeam();

        appOutput.display("--------------------------------------------------------");
        appOutput.display("Game: " + teamA.getTeamName() + " vs " + teamB.getTeamName());

        float teamAStrength = teamA.getTeamStrength();
        float teamBStrength = teamB.getTeamStrength();

        if (teamAStrength > teamBStrength) {
            winningTeam = teamA;
            losingTeam = teamB;
        }
        else {
            winningTeam = teamB;
            losingTeam = teamA;
        }

        boolean flipResult = false;
        IRandomChance randomChance = leagueManagerFactory.createRandomChance();
        float randomUpsetValue = randomChance.getRandomFloatNumber(0, 1);

        if (randomUpsetValue < league.getGamePlayConfig().getGameResolverConfig().getRandomWinChance()) {
            flipResult = true;
        }

        if (flipResult) {
            if (winningTeam == teamA) {
                winningTeam = teamB;
                losingTeam = teamA;
            }
            else {
                winningTeam = teamA;
                losingTeam = teamB;
            }
        }

        if (winningTeam == teamA) {
            winningTeamConference = schedule.getFirstConference();
            winningTeamDivision = schedule.getFirstDivision();
            losingTeamConference = schedule.getSecondConference();
            losingTeamDivision = schedule.getSecondDivision();
        }
        else {
            winningTeamConference = schedule.getSecondConference();
            winningTeamDivision = schedule.getSecondDivision();
            losingTeamConference = schedule.getFirstConference();
            losingTeamDivision = schedule.getFirstDivision();
        }

        schedule.setWinningTeam(winningTeam);
        league.getScheduleSystem().updateScheduleAfterGamePlayed(schedule);
        league.getStandingSystem().updateStatsForWinningTeam(winningTeamConference, winningTeamDivision, winningTeam);
        league.getStandingSystem().updateStatsForLosingTeam(losingTeamConference, losingTeamDivision, losingTeam);
        winningTeam.decrementLossPointValue();
        losingTeam.incrementLossPointValue();

        appOutput.display("Winner: " + winningTeam.getTeamName());

        return stateMachineFactory.createInjuryCheckState(teamA, teamB);
    }
}
