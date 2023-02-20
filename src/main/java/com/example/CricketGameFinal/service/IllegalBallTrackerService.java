package com.example.CricketGameFinal.service;

import com.example.CricketGameFinal.model.entities.ScoreModel;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class IllegalBallTrackerService {

    private final int ignoreBallCount = -1;
    @Autowired
    ScoreModel score;
    private int wideBalls;
    private int noBalls;

    public void wideTracker() {
        score.addScore(GameServiceImpl.getBatting(), 1);
        GameServiceImpl.getBowlingPlayer().setBallsBowled(ignoreBallCount);
        wideBalls++;
    }

    public void noBallTracker() {
        score.addScore(GameServiceImpl.getBatting(), 1);
        GameServiceImpl.getBowlingPlayer().setBallsBowled(ignoreBallCount);
        noBalls++;
    }
}