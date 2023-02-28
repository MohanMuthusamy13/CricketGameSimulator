package com.example.CricketGameFinal.model.entities;

import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@ToString
public class ScoreBuilder {

    public String overCount;
    public String ballOutCome;
    public int innings;
    private int wicketsDown;
    private Player currentBatsman;
    private Player currentBowler;

    public ScoreBuilder setOverCount(String overCount) {
        this.overCount = overCount;
        return this;
    }

    public ScoreBuilder setRunsScored(String ballOutCome) {
        this.ballOutCome = ballOutCome;
        return this;
    }

    public ScoreBuilder setInnings(int innings) {
        this.innings = innings;
        return this;
    }

    public ScoreBuilder setTotalWicketsDown(int wicketsDown) {
        this.wicketsDown = wicketsDown;
        return this;
    }

    public ScoreBuilder setCurrentBatsman(Player currentBatsman) {
        this.currentBatsman = currentBatsman;
        return this;
    }

    public ScoreBuilder setCurrentBowler(Player currentBowler) {
        this.currentBowler = currentBowler;
        return this;
    }



    public ScoreRecordModel createScoreTrackerRecord() {
        return new ScoreRecordModel(
                overCount, ballOutCome, innings,
                wicketsDown, currentBatsman, currentBowler);
    }

}