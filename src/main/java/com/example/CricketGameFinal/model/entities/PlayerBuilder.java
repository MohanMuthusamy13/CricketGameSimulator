package com.example.CricketGameFinal.model.entities;

import org.springframework.stereotype.Component;

// BUILDER CLASS

@Component
public class PlayerBuilder {
    private String playerName;
    private int playerScore;
    private int ballsFaced;
    private int ballsBowled;
    private int wicketsTaken;
    private String baseAbility;
    private String teamName;

    public PlayerBuilder setPlayerName(String name) {
        this.playerName = name;
        return this;
    }

    public PlayerBuilder setPlayerScore(int score) {
        this.playerScore = score;
        return this;
    }

    public PlayerBuilder setBallsFaced(int ballsFaced) {
        this.ballsFaced = ballsFaced;
        return this;
    }

    public PlayerBuilder setBallsBowled(int ballsBowled) {
        this.ballsBowled = ballsBowled;
        return this;
    }

    public PlayerBuilder setWicketsTaken(int wicketsTaken) {
        this.wicketsTaken = wicketsTaken;
        return this;
    }

    public PlayerBuilder setBaseAbility(String baseAbility) {
        this.baseAbility = baseAbility;
        return this;
    }


    public PlayerBuilder setTeamName(String teamName) {
        this.teamName = teamName;
        return this;
    }


    public PlayerModel createPlayer() {
        if (baseAbility.equals("Batsman")) {
            return PlayerFactory.createBatsmanWithInitialConditions(playerName, teamName);
        }
        else {
            return PlayerFactory.createBowlerWithInitialConditions(playerName, teamName);
        }
    }
}