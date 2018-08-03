import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Team;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class LeagueOperations {
  
  private static final Scanner scanner = new Scanner(System.in);

  public static List<Player> tempPlayers = new ArrayList<>();
  public static List<Team> tempTeams = new ArrayList<>();

  public static Team createNewTeam() {
    System.out.printf("%nEnter a team name : ");
    String name = scanner.next();
    System.out.printf("%nEnter the coach name : "); 
    String coach = scanner.next();
    
    Team team = new Team(name, coach);
    System.out.printf("%nCreated new team --- %s", team);
    return team;
    
  }
  
  private static void validateIntInput(String message) {
    System.out.printf(message);
      while (!scanner.hasNextInt()) {
        System.out.println("That's not a number!");
        scanner.next(); // this is important!
      }
  }
  
  public static void addPlayerToTeam(List<Player> players, List<Team> teams) {
    if (players.size() > 0) {
      displayPlayers(players);
      int playerNumber;
      do {
        validateIntInput("%nEnter the player number : ");
        playerNumber = scanner.nextInt();
      } while (players.get(playerNumber - 1) == null);

      if (teams.size() > 0) {
        displayTeams(teams);
        int teamNumber;
        do {
          validateIntInput("%nEnter the team number : ");
          teamNumber = scanner.nextInt();
        } while (teams.get(teamNumber - 1) == null);

        Team team = teams.get(teamNumber - 1);
        teams.remove(team);
        team.addToTeam(players.get(playerNumber - 1));
        players.remove(players.get(playerNumber - 1));
        teams.add(team);
        tempPlayers = players;
        tempTeams = teams;
      } else {
        System.out.printf("%nThere are no teams. You need to create one via the menu.");
        tempPlayers = players;
        tempTeams = teams;
      }
    } else {
      System.out.printf("%nThere are no more players left to add. Remove players from your teams to access them.");
      tempPlayers = players;
      tempTeams = teams;
    }
  }

  public static void removePlayerFromTeam(List<Player> availablePlayers, List<Player> players, List<Team> teams) {
    if (teams.size() > 0) {
      displayTeams(teams);
      int teamNumber;
      do {
        validateIntInput("%nEnter the team number : ");
        teamNumber = scanner.nextInt();
      } while (teams.get(teamNumber - 1) == null);

      if (teams.get(teamNumber-1).returnTeam().size() > 0) {
        Set<Player> playerSet = teams.get(teamNumber - 1).returnTeam();
        List<Player> uniquePlayers = new ArrayList<>();
        int count = 1;
        System.out.printf("%n********PLAYERS********%n");
        for (Player player : playerSet) {
          System.out.printf("%d) %s%n", count, player);
          count++;
          uniquePlayers.add(player);
        }
        int playerNumber;
        do {
          validateIntInput("%nEnter the player number you would like to remove : ");
          playerNumber = scanner.nextInt();
        } while (uniquePlayers.get(playerNumber - 1) == null);
        availablePlayers.add(uniquePlayers.get(playerNumber-1));
        teams.get(teamNumber - 1).removeFromTeam(uniquePlayers.get(playerNumber - 1));
        tempPlayers = availablePlayers;
        tempTeams = teams;
      } else {
        System.out.printf("%nYou have no players in your team to remove.");
        tempTeams = teams;
      }
    } else {
      System.out.printf("%nYou have no teams to remove players from");
      tempTeams = teams;
    }
  }

  public static void displayPlayersByHeight(List<Team> teams) {
    if (teams.size() > 0) {
      displayTeams(teams);
      int teamNumber;
      do {
        validateIntInput("%nEnter the team number : ");
        teamNumber = scanner.nextInt();
      } while (teams.get(teamNumber - 1) == null);

      if (teams.get(teamNumber-1).returnTeam().size() > 0) {
        Set<Player> teamPlayers = teams.get(teamNumber-1).returnTeam();
        List<Player> sortedPlayers = new ArrayList<>();
        for (Player p : teamPlayers) {
          sortedPlayers.add(p);
        }
        Collections.sort(sortedPlayers, Player.playerHeightComparator);
        Map<String, List<Player>> heightGroupings = new TreeMap<>();
        List<Player> groupOne = new ArrayList<Player>();
        List<Player> groupTwo = new ArrayList<Player>();
        List<Player> groupThree = new ArrayList<Player>();
        for (Player player : sortedPlayers) {
          int height = player.getHeightInInches();
          if (height >= 35 && height <= 40) {
            groupOne.add(player);
          } else if (height >= 41 && height <= 46) {
            groupTwo.add(player);
          } else {
            groupThree.add(player);
          }
        }
        heightGroupings.put(String.format("----Heights 35-40 (%d)---", groupOne.size()), groupOne);
        heightGroupings.put(String.format("----Heights 41-46 (%d)---", groupTwo.size()), groupTwo);
        heightGroupings.put(String.format("----Heights 47-50 (%d)---", groupThree.size()), groupThree);
        System.out.printf("%n********PLAYERS********%n%n");
        for (Map.Entry<String, List<Player>> entry : heightGroupings.entrySet()) {
          String key = entry.getKey();
          List<Player> value = entry.getValue();
          System.out.println(key);
          for (Player p : value) {
            System.out.println(p);
          }
        }
      } else {
        System.out.printf("%nYou have no players in your team to view");
      }
    } else {
      System.out.printf("%nYou have no teams to view the players");
    }
  }

  public static void displayExperianceDifference(List<Team> teams) {
    if (teams.size() > 0) {
      displayTeams(teams);
      int teamNumber;
      do {
        validateIntInput("%nEnter the team number : ");
        teamNumber = scanner.nextInt();
      } while (teams.get(teamNumber - 1) == null);

      if (teams.get(teamNumber-1).returnTeam().size() > 0) {
        List<Player> players = new ArrayList<>();
        for (Player player : teams.get(teamNumber-1).returnTeam()) {
          players.add(player);
        }
        Map<String, List<Player>> experianceGroupings = new TreeMap<>();
        List<Player> experianced = new ArrayList<>();
        List<Player> inexperianced = new ArrayList<>();
        for (Player p : players) {
          if (p.isPreviousExperience()) {
            experianced.add(p);
          } else {
            inexperianced.add(p);
          }
        }
        System.out.println(experianced.size());
        System.out.println(players.size());
        float sum = experianced.size()/players.size();
        System.out.println(sum);
        experianceGroupings.put(String.format("---Experianced (%f%%)---", (float) ((experianced.size()*100)/players.size())), experianced);
        experianceGroupings.put(String.format("---Inexperianced (%f%%)---", (float) ((inexperianced.size()*100)/players.size())), inexperianced);
        System.out.printf("%n********PLAYERS********%n%n");
        for (Map.Entry<String, List<Player>> entry : experianceGroupings.entrySet()) {
          String key = entry.getKey();
          List<Player> value = entry.getValue();

          System.out.println(key);
          for (Player p : value) {
            System.out.println(p);
          }
        }
      } else {
        System.out.printf("%nYou have no players in your team to view");
      }
    } else {
      System.out.printf("%nYou have no teams to view the players");
    }
  }

  public static void displayTeamRoster(List<Team> teams) {
    if (teams.size() > 0) {
      displayTeams(teams);
      int teamNumber;
      do {
        validateIntInput("%nEnter the team number : ");
        teamNumber = scanner.nextInt();
      } while (teams.get(teamNumber - 1) == null);

      if (teams.get(teamNumber-1).returnTeam().size() > 0) {
        Set<Player> temp = teams.get(teamNumber-1).returnTeam();
        List<Player> players = new ArrayList<>();
        for (Player player : temp) {
          players.add(player);
        }
        displayPlayers(players);
      } else {
        System.out.printf("%nYou have no players in your team to view");
      }
    } else {
      System.out.printf("%nYou have no teams to view the players");
    }
  }
  
  private static void displayPlayers(List<Player> players) {
    System.out.printf("%n********PLAYERS********%n");
    Collections.sort(players);
    int pCount = 1;
    for (Player player : players) {
      System.out.printf("%d) %s%n", pCount, player);
      pCount++;
    }
  }
  
  private static void displayTeams(List<Team> teams) {
    System.out.printf("%n********TEAMS********%n");
    teams.sort((o1, o2) -> {
      return o1.getName().compareTo(o2.getName());
    });
    int tCount = 1;
    for (Team team : teams) {
      System.out.printf("%d) %s%n", tCount, team);
      tCount++;
    }
  }

}