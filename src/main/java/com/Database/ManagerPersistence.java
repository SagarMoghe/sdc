package com.Database;

import com.IceHockeyLeague.LeagueManager.Manager.IManager;
import com.IceHockeyLeague.LeagueManager.Manager.IManagerPersistence;
import com.IceHockeyLeague.LeagueManager.Manager.Manager;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.sql.*;

public class ManagerPersistence implements IManagerPersistence {
    @Override
    public boolean saveTeamManager(IManager manager) {
        DBConnection connectionManager = null;
        Connection connection = null;
        CallableStatement myCall;
        try {

            connectionManager = AbstractDatabaseFactory.getFactory().getDBConnection();
            connection = connectionManager.getConnection();
            myCall = connection.prepareCall("{call insertIntoManager(?,?,?,?)}");
            myCall.setInt(1, manager.getTeamID());
            return saveBaseManager(manager, myCall);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in insert team Manager");
            return false;
        } finally {
            if (connection != null) {
                connectionManager.terminateConnection();
            }
        }
    }

    private boolean saveBaseManager(IManager manager, CallableStatement myCall) throws SQLException {
        String managerID = "";
        myCall.setInt(2, manager.getLeagueID());
        myCall.setString(3, manager.getManagerName());
        myCall.registerOutParameter(4, Types.INTEGER);
        ResultSet result = myCall.executeQuery();
        while (result.next()) {
            managerID = result.getString("managerID");
        }
        myCall.close();
        manager.setManagerID(Integer.parseInt(managerID));
        return true;
    }

    @Override
    public boolean saveLeagueManager(IManager manager) {
        DBConnection connectionManager = null;
        Connection connection = null;
        CallableStatement myCall;
        try {

            connectionManager = AbstractDatabaseFactory.getFactory().getDBConnection();
            connection = connectionManager.getConnection();
            myCall = connection.prepareCall("{call insertIntoManager(?,?,?,?)}");
            myCall.setNull(1, Types.INTEGER);
            return saveBaseManager(manager, myCall);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in insert league Manager");
            return false;
        } finally {
            if (connection != null) {
                connectionManager.terminateConnection();
            }
        }
    }

    @Override
    public boolean loadTeamManager(int teamId, IManager manager) {
        DBConnection connectionManager = null;
        Connection connection = null;
        CallableStatement myCall;
        try {

            connectionManager = AbstractDatabaseFactory.getFactory().getDBConnection();
            connection = connectionManager.getConnection();

            myCall = connection.prepareCall("{call insertIntoManager(?)}");
            myCall.setInt(1,teamId);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                manager.setManagerID(result.getInt("managerID"));
                manager.setTeamID(result.getInt("teamID"));
                manager.setLeagueID(result.getInt("leagueID"));
                manager.setManagerName(result.getString("name"));
            }
            myCall.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in loading Manager");
            return false;
        } finally {
            if (connection != null) {
                connectionManager.terminateConnection();
            }
        }
    }

    @Override
    public boolean loadLeagueManagers(int leagueId, List<IManager> managers) {
        DBConnection connectionManager = null;
        Connection connection = null;
        CallableStatement myCall;
        try {

            connectionManager = AbstractDatabaseFactory.getFactory().getDBConnection();
            connection = connectionManager.getConnection();

            myCall = connection.prepareCall("{call insertIntoManager(?)}");
            myCall.setInt(1,leagueId);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                IManager manager = new Manager();
                manager.setManagerID(result.getInt("managerID"));
                manager.setTeamID(result.getInt("teamID"));
                manager.setLeagueID(result.getInt("leagueID"));
                manager.setManagerName(result.getString("name"));
                managers.add(manager);
            }
            myCall.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in loading Manager");
            return false;
        } finally {
            if (connection != null) {
                connectionManager.terminateConnection();
            }
        }
    }
}
