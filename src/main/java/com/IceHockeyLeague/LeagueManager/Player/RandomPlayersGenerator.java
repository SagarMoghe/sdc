package com.IceHockeyLeague.LeagueManager.Player;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomPlayersGenerator implements IRandomPlayersGenerator {
    // Reference: Random names - https://homepage.net/name_generator/
    private static final String[] firstNames = {
            "Adam",
            "Adrian",
            "Alan",
            "Alexander",
            "Andrew",
            "Anthony",
            "Austin",
            "Benjamin",
            "Blake",
            "Boris",
            "Brandon",
            "Brian",
            "Cameron",
            "Carl",
            "Charles",
            "Christian",
            "Christopher",
            "Colin",
            "Connor",
            "Dan",
            "David",
            "Dominic",
            "Dylan",
            "Edward",
            "Eric",
            "Evan",
            "Frank",
            "Gavin",
            "Gordon",
            "Harry",
            "Ian",
            "Isaac",
            "Jack",
            "Jacob",
            "Jake",
            "James",
            "Jason",
            "Joe",
            "John",
            "Jonathan",
            "Joseph",
            "Joshua",
            "Julian",
            "Justin",
            "Keith",
            "Kevin",
            "Leonard",
            "Liam",
            "Lucas",
            "Luke",
            "Matt",
            "Max",
            "Michael",
            "Nathan",
            "Neil",
            "Nicholas",
            "Oliver",
            "Owen",
            "Paul",
            "Peter",
            "Phil",
            "Piers",
            "Richard",
            "Robert",
            "Ryan",
            "Sam",
            "Sean",
            "Sebastian",
            "Simon",
            "Stephen",
            "Steven",
            "Stewart",
            "Thomas",
            "Tim",
            "Trevor",
            "Victor",
            "Warren",
            "William"
    };
    private static final String[] lastNames = {
            "Abraham",
            "Allan",
            "Alsop",
            "Anderson",
            "Arnold",
            "Avery",
            "Bailey",
            "Baker",
            "Ball",
            "Bell",
            "Berry",
            "Black",
            "Blake",
            "Bond",
            "Bower",
            "Brown",
            "Burgess",
            "Butler",
            "Cameron",
            "Campbell",
            "Carr",
            "Chapman",
            "Churchill",
            "Clark",
            "Clarkson",
            "Coleman",
            "Cornish",
            "Davidson",
            "Davies",
            "Dickens",
            "Duncan",
            "Dyer",
            "Ellison",
            "Ferguson",
            "Fisher",
            "Forsyth",
            "Fraser",
            "Gibson",
            "Gill",
            "Glover",
            "Graham",
            "Grant",
            "Gray",
            "Greene",
            "Hamilton",
            "Harris",
            "Hart",
            "Henderson",
            "Hill",
            "Hodges",
            "Howard",
            "Hudson",
            "Hughes",
            "Hunter",
            "Jackson",
            "James",
            "Johnston",
            "Jones",
            "Kelly",
            "Kerr",
            "King",
            "Knox",
            "Lambert",
            "Lawrence",
            "Lee",
            "Lewis",
            "Lyman",
            "MacDonald",
            "Mackay",
            "Mackenzie",
            "MacLeod",
            "Manning",
            "Marshall",
            "Martin",
            "Mathis",
            "May",
            "McDonald",
            "McLean",
            "McGrath",
            "Young"
    };
    private static final int MIN_DRAFT_PLAYER_AGE = 18;
    private static final int MAX_DRAFT_PLAYER_AGE = 21;
    private static final int FORWARD_RATIO = 50;
    private static final int DEFENSE_RATIO = 40;
    private static IRandomChance randomChance;
    private final ILeagueManagerFactory leagueManagerFactory;

    public RandomPlayersGenerator(IRandomChance randomChance) {
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        RandomPlayersGenerator.randomChance = randomChance;
    }

    public List<IPlayer> generateRandomPlayers(LocalDate currentDate, int totalPlayers) {
        if(totalPlayers <= 0) {
            return new ArrayList<>();
        }

        List<IPlayer> generatedPlayers = new ArrayList<>();
        int numberOfForwardsToGenerate = numberOfPlayersToGenerate(totalPlayers, FORWARD_RATIO);
        int numberOfDefenseToGenerate = numberOfPlayersToGenerate(totalPlayers, DEFENSE_RATIO);

        for(int i = 0; i < totalPlayers; i++) {
            IPlayer player = generateRandomBasePlayer(currentDate);
            if(generatedPlayers.size() < numberOfForwardsToGenerate) {
                player.setPlayerStats(generateRandomStatsForForward());
            }
            else if(generatedPlayers.size() < numberOfForwardsToGenerate + numberOfDefenseToGenerate) {
                player.setPlayerStats(generateRandomStatsForDefense());
            }
            else {
                player.setPlayerStats(generateRandomStatsForGoalie());
            }
            generatedPlayers.add(player);
        }
        return generatedPlayers;
    }

    private int numberOfPlayersToGenerate(int totalPlayers, int percentage) {
        if(totalPlayers > 0) {
            return ((100 * percentage) / totalPlayers);
        }
        return 0;
    }

    private String generateRandomPlayerName() {
        int firstNameRandomIndex = randomChance.getRandomIntegerNumber(0, firstNames.length - 1);
        int lastNameRandomIndex = randomChance.getRandomIntegerNumber(0, lastNames.length - 1);

        return firstNames[firstNameRandomIndex] + lastNames[lastNameRandomIndex];
    }

    private LocalDate generateRandomPlayerDateOfBirth(LocalDate currentDate) {
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo.setBirthDate(currentDate);
        LocalDate minBirthDate = playerAgeInfo.getBirthDateForGivenYear(currentDate.getYear() - MAX_DRAFT_PLAYER_AGE);
        LocalDate maxBirthDate = playerAgeInfo.getBirthDateForGivenYear(currentDate.getYear() - MIN_DRAFT_PLAYER_AGE);
        long randomEpochDay = ThreadLocalRandom.current().nextLong(minBirthDate.toEpochDay(), maxBirthDate.toEpochDay());
        return LocalDate.ofEpochDay(randomEpochDay);
    }

    private IPlayer generateRandomBasePlayer(LocalDate currentDate) {
        IPlayer player = leagueManagerFactory.createPlayer();
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();

        player.setPlayerName(generateRandomPlayerName());
        playerAgeInfo.setBirthDate(generateRandomPlayerDateOfBirth(currentDate));
        playerAgeInfo.setAgeInYears(playerAgeInfo.calculatePlayerAgeInYears(currentDate));
        playerAgeInfo.setElapsedDaysFromLastBDay(playerAgeInfo.calculateElapsedDaysFromLastBDay(currentDate));
        player.setPlayerAgeInfo(playerAgeInfo);

        return player;
    }

    private IPlayerStats generateRandomStatsForForward() {
        IPlayerStats forwardPlayerStats = leagueManagerFactory.createPlayerStats();
        forwardPlayerStats.setPosition(PlayerPosition.FORWARD.toString());
        forwardPlayerStats.setSkating(randomChance.getRandomIntegerNumber(12, 20));
        forwardPlayerStats.setShooting(randomChance.getRandomIntegerNumber(12, 20));
        forwardPlayerStats.setChecking(randomChance.getRandomIntegerNumber(9, 18));
        forwardPlayerStats.setSaving(randomChance.getRandomIntegerNumber(1, 7));
        return forwardPlayerStats;
    }

    private IPlayerStats generateRandomStatsForDefense() {
        IPlayerStats defensePlayerStats = leagueManagerFactory.createPlayerStats();
        defensePlayerStats.setPosition(PlayerPosition.DEFENSE.toString());
        defensePlayerStats.setSkating(randomChance.getRandomIntegerNumber(10, 19));
        defensePlayerStats.setShooting(randomChance.getRandomIntegerNumber(9, 18));
        defensePlayerStats.setChecking(randomChance.getRandomIntegerNumber(12, 20));
        defensePlayerStats.setSaving(randomChance.getRandomIntegerNumber(1, 12));
        return defensePlayerStats;
    }

    private IPlayerStats generateRandomStatsForGoalie() {
        IPlayerStats goaliePlayerStats = leagueManagerFactory.createPlayerStats();
        goaliePlayerStats.setPosition(PlayerPosition.GOALIE.toString());
        goaliePlayerStats.setSkating(randomChance.getRandomIntegerNumber(8, 15));
        goaliePlayerStats.setShooting(randomChance.getRandomIntegerNumber(1, 10));
        goaliePlayerStats.setChecking(randomChance.getRandomIntegerNumber(1, 12));
        goaliePlayerStats.setSaving(randomChance.getRandomIntegerNumber(12, 20));
        return goaliePlayerStats;
    }
}
