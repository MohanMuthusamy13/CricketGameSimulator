package com.example.CricketGameFinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResetGameServiceImpl implements ResetGameService{

    @Autowired
    GameServiceImpl gameService;

    public void resetGame() {
        GameServiceImpl.setBatting(0);
        GameServiceImpl.setBowling(0);
        GameServiceImpl.setInnings(1);
        GameServiceImpl.setCurrentBatter(0);
        GameServiceImpl.setCurrentBowler(7);
        GameServiceImpl.setWickets(0);

        IllegalBallTrackerService.setWideBalls(0);
        IllegalBallTrackerService.setNoBalls(0);

        gameService.setScoreTeams(new int[2]);

    }

}