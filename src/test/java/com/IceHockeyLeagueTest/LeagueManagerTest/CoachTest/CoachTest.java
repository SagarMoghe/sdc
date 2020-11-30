package com.IceHockeyLeagueTest.LeagueManagerTest.CoachTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.Coach.ICoachStats;
import com.PersistenceTest.CoachPersistenceMock;
import com.PersistenceTest.PersistenceFactoryTest;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

public class CoachTest {
    private static ILeagueManagerFactory leagueManagerFactory;
    private static PersistenceFactoryTest persistenceFactory;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
        persistenceFactory = AppFactoryTest.createPersistenceFactoryTest();
    }

    @Test
    public void ConstructorTest() {
        ICoach coach = leagueManagerFactory.createCoach();
        Assert.assertEquals(-1, coach.getTeamId());
        Assert.assertEquals(-1, coach.getLeagueId());
        Assert.assertEquals(-1, coach.getCoachId());
    }

    @Test
    public void getCoachIdTest() {
        ICoach coach = leagueManagerFactory.createCoach();
        coach.setCoachId(3);
        Assert.assertEquals(3, coach.getCoachId());
    }

    @Test
    public void setCoachIdTest() {
        ICoach coach = leagueManagerFactory.createCoach();
        coach.setCoachId(22);
        Assert.assertEquals(22, coach.getCoachId());
    }

    @Test
    public void getCoachNameTest() {
        ICoach coach = leagueManagerFactory.createCoach();
        coach.setCoachName("Bob M");
        Assert.assertEquals("Bob M", coach.getCoachName());
    }

    @Test
    public void setCoachNameTest() {
        ICoach coach = leagueManagerFactory.createCoach();
        coach.setCoachName("Michael");
        Assert.assertEquals("Michael", coach.getCoachName());
    }

    @Test
    public void getTeamIdTest() {
        ICoach coach = leagueManagerFactory.createCoach();
        coach.setTeamId(3);
        Assert.assertEquals(3, coach.getTeamId());
    }

    @Test
    public void setTeamIdTest() {
        ICoach coach = leagueManagerFactory.createCoach();
        coach.setTeamId(22);
        Assert.assertEquals(22, coach.getTeamId());
    }

    @Test
    public void getLeagueIdTest() {
        ICoach coach = leagueManagerFactory.createCoach();
        coach.setLeagueId(3);
        Assert.assertEquals(3, coach.getLeagueId());
    }

    @Test
    public void setLeagueIdTest() {
        ICoach coach = leagueManagerFactory.createCoach();
        coach.setLeagueId(22);
        Assert.assertEquals(22, coach.getLeagueId());
    }

    @Test
    public void setCoachStatsTest() {
        ICoach coach = leagueManagerFactory.createCoach();
        ICoachStats stats = leagueManagerFactory.createCoachStats();
        ICoachStats coachStats;
        stats.setSaving(0.7f);
        stats.setChecking(0.5f);
        stats.setShooting(0.3f);
        stats.setSkating(0.8f);
        coach.setCoachStats(stats);

        coachStats = coach.getCoachStats();
        Assert.assertEquals(0.7f, coachStats.getSaving(), 0.0);
    }

    @Test
    public void setCoachStatsInvalidTest() {
        thrown.expect(IllegalArgumentException.class);
        ICoach coach = leagueManagerFactory.createCoach();
        coach.setCoachStats(null);
    }

    @Test
    public void isValidTrueTest() {
        ICoach coach = leagueManagerFactory.createCoach();
        coach.setCoachName("Raj K");
        Assert.assertTrue(coach.isValid());
    }

    @Test
    public void isValidFalseTest() {
        ICoach coach = leagueManagerFactory.createCoach();
        coach.setCoachName("");
        Assert.assertFalse(coach.isValid());
    }

    @Test
    public void isCoachNameExistTest() {
        CoachPersistenceMock coachPersistenceMock = persistenceFactory.createCoachPersistence();
        ICoach coach = leagueManagerFactory.createCoach();
        List<ICoach> coaches = new ArrayList<>();
        coachPersistenceMock.loadLeagueCoaches(1, coaches);
        Assert.assertTrue(coach.isCoachNameExist(coaches, "joe doe"));
    }

    @Test
    public void isCoachNameExistFalseTest() {
        CoachPersistenceMock coachPersistenceMock = persistenceFactory.createCoachPersistence();
        ICoach coach = leagueManagerFactory.createCoach();
        List<ICoach> coaches = new ArrayList<>();
        coachPersistenceMock.loadLeagueCoaches(1, coaches);
        Assert.assertFalse(coach.isCoachNameExist(coaches, ""));
        Assert.assertTrue(coach.isCoachNameExist(coaches, "joe doe"));
    }
}
