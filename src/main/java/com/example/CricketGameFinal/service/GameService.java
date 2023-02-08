package com.example.CricketGameFinal.service;


import com.example.CricketGameFinal.model.PlayerModel;
import com.example.CricketGameFinal.model.ScoreModel;
import com.example.CricketGameFinal.view.ScoreBoardDisplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Component
public class GameService {

    private static List teams;
    private int runsScorePerBall;
    private static int currentBatter;
    private static int currentBowler = 7;
    private int wickets;

    private static String tempRunsScorePerBall;
    private static int[] scoreTeams = new int[2];
    private static int Innings;
    private static int Batting;
    private static int Bowling;
    private static int totalOvers;
    private static boolean legalBallFlag = false;
    private static int wideBalls;
    private static int noBalls;
    private TreeMap<Integer, Integer> dict = new TreeMap<>();


    @Autowired
    ScoreModel score;

    @Autowired
    OverService over;

    @Autowired
    WicketService wicketTracker;

    @Autowired
    ScoreBoardDisplay scoreBoardDisplay;

    @Autowired
    TossService tossService;

    WinningService checkWinning = new WinningService();


    public GameService() {
        teams = new PlayersTeamService().generateBothTeams();
        new ScoreModel().setScoreOfBothTeams(new int[2]);
        GameService.scoreTeams = new ScoreModel().getScoreOfBothTeams();
        this.wickets = new WicketService().getWicketLose();
        GameService.Innings = 1;
        runsDictGenerator();
    }


    public static PlayerModel getBattingPlayer() {
        return ((ArrayList<PlayerModel>)teams.get(Batting)).get(currentBatter);
    }

    public static PlayerModel getBowlingPlayer() {
        return ((ArrayList<PlayerModel>)teams.get(Math.abs(1 - Batting))).get(currentBowler);
    }

    public static int getBatting() {
        return Batting;
    }

    public static void setBatting(int batting) {
        Batting = batting;
    }

    public static int getBowling() {
        return Bowling;
    }

    public static void setBowling(int bowling) {
        Bowling = bowling;
    }

    public static int getInnings() {
        return Innings;
    }

    public static void setInnings(int innings) {
        Innings = innings;
    }

    public static int getTotalOvers() {
        return totalOvers;
    }

    public static void setTotalOvers(int totalOvers) {
        GameService.totalOvers = totalOvers;
    }

    public void wicketTrackerFunc() {
        wicketTracker.gotWicket();
    }

    public static int[] getScoreTeams() {
        return scoreTeams;
    }

    public void setScoreTeams(int[] scoreTeams) {
        this.scoreTeams = scoreTeams;
    }

    public static String getTempRunsScorePerBall() {
        return tempRunsScorePerBall;
    }

    public static int getCurrentBatter() {
        return currentBatter;
    }

    public static void setCurrentBatter(int currentBatter) {
        getBattingPlayer().setActiveStatus("active");
        GameService.currentBatter = currentBatter;
    }

    public static int getCurrentBowler() {
        return currentBowler;
    }

    public static void setCurrentBowler(int currentBowler) {
        getBowlingPlayer().setActiveStatus("active");
        GameService.currentBowler = currentBowler;
    }

    public static void setNextBowler() {
        if (currentBowler < 10) {
            currentBowler += 1;
        }
        else {
            setCurrentBowler(7);
        }
    }

    public static void setNextBatter() {
        GameService.currentBatter += 1;
    }

    public static List getTeams() {
        return teams;
    }

    public void addScoreToBatter(int Batting, int currentBatter, int runsScorePerBall) {
        getBattingPlayer().setScore(runsScorePerBall);
        getBattingPlayer().setBallsFaced(1);
    }

    public void runsDictGenerator() {
        dict.put(3, -3); // NO BALL
        dict.put(6, -2); // WIDE
        dict.put(12, -1); // WICKET
        dict.put(20, 0);
        dict.put(41, 1);
        dict.put(55, 2);
        dict.put(67, 3);
        dict.put(87, 4);
        dict.put(91, 5);
        dict.put(100, 6);
    }

    public int runsGenerator() {
        int randomNumber = (int)(Math.random() * 100) ;

        for (Map.Entry map : dict.entrySet()) {
            if (randomNumber < (int)map.getKey()) {
                return (int)map.getValue();
            }
        }
        return 0;
    }

    public void wideTracker() {
        score.addScore(Batting, 1);
        getBowlingPlayer().setBallsBowled(-1); // ignoring the ball count
        wideBalls++;
    }

    public void noBallTracker() {
        score.addScore(Batting, 1);
        getBowlingPlayer().setBallsBowled(-1);
        noBalls++;
    }


    public void BowlTheBall() {


        runsScorePerBall = runsGenerator();

        if (runsScorePerBall == -1) {
            wicketTrackerFunc();
            legalBallFlag = true;
        }
        else if (runsScorePerBall == -2) {
            wideTracker();
            legalBallFlag = false;
        }
        else if (runsScorePerBall == -3) {
            noBallTracker();
            legalBallFlag = false;
        }
        else {
            score.addScore(Batting, runsScorePerBall);
            addScoreToBatter(Batting, currentBatter, runsScorePerBall);
            legalBallFlag = true;
        }

        if (legalBallFlag) {
            OverService.BowlingStarts();
        }


        // check winning status
        if (checkWinning.checkWinningStatusNumber() != 3) {
            checkWinning.checkWinningStatus();
            scoreBoardDisplay.showFinalScoreBoard();
            System.exit(0);
        }
        else {
            // increase the balls bowled
            if (legalBallFlag)
                over.IncreaseBallCount();
        }


        if (runsScorePerBall < 0) {
            if (runsScorePerBall == -1) {
                tempRunsScorePerBall = "WICKET";
            }
            else if (runsScorePerBall == -2) {
                tempRunsScorePerBall = "WIDE";
            }
            else {
                tempRunsScorePerBall = "NO BALL";
            }
        }
        else {
            tempRunsScorePerBall = Integer.toString(runsScorePerBall);
        }

        scoreBoardDisplay.showStatusPerBall();

    }

    public void setActiveStatusForPlayers() {
        getBattingPlayer().setActiveStatus("active");
        getBowlingPlayer().setActiveStatus("active");
    }

    public void startGame() {

        tossService.startTossing();

        setActiveStatusForPlayers();


        while (Innings <= 2) {

            // CONDITION FOR STARTING WITH SECOND INNINGS
            if ((wicketTracker.getWicketLose() > 9 || OverService.getOverCount() == totalOvers) && Innings == 1) {
                wicketTracker.startSecondInnings();
            }
            else if (Innings == 2 && (OverService.getOverCount() == totalOvers)){
                scoreBoardDisplay.showScoreOfBothTeams();
                checkWinning.checkWinningStatus();
                scoreBoardDisplay.showFinalScoreBoard();
                break;
            }
            else {
                BowlTheBall();
            }

        }

    }



    // UTILITIES
    public String getFinalScore() {
        return String.format("FINAL SCORE\nThe score of team 1 : %d" +
                "\nThe score of team 2 : %d", scoreTeams[0], scoreTeams[1]);
    }

    public String getScoreBoard() {
        return String.format("SCORE BOARD\n" +
                "Score board of Team 1\n" +
                "%s\n" +
                "Score board of Team 2\n" +
                "%s", teams.get(0), teams.get(1));
    }

    public String getResults() {
        if (scoreTeams[0] > scoreTeams[1]) {
            return "Team 1 Wins";
        }
        else if (scoreTeams[0] < scoreTeams[1]) {
            return "Team 2 Wins";
        }
        return "Draw";
    }


}

