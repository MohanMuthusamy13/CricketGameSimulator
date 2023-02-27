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
    private PlayerModel batsman;

    @ManyToOne
    @JoinColumn(name = "bowler_id")
    private PlayerModel bowler;

    public ScoreRecordModel(String overCount, String ballOutCome, int innings, int totalWicketsDown,
                            PlayerModel currentBatsman, PlayerModel currentBowler) {
        this.overCount = overCount;
        this.ballOutCome = ballOutCome;
        this.innings = innings;
        this.totalWicketsDown = totalWicketsDown;
        batsman = currentBatsman;
        bowler = currentBowler;
    }
}