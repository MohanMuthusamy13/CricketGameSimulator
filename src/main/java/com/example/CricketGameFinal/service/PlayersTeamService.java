package com.example.CricketGameFinal.service;

import com.example.CricketGameFinal.model.entities.PlayerBuilder;
import com.example.CricketGameFinal.model.entities.Player;
import com.example.CricketGameFinal.model.entities.Team;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlayersTeamService {

    @Autowired
    TeamCreationService teamCreationService;

    private int teamNumber = 1;

    @Autowired
    private final PlayerBuilder playerBuilder;

    private ArrayList<Player> playerTeam = new ArrayList<>();
    private final Faker faker = new Faker();
    private final List<List> playerTeams = new ArrayList<>();

    public PlayersTeamService(PlayerBuilder playerBuilder) {
        this.playerBuilder = playerBuilder;
    }


//    public List generateTeam(int teamNumber) {
//
//        String baseAbility;
//        playerTeam = new ArrayList<>();
//        int playerCount = 11;
//        for (int i = 1; i <= playerCount; i++) {
//            if (i < 6) {
//                baseAbility = "Batsman";
//            }
//            else {
//                baseAbility = "Bowler";
//            }
//            Player player =
//                    playerBuilder
//                            .setPlayerName(faker.name().fullName())
//                            .setTeamName(String.format("Team %d", teamNumber))
//                            .setBaseAbility(baseAbility)
//                            .createPlayer();
//            playerTeam.add(player);
//        };
//        return playerTeam;
//    }
//
//    public List generateBothTeams() {
//        for (int i = 1;i <= 2;i++) {
//            playerTeams.add(generateTeam(i));
//        }
//        return playerTeams;
//    }



    public Team generateTeam(String teamName) {

        Team team = new TeamCreationService().build(teamName);
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
            Player player =
                    playerBuilder
                            .setPlayerName(faker.name().fullName())
                            .setBaseAbility(baseAbility)
                            .setTeamName(teamName)
                            .setTeam(team)
                            .createPlayer();
            playerTeam.add(player);
        };

        team.setPlayers(playerTeam);

        return team;
    }


}