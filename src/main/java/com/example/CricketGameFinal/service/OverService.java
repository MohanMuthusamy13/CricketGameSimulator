package com.example.CricketGameFinal.service;

import com.example.CricketGameFinal.model.PlayerModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class OverService {
    private static int overCount;
    private static int ballsCount;

    private static int tempBallCount;

    private static PlayerModel currentBowler;

    private static ArrayList<PlayerModel> bowlingTeam;

    public static int getOverCount() {
        return overCount;
    }

    public static void setOverCount(int overCount) {
        overCount = overCount;
    }

    public static int getBallsCount() {
        return ballsCount;
    }

    public static void setBallsCount(int ballsCount) {
        ballsCount = ballsCount;
    }

    public String getOverInString() {
        return String.format("%d.%d", overCount, ballsCount);
    }

    public static void startFromFirstOver() {
        overCount = 0;
        ballsCount = 0;
    }

    public void IncreaseBallCount() {
        if (ballsCount == 5) {
            overCount++;

            ballsCount = 0;
        }
        else {
            ballsCount++;
        }
    }

    private ArrayList<PlayerModel> getBowlingTeam() {
        return bowlingTeam;
    }

    public static void setTempBallCount(int tempBallCount) {
        OverService.tempBallCount = tempBallCount;
    }

    public static void BowlingStarts() {

        bowlingTeam = (ArrayList<PlayerModel>) GameService.getTeams().get(Math.abs(1 - GameService.getBatting()));
        currentBowler = bowlingTeam.get(GameService.getCurrentBowler());

        if (tempBallCount < 6) {
            tempBallCount++;
            currentBowler.setBallsBowled(1);
        }
        else {
            bowlingTeam.get(GameService.getCurrentBowler()).setActiveStatus("inactive");
            GameService.setNextBowler();
            currentBowler = bowlingTeam.get(GameService.getCurrentBowler());
            currentBowler.setActiveStatus("active");

            currentBowler.setBallsBowled(1);
            tempBallCount = 1;
        }
    }
}



