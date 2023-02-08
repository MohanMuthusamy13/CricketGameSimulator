package com.example.CricketGameFinal.model;

import org.springframework.stereotype.Component;

@Component
public class ScoreModel {
    private int CurrentScore;
    private int BallsTaken;
    private int totalScore;

    public ScoreModel() {
        CurrentScore = 0;
        BallsTaken = 0;
        this.totalScore = 0;
    }

    private static int[] scoreOfBothTeams;

    public int getCurrentScore() {
        return CurrentScore;
    }

    public int setCurrentScore(int currentScore) {
        CurrentScore = currentScore;
        return currentScore;
    }

    public int[] getScoreOfBothTeams() {
        return scoreOfBothTeams;
    }

    public void setScoreOfBothTeams(int[] scoreOfBothTeams) {
        ScoreModel.scoreOfBothTeams = scoreOfBothTeams;
    }

    public void addScore(int current_team, int run) {
        scoreOfBothTeams[current_team] += run;
    }

}

