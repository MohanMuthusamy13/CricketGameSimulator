package com.example.CricketGameFinal.service;


import com.example.CricketGameFinal.model.PlayerModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class WicketService extends WinningService {
    private int wicketLose;


    public int getWicketLose() {
        return wicketLose;
    }

    public void setWicketLose(int wicketLose) {
        this.wicketLose = wicketLose;
    }
    public void gotWicket() {
        if (wicketLose >= 9) {
            if (GameService.getInnings() == 2) {
                checkWinningStatus();
            }
            else {
                // starting the second innings
                startSecondInnings();

            }
        }
        else {
            wicketLose++;
            ArrayList<PlayerModel> battingTeam = (ArrayList<PlayerModel>) GameService.getTeams().get(GameService.getBatting());
            battingTeam.get(GameService.getCurrentBatter()).setBallsFaced(1);
            battingTeam.get(GameService.getCurrentBatter()).setActiveStatus("inactive");
            GameService.setNextBatter();
            battingTeam.get(GameService.getCurrentBatter()).setActiveStatus("active");


            ArrayList<PlayerModel> bowlingTeam = (ArrayList<PlayerModel>) GameService.getTeams().get(Math.abs(1 - GameService.getBatting()));
            bowlingTeam.get(GameService.getCurrentBowler()).setWicketsTaken(1);
        }
    }

    public void startSecondInnings() {

        System.out.println("\nStarted 2nd Innings :)");

        ArrayList<PlayerModel> pt = (ArrayList<PlayerModel>) GameService.getTeams().get(GameService.getBatting());
        pt.get(GameService.getCurrentBatter()).setActiveStatus("inactive");

        ArrayList<PlayerModel> ptd = (ArrayList<PlayerModel>) GameService.getTeams().get(Math.abs(1 - GameService.getBatting()));
        ptd.get(GameService.getCurrentBowler()).setActiveStatus("inactive");


        GameService.setInnings(2);
        wicketLose = 0;


        GameService.setBatting(Math.abs(1 - GameService.getBatting()));
        score.setCurrentScore(0);

        OverService.startFromFirstOver();

        GameService.setCurrentBatter(0);
        OverService.setTempBallCount(0);
        GameService.setCurrentBowler(7);


        System.out.println(String.format("The over got initialized %d.%d", OverService.getOverCount(), OverService.getBallsCount()));
    }
}
