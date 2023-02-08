package com.example.CricketGameFinal.model;

public class PlayerModel {
    private String name;
    private int score;
    private int ballsFaced;
    private int ballsBowled;

    private int wicketsTaken;
    private String baseAbility;

    private String activeStatus = "inactive";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score += score;
    }

    public int getBallsFaced() {
        return ballsFaced;
    }

    public void setBallsFaced(int ballsFaced) {
        this.ballsFaced += ballsFaced;
    }

    public int getBallsBowled() {
        return ballsBowled;
    }

    public void setBallsBowled(int ballsBowled) {
        this.ballsBowled += ballsBowled;
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public void setWicketsTaken(int wicketsTaken) {
        this.wicketsTaken += wicketsTaken;
    }

    public String getBaseAbility() {
        return baseAbility;
    }

    public void setBaseAbility(String baseAbility) {
        this.baseAbility = baseAbility;
    }

    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", ballsFaced=" + ballsFaced +
                ", ballsBowled=" + ballsBowled +
                ", wicketsTaken=" + wicketsTaken +
                ", baseAbility='" + baseAbility + '\'' +
                ", activeStatus='" + activeStatus + '\'' +
                '}' + "\n";
    }
}
