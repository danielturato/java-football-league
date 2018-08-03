package com.teamtreehouse.model;

import java.util.Set;
import java.util.HashSet;

public class Team {
  
  private String name;
  private String coach;
  private Set<Player> team;

  public Team(String name, String coach) {
    this.name = name;
    this.coach = coach;
    team = new HashSet<>();
  }
  
  public String getName() {
    return name;
  }
  
  public String getCoach() {
    return coach;
  }
  
  public void getTeam() {
    if (team.size() > 0) {
      int count = 1;
      for (Player player : team) {
        System.out.printf("%d) %s%n", count, player.toString());
        count++;
      }
    } else {
      System.out.println("There are no players in the team");
    }
  }

  public Set<Player> returnTeam() {
    return team;
  }
  
  public void addToTeam(Player player) {
    team.add(player);
  }
  
  public void removeFromTeam(Player player) {
    team.remove(player);
  }
  
  @Override
  public String toString() {
    return String.format("Name : %s, Coach : %s", name, coach);
  }

}