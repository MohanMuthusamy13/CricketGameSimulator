package com.example.CricketGameFinal.service;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

@Component
public class RunsGenerator {

    RunsScheduler runsScheduler = RunsScheduler.getRunsSchedulerInstance();

    public TreeMap<Integer, Integer> dictionaryAssigner(String playerBaseAbility) {
        if (playerBaseAbility.equals("Batsman")) {
            return RunsScheduler.getBatsmanBattingDictionary();
        }
        else if (playerBaseAbility.equals("Bowler")) {
            return RunsScheduler.getBowlerBattingDictionary();
        }else {
            return RunsScheduler.getAllRounderBattingDictionary();
        }
    }


    public int runsGeneratorByAbility(String playerBaseAbility) {
        TreeMap<Integer, Integer> dictionary = dictionaryAssigner(playerBaseAbility);
        int randomNumber = (int)(Math.random() * 100) ;
        for (Map.Entry map : dictionary.entrySet()) {
            if (randomNumber < (int)map.getKey()) {
                return (int)map.getValue();
            }
        }
        return 0;
    }
}
