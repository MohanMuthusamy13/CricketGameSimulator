package com.example.CricketGameFinal.view;

import org.springframework.stereotype.Component;

@Component
public class TossDisplay {
    public void tossDisplayed(int teamSelected) {
        System.out.println("STARTING WITH THE COIN TOSS : \n");

        if (teamSelected == 0) {
            System.out.println("Team 1 : Bowling" + "\n" + "Team 2 : Batting\n");
        }
        else {
            System.out.println("Team 1 : Batting" + "\n" + "Team 2 : Bowling\n");
        }
    }
}
