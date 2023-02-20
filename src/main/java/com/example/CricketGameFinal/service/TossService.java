package com.example.CricketGameFinal.service;

import com.example.CricketGameFinal.model.enums.Toss;
import com.example.CricketGameFinal.view.TossDisplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class TossService {

    @Autowired
    TossDisplay tossDisplay;

    public void startTossing() {

        Toss[] tossPossibilities = new Toss[] {Toss.HEAD, Toss.TAIL};
        Toss tossWinner = tossPossibilities[ThreadLocalRandom.current().nextInt(0, 2)];
        int teamSelected = tossWinner.getBattingOrderIndicator();
        tossDisplay.tossDisplayed(teamSelected);
        if (teamSelected == 0) {
            GameServiceImpl.setBatting(1);
            GameServiceImpl.setBowling(0);
        }
        else {
            GameServiceImpl.setBatting(0);
            GameServiceImpl.setBowling(1);
        }
    }
}