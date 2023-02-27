package com.example.CricketGameFinal.model.entities;

public class PlayerFactory {

    public static PlayerModel createBatsmanWithInitialConditions(String name, String teamName) {
        return new PlayerModel(name, 0, 0, 0, 0, "Batsman", "inactive", teamName);
    }


    public static PlayerModel createBowlerWithInitialConditions(String name, String teamName) {
        return new PlayerModel(name, 0, 0, 0, 0, "Bowler", "inactive", teamName);
    }

    public static PlayerModel createPlayer(String name, String teamName, String baseAbility) {
        if (baseAbility.equals("Batsman")) {
            return createBatsmanWithInitialConditions(name, teamName);
        }
        else {
            return createBowlerWithInitialConditions(name, teamName);
        }
    }
}