package com.example.CricketGameFinal.service;

import com.example.CricketGameFinal.view.ScoreBoardDisplay;
import org.springframework.stereotype.Component;

@Component
public class WicketStatusProvider extends WinningStatusProvider {
    private static int wicketLose;
    private static boolean wicketFlag = false;

    public static boolean isWicketFlag() {
        return wicketFlag;
    }

    public static int getWicketLose() {
        return wicketLose;
    }

    public static void setWicketLose(int wicketLose) {
        WicketStatusProvider.wicketLose = wicketLose;
    }

    public void gotWicket() {
        if (wicketLose >= 9) {
            if (GameServiceImpl.getInnings() == 2) {
                wicketFlag = true;
            }
            else {
                startSecondInnings();
            }
        }
        else {
            wicketLose++;

            GameServiceImpl.getBattingPlayer().setBallsFaced(1);
            GameServiceImpl.getBattingPlayer().setActiveStatus("inactive");

            GameServiceImpl.setNextBatter();
            GameServiceImpl.getBattingPlayer().setActiveStatus("active");

            GameServiceImpl.getBowlingPlayer().setWicketsTaken(1);
        }
    }

    public void startSecondInnings() {

        ScoreBoardDisplay.printFirstInningsFinalScore();

        System.out.println("\nStarted 2nd Innings :)");
        GameServiceImpl.getBattingPlayer().setActiveStatus("inactive");
        GameServiceImpl.getBowlingPlayer().setActiveStatus("inactive");
        GameServiceImpl.setInnings(2);
        wicketLose = 0;

        GameServiceImpl.setBatting(Math.abs(1 - GameServiceImpl.getBatting()));
        score.setCurrentScore(0);

        OverService.startFromFirstOver();
        GameServiceImpl.setCurrentBatter(0);
        OverService.setTempBallCount(0);
        GameServiceImpl.setCurrentBowler(7);
        GameServiceImpl.setRunsScorePerBall(0);

        System.out.printf("The over got initialized %d.%d%n", OverService.getOverCount(), OverService.getBallsCount());
    }
}