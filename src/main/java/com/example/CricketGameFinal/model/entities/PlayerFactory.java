package com.example.CricketGameFinal.model.entities;

public class PlayerFactory {

    public static Player createBatsmanWithInitialConditions(String name, String teamName, Team team) {
        return new Player(name, 0, 0, 0, 0, "Batsman", "inactive",teamName, team);
    }


    public static Player createBowlerWithInitialConditions(String name,String teamName, Team team) {
        return new Player(name, 0, 0, 0, 0, "Bowler", "inactive",teamName, team);
    }

    public static Player createPlayer(String name, String baseAbility,String teamName, Team team) {
        if (baseAbility.equals("Batsman")) {
            return createBatsmanWithInitialConditions(name, teamName,  team);
        }
        else {
            return createBowlerWithInitialConditions(name, teamName, team);
        }
    }
}