package com;


import com.IO.IIOFactory;
import com.IO.IOFactory;
import com.IceHockeyLeague.LeagueFileHandler.ILeagueFileHandlerFactory;
import com.IceHockeyLeague.LeagueFileHandler.LeagueFileHandlerFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.LeagueManagerFactory;
import com.IceHockeyLeague.SerializeDeserializeLeagueObject.ISerializeDeserializeLeagueObjectFactory;
import com.IceHockeyLeague.SerializeDeserializeLeagueObject.SerializeDeserializeLeagueObjectFactory;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import com.IceHockeyLeague.StateMachine.StateMachineFactory;
import com.Persistence.IPersistenceFactory;
import com.Persistence.PersistenceFactory;
import com.TrophySystem.ITrophySystemFactory;
import com.TrophySystem.TrophySystemFactory;
import com.IceHockeyLeague.Trading.ITradingFactory;
import com.IceHockeyLeague.Trading.TradingFactory;

public class AppFactory extends AbstractAppFactory {

    @Override
    public ILeagueFileHandlerFactory createLeagueFileHandlerFactory() {
        return new LeagueFileHandlerFactory();
    }

    @Override
    public IIOFactory createIOFactory() {
        return new IOFactory();
    }

    @Override
    public ILeagueManagerFactory createLeagueManagerFactory() {
        return new LeagueManagerFactory();
    }

    @Override
    public ITrophySystemFactory createTrophySystemFactory() {
        return new TrophySystemFactory();
    }

    @Override
    public ISerializeDeserializeLeagueObjectFactory createSerializeDeserializeLeagueObjectFactory() {
        return new SerializeDeserializeLeagueObjectFactory();
    }

    @Override
    public ITradingFactory createTradingFactory() {
        return new TradingFactory();
    }

    @Override
    public IStateMachineFactory createStateMachineFactory() {
        IIOFactory ioFactory = AbstractAppFactory.getIOFactory();
        ILeagueFileHandlerFactory leagueFileHandlerFactory = AbstractAppFactory.getLeagueFileHandlerFactory();
        return new StateMachineFactory(
                ioFactory.createCommandLineInput(),
                ioFactory.createCommandLineOutput(),
                leagueFileHandlerFactory.createLeagueFileReader(),
                leagueFileHandlerFactory.createJsonParser(),
                leagueFileHandlerFactory.createLeagueFileValidator());
    }

    @Override
    public IPersistenceFactory createPersistenceFactory() {
        return new PersistenceFactory();
    }

    public static AbstractAppFactory createAppFactory() {
        return new AppFactory();
    }

}
