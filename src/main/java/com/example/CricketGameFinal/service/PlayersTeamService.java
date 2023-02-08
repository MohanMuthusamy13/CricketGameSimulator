package com.example.CricketGameFinal.service;

import com.example.CricketGameFinal.model.PlayerModel;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class PlayersTeamService {
    private ArrayList<PlayerModel> playerTeam = new ArrayList<>();
    private int teamSelected;

    private Faker faker = new Faker();


    private List<List> playerTeams = new ArrayList<>();
    private int playerCount = 11;

    public List generateTeam() {
        playerTeam = new ArrayList<>();
        for (int i = 1; i <= playerCount;i++) {
            PlayerModel player = new PlayerModel();
            player.setName(faker.name().fullName());

            if (i < 6) {
                player.setBaseAbility("Batsman");
            }
            else if (i < 8) {
                player.setBaseAbility("All rounder");
            }
            else {
                player.setBaseAbility("Bowler");
            }
            playerTeam.add(player);
        }
        return playerTeam;
    }

    public List generateBothTeams() {
        for (int i = 1;i <= 2;i++) {
            playerTeams.add(generateTeam());
        }
        return playerTeams;
    }

}
