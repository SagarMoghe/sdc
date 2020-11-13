package com;

import com.IO.IIOFactory;
import com.Database.IDatabaseFactory;
import com.IceHockeyLeague.LeagueFileHandler.ILeagueFileHandlerFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueScheduler.ILeagueSchedulerFactory;
import com.IceHockeyLeague.LeagueStandings.ILeagueStandingsFactory;
import com.IceHockeyLeague.SerializeDeserializeLeagueObject.ISerializeDeserializeLeagueObjectFactory;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import com.Trading.ITradingFactory;

public abstract class AbstractAppFactory {

    private static AbstractAppFactory appFactory;
    private static ILeagueFileHandlerFactory leagueFileHandlerFactory;
    private static IDatabaseFactory databaseFactory;
    private static IIOFactory ioFactory;
    private static ILeagueManagerFactory leagueManagerFactory;
    private static ILeagueSchedulerFactory leagueSchedulerFactory;
    private static ILeagueStandingsFactory leagueStandingsFactory;
    private static ISerializeDeserializeLeagueObjectFactory leagueSerializationFactory;
    private static ITradingFactory tradingFactory;
    private static IStateMachineFactory stateMachineFactory;

    public abstract ILeagueFileHandlerFactory createLeagueFileHandlerFactory();
    public abstract IDatabaseFactory createDatabaseFactory();
    public abstract IIOFactory createIOFactory();
    public abstract ILeagueManagerFactory createLeagueManagerFactory();
    public abstract ILeagueSchedulerFactory createLeagueSchedulerFactory();
    public abstract ILeagueStandingsFactory createLeagueStandingsFactory();
    public abstract ISerializeDeserializeLeagueObjectFactory createSerializeDeserializeLeagueObjectFactory();
    public abstract ITradingFactory createTradingFactory();
    public abstract IStateMachineFactory createStateMachineFactory();

    public static AbstractAppFactory getAppFactory() {
        return appFactory;
    }

    public static void setAppFactory(AbstractAppFactory appFactory) {
        AbstractAppFactory.appFactory = appFactory;
    }

    public static ILeagueFileHandlerFactory getLeagueFileHandlerFactory() {
        return leagueFileHandlerFactory;
    }

    public static void setLeagueFileHandlerFactory(ILeagueFileHandlerFactory leagueFileHandlerFactory) {
        AbstractAppFactory.leagueFileHandlerFactory = leagueFileHandlerFactory;
    }

    public static IDatabaseFactory getDatabaseFactory() {
        return databaseFactory;
    }

    public static void setDatabaseFactory(IDatabaseFactory databaseFactory) {
        AbstractAppFactory.databaseFactory = databaseFactory;
    }

    public static IIOFactory getIOFactory() {
        return ioFactory;
    }

    public static void setIOFactory(IIOFactory ioFactory) {
        AbstractAppFactory.ioFactory = ioFactory;
    }

    public static ILeagueManagerFactory getLeagueManagerFactory() {
        return leagueManagerFactory;
    }

    public static void setLeagueManagerFactory(ILeagueManagerFactory leagueManagerFactory) {
        AbstractAppFactory.leagueManagerFactory = leagueManagerFactory;
    }

    public static ILeagueSchedulerFactory getLeagueSchedulerFactory() {
        return leagueSchedulerFactory;
    }

    public static void setLeagueSchedulerFactory(ILeagueSchedulerFactory leagueSchedulerFactory) {
        AbstractAppFactory.leagueSchedulerFactory = leagueSchedulerFactory;
    }

    public static ILeagueStandingsFactory getLeagueStandingsFactory() {
        return leagueStandingsFactory;
    }

    public static void setLeagueStandingsFactory(ILeagueStandingsFactory leagueStandingsFactory) {
        AbstractAppFactory.leagueStandingsFactory = leagueStandingsFactory;
    }

    public static ISerializeDeserializeLeagueObjectFactory getSerializeDeserializeLeagueObjectFactory() {
        return leagueSerializationFactory;
    }

    public static void setSerializeDeserializeLeagueObjectFactory(ISerializeDeserializeLeagueObjectFactory leagueSerializationFactory) {
        AbstractAppFactory.leagueSerializationFactory = leagueSerializationFactory;
    }

    public static ITradingFactory getTradingFactory() {
        return tradingFactory;
    }

    public static void setTradingFactory(ITradingFactory tradingFactory) {
        AbstractAppFactory.tradingFactory = tradingFactory;
    }

    public static IStateMachineFactory getStateMachineFactory() {
        return stateMachineFactory;
    }

    public static void setStateMachineFactory(IStateMachineFactory stateMachineFactory) {
        AbstractAppFactory.stateMachineFactory = stateMachineFactory;
    }

}
