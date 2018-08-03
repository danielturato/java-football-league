import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.Team;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.Scanner;
  
public class LeagueManager {
  
  private static final Scanner scanner = new Scanner(System.in);
  
  static final List<Player> PLAYERS = Players.load();
  static List<Player> availablePlayers = PLAYERS;
  static List<Team> teams = new ArrayList<>();

  public static void main(String[] args) {
    System.out.printf("There are currently %d registered players.%n", PLAYERS.size());
    run();
  }
  
  private static void run() {
    while (true) {
      displayMenu();
      int choice = getChoice();
      if (choice != 7) {
        processChoice(choice);
      } else {
        System.out.println("Quitting....");
        sleep(3);
        break;
      }
    }
  }
  
  private static void displayMenu() {
    System.out.printf("%n%n%n%n%n%n%n");
    System.out.println("*********MENU*********");
    System.out.println("1 -- Create a new team");
    System.out.println("2 -- Add player to team");
    System.out.println("3 -- Remove player from a team");
    System.out.println("4 -- View team height report");
    System.out.println("5 -- View league balance report");
    System.out.println("6 -- View team roster");
    System.out.println("7 -- Quit");
  }
  
  private static int getChoice() {
    int choice;
    do {
      System.out.printf("%nEnter your choice : ");
      while (!scanner.hasNextInt()) {
        System.out.println("That's not a number!");
        scanner.next(); // this is important!
      }
      choice = scanner.nextInt();
    } while (choice < 1 || choice > 7);
    
    return choice;
  }
  
  private static void processChoice(int choice) {
    switch (choice) {
      case 1:
        teams.add(LeagueOperations.createNewTeam());
        sleep(3);
        break;
      case 2:
        LeagueOperations.addPlayerToTeam(PLAYERS, teams);
        teams = LeagueOperations.tempTeams;
        availablePlayers = LeagueOperations.tempPlayers;
        sleep(5);
        break;
      case 3:
        LeagueOperations.removePlayerFromTeam(availablePlayers, PLAYERS, teams);
        teams = LeagueOperations.tempTeams;
        availablePlayers = LeagueOperations.tempPlayers;
        sleep(5);
        break;
      case 4:
        LeagueOperations.displayPlayersByHeight(teams);
        sleep(10);
        break;
      case 5:
        LeagueOperations.displayExperianceDifference(teams);
        sleep(10);
        break;
      case 6:
        LeagueOperations.displayTeamRoster(teams);
        sleep(10);
        break;
      case 7:
        break;
      default:
        System.out.println("You've entered an invalid option. Try again!");
    }
  }
  
  private static void sleep(int time) {
    try {
      TimeUnit.SECONDS.sleep(time);
    } catch (InterruptedException ix) {
      System.out.println("ERROR: time sleep");
      ix.printStackTrace();                   
    }
  }
  
}
  