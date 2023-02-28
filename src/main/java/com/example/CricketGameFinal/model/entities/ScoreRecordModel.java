package com.example.CricketGameFinal.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ScoreRecordModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ball_id", nullable = false)
    public Long ballId;

    public String overCount;

    public String ballOutCome;

    public int innings;

    public int totalWicketsDown;

    @ManyToOne
    @JoinColumn(name = "batsman_id")
    private Player batsman;

    @ManyToOne
    @JoinColumn(name = "bowler_id")
    private Player bowler;

    public ScoreRecordModel(String overCount, String ballOutCome, int innings, int totalWicketsDown,
                            Player currentBatsman, Player currentBowler) {
        this.overCount = overCount;
        this.ballOutCome = ballOutCome;
        this.innings = innings;
        this.totalWicketsDown = totalWicketsDown;
        batsman = currentBatsman;
        bowler = currentBowler;
    }
}