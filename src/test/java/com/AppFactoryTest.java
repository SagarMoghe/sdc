package com;

import com.Database.IDatabaseFactory;
import com.DatabaseTest.DatabaseFactoryTest;
import com.IO.IIOFactory;
import com.IO.IOFactory;
import com.IceHockeyLeague.LeagueFileHandler.ILeagueFileHandlerFactory;
import com.IceHockeyLeague.LeagueFileHandler.LeagueFileHandlerFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import com.IceHockeyLeague.StateMachine.StateMachineFactory;
import com.IceHockeyLeagueTest.LeagueManagerTest.LeagueManagerFactoryTest;

public class AppFactoryTest extends AbstractAppFactory {

    @Override
    public ILeagueFileHandlerFactory createLeagueFileHandlerFactory() {
        return new LeagueFileHandlerFactory();
    }

    @Override
    public IDatabaseFactory createDatabaseFactory() {
        return new DatabaseFactoryTest();
    }

    @Override
    public IIOFactory createIOFactory() {
        return new IOFactory();
    }

    @Override
    public ILeagueManagerFactory createLeagueManagerFactory() {
        return new LeagueManagerFactoryTest();
    }

    @Override
    public IStateMachineFactory createStateMachineFactory() {
        IIOFactory ioFactory = createIOFactory();
        ILeagueFileHandlerFactory leagueFileHandlerFactory = createLeagueFileHandlerFactory();
        return new StateMachineFactory(
                ioFactory.createCommandLineInput(),
                ioFactory.createCommandLineOutput(),
                leagueFileHandlerFactory.createLeagueFileReader(),
                leagueFileHandlerFactory.createJsonParser(),
                leagueFileHandlerFactory.createLeagueFileValidator());
    }

    public static AbstractAppFactory createAppFactoryTest() {
        return new AppFactoryTest();
    }

}
