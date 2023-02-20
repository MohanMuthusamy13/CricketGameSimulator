package com.example.CricketGameFinal.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ScoreModel {

    @Getter
    private int CurrentScore;
    private int BallsTaken;
    private int totalScore;
    private static int[] scoreOfBothTeams;

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

