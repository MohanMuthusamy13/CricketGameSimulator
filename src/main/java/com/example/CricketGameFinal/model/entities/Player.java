package com.example.CricketGameFinal.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "playerstats")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    @Id
    @SequenceGenerator(name = "playerstats_sequence" , sequenceName = "playerstats_sequence" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "playerstats_sequence")
    @ToString.Exclude
    private Long playerId;

    private String name;

    private int score;

    @ToString.Exclude
    private int ballsFaced;

    @ToString.Exclude
    private int ballsBowled;

    private int wicketsTaken;

    private String baseAbility;

    @ToString.Exclude
    private String activeStatus = "inactive";

    private String teamName;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public void setScore(int score) {
        this.score += score;
    }

    public void setBallsFaced(int ballsFaced) {
        this.ballsFaced += ballsFaced;
    }

    public void setBallsBowled(int ballsBowled) {
        this.ballsBowled += ballsBowled;
    }

    public void setWicketsTaken(int wicketsTaken) {
        this.wicketsTaken += wicketsTaken;
    }

    public Player(String name, int score, int ballsFaced,
                  int ballsBowled, int wicketsTaken, String baseAbility,
                  String activeStatus,String teamName, Team team) {
        this.name = name;
        this.score = score;
        this.ballsFaced = ballsFaced;
        this.ballsBowled = ballsBowled;
        this.wicketsTaken = wicketsTaken;
        this.baseAbility = baseAbility;
        this.activeStatus = activeStatus;
        this.teamName = teamName;
        this.team = team;
    }
}