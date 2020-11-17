DELIMITER $$

-- START: Save League data models --

CREATE PROCEDURE
    insertIntoLeague(
        IN leagueName VARCHAR(45),
        IN leagueDate DATE,
        OUT leagueID INT
    )
BEGIN
    INSERT INTO
        league
    VALUES
        (NULL,
         leagueName,
         leagueDate);

    SET leagueID = last_insert_id();
    SELECT leagueID;
END $$

CREATE PROCEDURE
    insertIntoConference(
        IN leagueID INT,
        IN conferenceName VARCHAR(45),
        OUT conferenceID INT
    )
BEGIN
    INSERT INTO
        conference
    VALUES
        (NULL,
         leagueID,
         conferenceName);

    SET conferenceID = last_insert_id();
    SELECT conferenceID;
END $$

CREATE PROCEDURE
    insertIntoDivision(
        IN conferenceID INT,
        IN divisionName VARCHAR(45),
        OUT divisionID INT
    )
BEGIN
    INSERT
    INTO
        division
    VALUES
        (NULL,
         conferenceID,
         divisionName);

    SET divisionID = last_insert_id();
    SELECT divisionID;
END $$

CREATE PROCEDURE
    insertIntoTeam(
        IN divisionID INT,
        IN teamName VARCHAR(45),
        IN isUserCreated TINYINT(1),
        IN strength FLOAT(1),
        OUT teamID INT
    )
BEGIN
    INSERT INTO
        team
    VALUES (
        NULL,
        divisionID,
        teamName,
        isUserCreated,
        strength);

    SET teamID = last_insert_id();
    SELECT teamID;
END $$

CREATE PROCEDURE
    insertIntoFreeAgent(
        IN leagueID INT,
        IN playerName VARCHAR(45),
        IN birthDate DATE,
        IN age INT,
        IN elapsedDaysFromLastBDay INT,
        IN isInjured TINYINT(1),
        IN daysInjured INT,
        IN injuryDate DATE,
        IN isRetired TINYINT(1),
        IN retirementDate DATE,
        IN pos VARCHAR(45),
        IN skating INT,
        IN shooting INT,
        IN checking INT,
        IN saving INT,
        IN strength FLOAT(1),
        OUT freeAgentID INT
    )
BEGIN
    INSERT INTO freeagent
    VALUES
        (NULL,
         leagueID,
         playerName,
         birthDate,
         age,
         elapsedDaysFromLastBDay,
         isInjured,
         daysInjured,
         injuryDate,
         isRetired,
         retirementDate,
         pos,
         skating,
         shooting,
         checking,
         saving,
         strength);

    SET freeAgentID = last_insert_id();
    SELECT freeAgentID;
END $$

CREATE PROCEDURE
    insertIntoPlayer(
        IN teamID INT,
        IN playerName VARCHAR(45),
        IN captain TINYINT(1),
        IN birthDate DATE,
        IN age INT,
        IN elapsedDaysFromLastBDay INT,
        IN isInjured TINYINT(1),
        IN daysInjured INT,
        IN injuryDate DATE,
        IN isRetired TINYINT(1),
        IN retirementDate DATE,
        IN pos VARCHAR(45),
        IN skating INT,
        IN shooting INT,
        IN checking INT,
        IN saving INT,
        IN strength FLOAT(1),
        OUT playerID INT
    )
BEGIN
    INSERT INTO player
    VALUES
        (NULL,
         teamID,
         playerName,
         captain,
         birthDate,
         age,
         elapsedDaysFromLastBDay,
         isInjured,
         daysInjured,
         injuryDate,
         isRetired,
         retirementDate,
         pos,
         skating,
         shooting,
         checking,
         saving,
         strength);

    SET playerID = last_insert_id();
    SELECT playerID;
END $$

CREATE PROCEDURE
    insertIntoCoach(
        IN teamID INT,
        IN leagueID INT,
        IN coachName VARCHAR(45),
        IN skating FLOAT(1),
        IN shooting FLOAT(1),
        IN checking FLOAT(1),
        IN saving FLOAT(1),
        OUT coachID INT
    )
BEGIN
    INSERT INTO
        coach
    VALUES (
        NULL,
        teamID,
        leagueID,
        coachName,
        skating,
        shooting,
        checking,
        saving);

    SET coachID = last_insert_id();
    SELECT coachID;
END $$

CREATE PROCEDURE
    insertIntoManager(
        IN teamID INT,
        IN leagueID INT,
        IN managerName VARCHAR(45),
        OUT managerID INT
    )
BEGIN
    INSERT INTO
        manager
    VALUES
        (NULL,
         teamID,
         leagueID,
         managerName);

    SET managerID = last_insert_id();
    SELECT managerID;
END $$

CREATE PROCEDURE
    insertIntoGamePlayConfig(
        IN leagueID INT,
        IN averageRetirementAge INT,
        IN maximumAge INT,
        IN statDecayChance FLOAT(2),
        IN randomWinChance FLOAT(1),
        IN randomInjuryChance FLOAT(2),
        IN injuryDaysLow INT,
        IN injuryDaysHigh INT,
        IN daysUntilStatIncreaseCheck INT,
        IN lossPoint INT,
        IN randomTradeOfferChance FLOAT(2),
        IN maxPlayersPerTrade INT,
        IN randomAcceptanceChance FLOAT(2),
        OUT gamePlayConfigID INT
    )
BEGIN
    INSERT
    INTO
        gamePlayConfig
    VALUES
        (NULL,
         leagueID,
         averageRetirementAge,
         maximumAge,
         statDecayChance,
         randomWinChance,
         randomInjuryChance,
         injuryDaysLow,
         injuryDaysHigh,
         daysUntilStatIncreaseCheck,
         lossPoint,
         randomTradeOfferChance,
         maxPlayersPerTrade,
         randomAcceptanceChance
        );

    SET gamePlayConfigID = last_insert_id();
    SELECT gamePlayConfigID;
