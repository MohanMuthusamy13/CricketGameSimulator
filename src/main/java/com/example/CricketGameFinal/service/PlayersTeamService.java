package com.example.CricketGameFinal.service;

import com.example.CricketGameFinal.model.entities.PlayerBuilder;
import com.example.CricketGameFinal.model.entities.PlayerModel;
import com.example.CricketGameFinal.service.repositoriesService.PlayerRepositoryService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlayersTeamService {

    private int teamNumber = 1;

    @Autowired
    private final PlayerBuilder playerBuilder;

    private ArrayList<PlayerModel> playerTeam = new ArrayList<>();
    private int teamSelected;
    private final Faker faker = new Faker();
    private final List<List> playerTeams = new ArrayList<>();

    @Autowired
    PlayerRepositoryService playerRepositoryService;

    public PlayersTeamService(PlayerBuilder playerBuilder) {
        this.playerBuilder = playerBuilder;
    }


    public List generateTeam(int teamNumber) {

        String baseAbility;

        playerTeam = new ArrayList<>();

        int playerCount = 11;

        for (int i = 1; i <= playerCount; i++) {

            if (i < 6) {
                baseAbility = "Batsman";
            }
            else {
                baseAbility = "Bowler";
            }
            PlayerModel player =
                    playerBuilder
                            .setPlayerName(faker.name().fullName())
                            .setTeamName(String.format("Team %d", teamNumber))
                            .setBaseAbility(baseAbility).createPlayer();


            playerTeam.add(player);
        };
        return playerTeam;
    }

    public List generateBothTeams() {
        for (int i = 1;i <= 2;i++) {
            playerTeams.add(generateTeam(i));
        }
        return playerTeams;
    }

//    public Team generateTeamNew(String teamName) {
//        return new Team(
//                teamName, 0, generateTeam(teamNumber)
//        );
//    }


}