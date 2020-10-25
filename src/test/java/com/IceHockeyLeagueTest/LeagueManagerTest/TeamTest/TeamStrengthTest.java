package com.IceHockeyLeagueTest.LeagueManagerTest.TeamTest;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayerPersistence;
import com.IceHockeyLeague.LeagueManager.Team.ITeamStrength;
import com.IceHockeyLeagueTest.LeagueManagerTest.TestLeagueManagerFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TeamStrengthTest {

    @BeforeClass
    public static void setup() {
        AbstractLeagueManagerFactory.setFactory(new TestLeagueManagerFactory());
    }

    @Test
    public void calculateTest() {
        ITeamPlayerPersistence teamPlayerDB = AbstractLeagueManagerFactory.getFactory().getTeamPlayerDB();
        List<ITeamPlayer> teamPlayers = new ArrayList<>();
        teamPlayerDB.loadTeamPlayers(1, teamPlayers);

        ITeamStrength teamStrength = AbstractLeagueManagerFactory.getFactory().getTeamStrength();
        Assert.assertEquals(46.5, teamStrength.calculate(teamPlayers), 0.0);
    }
}
