package com.example.CricketGameFinal.view;

import com.example.CricketGameFinal.service.GameService;
import com.example.CricketGameFinal.service.OverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScoreBoardDisplay {


    @Autowired
    OverService over;

    public void showStatusPerBall() {
        System.out.println(String.format(
                "Runs Scored :     %s\n" +
                        "Overs       :     %s\n" +
                        "Total Score :     %d\n" +
                        "Current Active Batter : %s\t\t\t\t\tCurrent Active Batter Score : %d\n" +
                        "Current Active Bowler : %s\t\t\t\t\tCurrent Active Bowler Wickets : %d\n\n",
                GameService.getTempRunsScorePerBall(),
                over.getOverInString(), GameService.getScoreTeams()[GameService.getBatting()],
                GameService.getBattingPlayer().getName(), GameService.getBattingPlayer().getScore(),
                GameService.getBowlingPlayer().getName(), GameService.getBowlingPlayer().getWicketsTaken()
        ));
    }

    public void showStatusPerBallForTesting() {
        System.out.println("SCORE BOARD OF BATTING");
        System.out.println(GameService.getTeams().get(GameService.getBatting()));

        System.out.println("SCORE BOARD OF BOWLING");
        System.out.println(GameService.getTeams().get(Math.abs(1 - GameService.getBatting())));
    }

    public void showFinalScoreBoard() {

        System.out.println(String.format("\nSCORE BOARD OF TEAM 1\n"));
        System.out.println("\n" + GameService.getTeams().get(0));

        System.out.println(String.format("\nSCORE BOARD OF TEAM 2\n"));
        System.out.println("\n" + GameService.getTeams().get(1));
    }

    public void showScoreOfBothTeams() {
        System.out.println(String.format(
                "FINAL SCORE\n" +
                        "Team 1  : %d\n" +
                        "Team 2  : %d\n", GameService.getScoreTeams()[0], GameService.getScoreTeams()[1])
        );
    }
}


