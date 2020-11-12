package com.IceHockeyLeagueTest.StateMachineTest.StatesTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.League;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import com.IceHockeyLeague.StateMachine.States.AbstractState;
import com.IceHockeyLeague.StateMachine.States.TrainingState;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;

public class AdvanceTimeStateTest {
    private static IStateMachineFactory stateMachineFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory appFactory = AppFactoryTest.createAppFactoryTest();
        stateMachineFactory = appFactory.createStateMachineFactory();
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        AbstractAppFactory.setStateMachineFactory(stateMachineFactory);
    }

    @Test
    public void onRunTest() {
        ILeague league = new League();
        league.setLeagueDate(LocalDate.now());
        league.getScheduleSystem().setRegularSeasonEndDate(LocalDate.now().plusDays(2));

        AbstractState advanceTimeState = stateMachineFactory.createAdvanceTimeState();
        advanceTimeState.setLeague(league);

        Assert.assertTrue(advanceTimeState.onRun() instanceof TrainingState);
    }
}
