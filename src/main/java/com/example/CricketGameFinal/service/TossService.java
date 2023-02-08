package com.example.CricketGameFinal.service;

import com.example.CricketGameFinal.view.TossDisplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class TossService {

    private int teamSelected;

    @Autowired
    TossDisplay tossDisplay;

    public int tossingOfCoin() {
        teamSelected = ThreadLocalRandom.current().nextInt(0, 2);
        tossDisplay.tossDisplayed(teamSelected);
        if (teamSelected == 0) {
            return 1; // score is incremented for team 2 as team2 is batting
        }
        return 0; // score is incremented for team 1 as team 1 is batting
    }

    public void startTossing() {

        if(tossingOfCoin() == 1) {
            GameService.setBatting(1);
            GameService.setBowling(0);
        }
        else {
            GameService.setBatting(0);
            GameService.setBowling(1);
        }
    }



}
