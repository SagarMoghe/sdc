package com.IceHockeyLeagueTest.StateMachineTest.StatesTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Scheduler.ISchedule;
import com.IceHockeyLeague.LeagueManager.League.ILeaguePersistence;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import com.IceHockeyLeague.StateMachine.States.AbstractState;
import com.IceHockeyLeague.StateMachine.States.AdvanceToNextSeasonState;
import com.IceHockeyLeague.StateMachine.States.PersistState;
import com.IceHockeyLeague.StateMachine.States.TrophyState;
import com.Persistence.ILeaguePersistence;
import com.PersistenceTest.LeaguePersistenceMock;
import com.PersistenceTest.PersistenceFactoryTest;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

public class AgingStateTest {
    private static IStateMachineFactory stateMachineFactory;
    private static ILeagueManagerFactory leagueManagerFactory;
    private static PersistenceFactoryTest persistenceFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory appFactory = AppFactoryTest.createAppFactory();
        AbstractAppFactory.setStateMachineFactory(appFactory.createStateMachineFactory());
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        stateMachineFactory = AbstractAppFactory.getStateMachineFactory();
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        persistenceFactory = AppFactoryTest.createPersistenceFactoryTest();
    }

    @Test
    public void onRunTest() {
        ILeague league = leagueManagerFactory.createLeague();
        ILeaguePersistence leaguePersistence = persistenceFactory.createLeaguePersistence();
        leaguePersistence.loadLeague("");
        league.setLeagueDate(LocalDate.of(2020, Month.NOVEMBER, 17));

        AbstractState agingState = stateMachineFactory.createAgingState();
        agingState.setLeague(league);

        Assert.assertTrue(agingState.onRun() instanceof AdvanceToNextSeasonState);
        Assert.assertEquals(19, league.getConferences().get(0).getDivisions().get(0).getTeams().get(0).getPlayers().get(0).getPlayerAgeInfo().getAgeInYears());
        Assert.assertEquals(333, league.getFreeAgents().get(0).getPlayerAgeInfo().getElapsedDaysFromLastBDate());
    }

    @Test
    public void onRunAlternateTest() {
        ILeague league = leagueManagerFactory.createLeague();
        ILeaguePersistence leagueDB = persistenceFactory.createLeaguePersistence();
        leagueDB.loadLeague("");
        league.setLeagueDate(LocalDate.of(2020, Month.NOVEMBER, 17));
        league.getScheduleSystem().setPlayoffSchedule(null);
        AbstractState agingState = stateMachineFactory.createAgingState();
        agingState.setLeague(league);

        Assert.assertTrue(agingState.onRun() instanceof PersistState);
        Assert.assertEquals(19, league.getConferences().get(0).getDivisions().get(0).getTeams().get(0).getPlayers().get(0).getPlayerAgeInfo().getAgeInYears());
        Assert.assertEquals(333, league.getFreeAgents().get(0).getPlayerAgeInfo().getElapsedDaysFromLastBDate());
    }
}
