package com.example.CricketGameFinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResetGameServiceImpl implements ResetGameService{

    @Autowired
    GameServiceImpl gameService;

    @Autowired
    IllegalBallTrackerService illegalBallTrackerService;

    public void resetGame() {
        GameServiceImpl.setBatting(0);
        GameServiceImpl.setBowling(0);
        GameServiceImpl.setInnings(1);
        GameServiceImpl.setCurrentBatter(0);
        GameServiceImpl.setCurrentBowler(7);
        GameServiceImpl.setWickets(0);

        illegalBallTrackerService.setWideBalls(0);
        illegalBallTrackerService.setNoBalls(0);

        gameService.setScoreTeams(new int[2]);

    }

}