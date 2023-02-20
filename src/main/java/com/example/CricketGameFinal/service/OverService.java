package com.example.CricketGameFinal.service;

import com.example.CricketGameFinal.model.entities.PlayerModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class OverService {

    @Getter @Setter
    private static int overCount;

    @Getter @Setter
    private static int ballsCount;

    @Setter
    private static int tempBallCount;

    @Getter
    private static ArrayList<PlayerModel> bowlingTeam;

    private static PlayerModel currentBowler;


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

    public static void BowlingStarts() {

        currentBowler = GameServiceImpl.getBowlingPlayer();

        if (tempBallCount < 6) {
            tempBallCount++;
            currentBowler.setBallsBowled(1);
        }
        else {
            currentBowler.setActiveStatus("inactive");
            GameServiceImpl.setNextBowler();
            currentBowler = GameServiceImpl.getBowlingPlayer();
            currentBowler.setActiveStatus("active");

            currentBowler.setBallsBowled(1);
            tempBallCount = 1;
        }
    }
}