package com.example.CricketGameFinal.view;


public class TossDisplay {
    public static void tossDisplayed(int teamSelected) {
        System.out.println("STARTING WITH THE COIN TOSS : \n");

        if (teamSelected == 0) {
            System.out.println("""
                    Team 1 : Bowling
                    Team 2 : Batting
                    """);
        }
        else {
            System.out.println("""
                    Team 1 : Batting
                    Team 2 : Bowling
                    """);
        }
    }
}