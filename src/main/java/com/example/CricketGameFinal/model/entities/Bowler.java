package com.example.CricketGameFinal.model.entities;

import com.example.CricketGameFinal.service.RunsGenerator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "bowler")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Bowler extends PlayerModel{

    private String name;

    private int score;

    @ToString.Exclude
    private int ballsFaced;

    private int ballsBowled;

    private int wicketsTaken;

    private String baseAbility = "Bowler";

    @ToString.Exclude
    private String activeStatus = "inactive";

    private String teamName;

    public Bowler(String name, int score, int ballsFaced, int ballsBowled, int wicketsTaken, String teamName) {
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