END $$

-- END: Save League data models --

-- START: Load League data models --

CREATE PROCEDURE
    checkIfLeagueNameExists(
        IN leagueName VARCHAR(200)
    )
BEGIN
    SELECT *
    FROM league
    WHERE LOWER(league.name) = LOWER(leagueName);
END $$

CREATE PROCEDURE
    checkIfTeamNameExists(
        IN teamName VARCHAR(200)
    )
BEGIN
    SELECT * FROM league WHERE leagueID IN
        (SELECT leagueID FROM conference WHERE conferenceID IN
            (SELECT conferenceID FROM division WHERE divisionID IN
                (SELECT divisionID FROM team WHERE LOWER(team.teamName) = LOWER(teamName) AND isUserCreated = 1)))
                    ORDER BY leagueID;
END $$

CREATE PROCEDURE
    loadLeague(
        IN ID INT
    )
BEGIN
    SELECT *
    FROM league
    WHERE leagueID = ID;
END $$

CREATE PROCEDURE
    loadConferences(
        IN ID int
    )
BEGIN
    SELECT *
    FROM conference
    WHERE leagueID = ID;
END $$

CREATE PROCEDURE
    loadDivisions(
        IN ID INT
    )
BEGIN
    SELECT *
    FROM division
    WHERE conferenceID = ID;
END $$

CREATE PROCEDURE
    loadTeams(
        IN ID INT
    )
BEGIN
    SELECT *
    FROM team
    WHERE divisionID = ID;
END $$

CREATE PROCEDURE
    loadTeamPlayers(
     IN ID INT
    )
BEGIN
    SELECT *
    FROM player
    WHERE teamID = ID AND isRetired = 0;
END $$

CREATE PROCEDURE
    loadTeamCoach(
        IN ID INT
    )
BEGIN
    SELECT *
    FROM coach
    WHERE teamID = ID;
END $$

CREATE PROCEDURE
    loadTeamManager(
        IN ID INT
    )
BEGIN
    SELECT *
    FROM manager
    WHERE teamID = ID;
END $$

CREATE PROCEDURE
    loadFreeAgents(
        IN ID INT
    )
BEGIN
    SELECT *
    FROM freeagent
    WHERE leagueID = ID AND isRetired = 0;
END $$

CREATE PROCEDURE
    loadLeagueCoaches(
        IN ID INT
    )
BEGIN
    SELECT *
    FROM coach
    WHERE leagueID = ID
      AND teamID IS NULL;
END $$

CREATE PROCEDURE
    loadLeagueManagers(
        IN ID INT
    )
BEGIN
    SELECT *
    FROM manager
    WHERE leagueID = ID
      AND teamID IS NULL;
END $$

CREATE PROCEDURE
    loadLeagueGamePlayConfig(
        IN ID INT
    )
BEGIN
    SELECT *
    FROM gamePlayConfig
    WHERE leagueID = ID;
END $$

-- END: Load League data models --

CREATE PROCEDURE
    teamToFreeTrade(
        IN idPlayer INT,
        IN idLeague INT,
        OUT status VARCHAR(100),
        OUT lastID INT
    )
BEGIN
    SET IdPlayer = idPlayer;
    SET IdLeague = idLeague;

    START TRANSACTION;
    SET SQL_SAFE_UPDATES = 0;

    INSERT INTO
        freeagent
            (name,
             position,
             age,
             skating,
             shooting,
             checking,
             saving,
             strength,
             isInjured,
             leagueID)
    SELECT
             name,
             position,
             age,
             skating,
             shooting,
             checking,
             saving,
             strength,
             isInjured,
             IdLeague
    FROM
        player
    WHERE playerID = IdPlayer;

    SET lastID = last_insert_id();

    DELETE FROM player WHERE playerID = IdPlayer;

    SET SQL_SAFE_UPDATES = 1;
    commit;
    SET status = "Player transfered to freeAgent list successfully";

    SELECT status;
    SELECT lastID;
END $$

CREATE PROCEDURE
    freeToTeamTrade(
        IN idFree INT,
        IN idTeam INT,
        IN isCaptain TINYINT(1),
        OUT status VARCHAR(100),
        OUT lastID INT
    )
BEGIN
    SET IDFree = idFree;
    SET IDTeam = idTeam;
    SET Iscaptain = isCaptain;

    START TRANSACTION;

    INSERT INTO
        player
            (teamID,
             name,
             position,
             age,
             skating,
             shooting,
             checking,
             saving,
             captain,
             strength,
             isInjured)
    SELECT
            IDTeam,
            name,
            position,
            age,
            skating,
            shooting,
            checking,
            saving,
            Iscaptain,
            strength,
            isInjured
    FROM
        freeagent
    WHERE freeAgentID = IDFree;

    SET lastID = last_insert_id();

    SET SQL_SAFE_UPDATES = 0;
    delete FROM freeagent WHERE freeAgentID = IDFree;
    SET SQL_SAFE_UPDATES = 1;
    commit;
    SET status = "freeAgent transfered to Player list successfully";

    SELECT status;
    SELECT lastID;

END $$

CREATE PROCEDURE
    interTeamTrade(
        IN toTeamID int,
        IN IdPlayer int,
        OUT status VARCHAR(45)
    )
BEGIN
    SET IDplayer = IdPlayer;
    START TRANSACTION;
    SET SQL_SAFE_UPDATES = 0;
    update player SET teamID=toTeamID WHERE playerID = IDplayer;
    SET SQL_SAFE_UPDATES = 1;
    commit;
    SET status = "Player transfered successfully";
    SELECT status;
END $$

DELIMITER ;