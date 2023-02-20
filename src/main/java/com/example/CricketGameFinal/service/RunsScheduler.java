package com.example.CricketGameFinal.service;

import java.util.Objects;
import java.util.TreeMap;

// SINGLETON CLASS

public class RunsScheduler {

    public static RunsScheduler runsSchedulerInstance;
    private static TreeMap<Integer, Integer> BatsmanBattingDictionary;
    private static TreeMap<Integer, Integer> BowlerBattingDictionary;
    private static TreeMap<Integer, Integer> AllRounderBattingDictionary;

    private RunsScheduler() {
        BatsmanBattingDictionary = new TreeMap<>()
        {{
            put(2, -3);
            put(4, -2);
            put(8, -1);
            put(12, 0);
            put(20, 1);
            put(40, 2);
            put(47, 3);
            put(80, 4);
            put(85, 5);
            put(100, 6);
        }};
        BowlerBattingDictionary = new TreeMap<>()
        {{
            put(3, -3);
            put(6, -2);
            put(12, -1);
            put(32, 0);
            put(60, 1);
            put(70, 2);
            put(75, 3);
            put(92, 4);
            put(97, 5);
            put(100, 6);
        }};
        AllRounderBattingDictionary = new TreeMap<>()
        {{
            put(3, -3);
            put(6, -2);
            put(12, -1);
            put(32, 0);
            put(60, 1);
            put(82, 2);
            put(85, 3);
            put(93, 4);
            put(97, 5);
            put(100, 6);
        }};
    }

    public static TreeMap<Integer, Integer> getBatsmanBattingDictionary() {
        return BatsmanBattingDictionary;
    }

    public static TreeMap<Integer, Integer> getBowlerBattingDictionary() {
        return BowlerBattingDictionary;
    }

    public static TreeMap<Integer, Integer> getAllRounderBattingDictionary() {
        return AllRounderBattingDictionary;
    }

    public static RunsScheduler getRunsSchedulerInstance() {
        return Objects.requireNonNullElseGet(runsSchedulerInstance, RunsScheduler::new);
    }
}