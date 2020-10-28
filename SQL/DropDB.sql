USE CSCI5308_6_DEVINT;

DROP TABLE manager;
DROP TABLE coach;
DROP TABLE player;
DROP TABLE freeagent;
DROP TABLE team;
DROP TABLE division;
DROP TABLE conference;
DROP TABLE gamePlayConfig;
DROP TABLE league;

DROP PROCEDURE insertIntoLeague;
DROP PROCEDURE insertIntoConference;
DROP PROCEDURE insertIntoDivision;
DROP PROCEDURE insertIntoTeam;
DROP PROCEDURE insertIntoFreeAgent;
DROP PROCEDURE insertIntoPlayer;
DROP PROCEDURE insertIntoCoach;
DROP PROCEDURE insertIntoManager;
DROP PROCEDURE insertIntoGamePlayConfig;
DROP PROCEDURE checkIfLeagueNameExists;
DROP PROCEDURE loadLeague;
DROP PROCEDURE loadConferences;
DROP PROCEDURE loadDivisions;
DROP PROCEDURE loadTeams;
DROP PROCEDURE loadTeamPlayers;
DROP PROCEDURE loadTeamCoach;
DROP PROCEDURE loadTeamManager;
DROP PROCEDURE loadFreeAgents;
DROP PROCEDURE loadLeagueCoaches;
DROP PROCEDURE loadLeagueManagers;
DROP PROCEDURE loadLeagueGamePlayConfig;
DROP PROCEDURE teamToFreeTrade;
DROP PROCEDURE freeToTeamTrade;
DROP PROCEDURE interTeamTrade;