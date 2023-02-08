package com.example.CricketGameFinal.service;

import com.example.CricketGameFinal.model.ScoreModel;

public class WinningService {
    private String str;
    ScoreModel score = new ScoreModel();
    int i = 1;


    public byte checkTeam1WinCondition() {
        if (score.getScoreOfBothTeams()[0] > score.getScoreOfBothTeams()[1]) {
            return 1;
        } else {
            return 2;
        }
    }

    public byte checkWinningStatusNumber() {

        // IF OVERS GOT COMPLETED

        if (new OverService().getOverCount() == GameService.getTotalOvers()) {

            if (checkTeam1WinCondition() == 1) {
                return 1;
            } else {
                return 2;
            }

        } else if (i == 2) {

            // IF OVERS ARE NOT COMPLETED

            if (checkTeam1WinCondition() == 1) {
                return 1;
            } else {
                return 2;
            }
        }
        return 3;
    }

    public void checkWinningStatus() {
        if (checkWinningStatusNumber() == 1) {
            System.out.println("The Game is over :)"+ "\n" +"Team 1 Wins");
        } else if (checkWinningStatusNumber() == 2) {
            System.out.println("The Game is over :)"+ "\n" +"Team 2 Wins");
        }
        else {
            System.out.println("The Game is over :)" + "\n" +"Game is drawn");
        }
    }
}
