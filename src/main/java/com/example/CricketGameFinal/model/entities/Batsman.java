package com.example.CricketGameFinal.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Batsman")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Batsman extends PlayerModel {

    private String name;

    private int score;

    private int ballsFaced;

    @ToString.Exclude
    private int ballsBowled;

    @ToString.Exclude
    private int wicketsTaken;

    private String baseAbility = "Batsman";

    @ToString.Exclude
    private String activeStatus = "inactive";

    private String teamName;

    public Batsman(String name, int score, int ballsFaced, int ballsBowled, int wicketsTaken, String teamName) {
        this.name = name;
        this.score = score;
        this.ballsFaced = ballsFaced;
        this.ballsBowled = ballsBowled;
        this.wicketsTaken = wicketsTaken;
        this.teamName = teamName;
    }


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
}
