package com.IceHockeyLeagueTest.LeagueManagerTest.DivisionTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.PersistenceTest.DivisionPersistenceMock;
import com.PersistenceTest.PersistenceFactoryTest;
import com.PersistenceTest.TeamPersistenceMock;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DivisionTest {
    private static ILeagueManagerFactory leagueManagerFactory;
    private static PersistenceFactoryTest persistenceFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
        persistenceFactory = AppFactoryTest.createPersistenceFactoryTest();
    }
    @Test
    public void ConstructorTest() {
        IDivision division = leagueManagerFactory.createDivision();
        Assert.assertEquals(-1, division.getDivisionID());
        Assert.assertEquals(-1, division.getConferenceID());
    }

    @Test
    public void getDivisionIDTest() {
        IDivision division = leagueManagerFactory.createDivision();
        division.setDivisionID(11);
        Assert.assertEquals(11, division.getDivisionID());
    }

    @Test
    public void setDivisionIDTest() {
        IDivision division = leagueManagerFactory.createDivision();
        division.setDivisionID(3);
        Assert.assertEquals(3, division.getDivisionID());
    }

    @Test
    public void getDivisionNameTest() {
        IDivision division = leagueManagerFactory.createDivision();
        division.setDivisionName("Atlantic");
        Assert.assertEquals("Atlantic", division.getDivisionName());
    }

    @Test
    public void setDivisionNameTest() {
        IDivision division = leagueManagerFactory.createDivision();
        division.setDivisionName("Pacific");
        Assert.assertEquals("Pacific", division.getDivisionName());
    }

    @Test
    public void getConferenceIDTest() {
        IDivision division = leagueManagerFactory.createDivision();
        division.setConferenceID(11);
        Assert.assertEquals(11, division.getConferenceID());
    }

    @Test
    public void setConferenceIDTest() {
        IDivision division = leagueManagerFactory.createDivision();
        division.setConferenceID(3);
        Assert.assertEquals(3, division.getConferenceID());
    }

    @Test
    public void getTeamByIdTest() {
        IDivision division = leagueManagerFactory.createDivision();
        Assert.assertNull(division.getTeamById(1));
    }

    @Test
    public void addTeamTest() {
        IDivision division = leagueManagerFactory.createDivision();
        ITeam team = leagueManagerFactory.createTeam();
        team.setTeamName("Halifax");

        division.addTeam(team);
        List<ITeam> divisionTeams = division.getTeams();
        Assert.assertEquals(1, divisionTeams.size());
    }

    @Test
    public void setTeamsTest() {
        IDivision division = leagueManagerFactory.createDivision();
        List<ITeam> teams = new ArrayList<>();
        TeamPersistenceMock teamPersistenceMock = persistenceFactory.createTeamPersistence();
        teamPersistenceMock.loadTeams(1,teams);
        division.setTeams(teams);
        division.setTeams(teams);
        List<ITeam> divisionTeams = division.getTeams();
        Assert.assertEquals(1, divisionTeams.size());
    }

    @Test
    public void isNullOrEmptyTest() {
        IDivision division = leagueManagerFactory.createDivision();
        Assert.assertTrue(division.isNullOrEmpty(""));
        Assert.assertFalse(division.isNullOrEmpty("central"));
    }

    @Test
    public void isDivisionNameExistTest() {
        IDivision division = leagueManagerFactory.createDivision();
        DivisionPersistenceMock divisionPersistenceMock = persistenceFactory.createDivisionPersistence();
        List<IDivision> divisions = new ArrayList<>();
        divisionPersistenceMock.loadDivisions(1, divisions);
        Assert.assertFalse(division.isDivisionNameExist(divisions, "central"));
        Assert.assertTrue(division.isDivisionNameExist(divisions, "atlantic"));
    }

}
