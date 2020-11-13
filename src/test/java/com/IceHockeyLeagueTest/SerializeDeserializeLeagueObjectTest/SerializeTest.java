package com.IceHockeyLeagueTest.SerializeDeserializeLeagueObjectTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.SerializeDeserializeLeagueObject.ISerialize;
import com.IceHockeyLeague.SerializeDeserializeLeagueObject.ISerializeDeserializeLeagueObjectFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.*;

public class SerializeTest {
    private static ILeagueManagerFactory leagueManagerFactory;
    private static ISerializeDeserializeLeagueObjectFactory leagueSerializationFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setLeagueStandingsFactory(appFactory.createLeagueStandingsFactory());
        AbstractAppFactory.setLeagueSchedulerFactory(appFactory.createLeagueSchedulerFactory());
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
        leagueSerializationFactory = appFactory.createSerializeDeserializeLeagueObjectFactory();
    }

    @Test
    public void SerializeLeagueObjectTest() {
        ILeague league = leagueManagerFactory.createLeague();
        league.setLeagueID(13);
        league.setLeagueName("Dalhousie Hockey League");
        ISerialize serialize = leagueSerializationFactory.createSerialize();
        String path = serialize.serializeLeagueObject(league);
        File file = new File(path);
        Assert.assertTrue(file.exists());
    }
}