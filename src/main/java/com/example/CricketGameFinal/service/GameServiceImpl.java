package com.example.CricketGameFinal.service;

import com.example.CricketGameFinal.model.entities.*;
import com.example.CricketGameFinal.view.ScoreBoardDisplay;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Data
@Component
public class GameServiceImpl implements GameService {
    private static List playingTeams;
    private static int currentBatter;
    private static int currentBowler = 7;
    private static int[] scoreTeams = new int[2];
    private static int Innings;
    private static int Batting;
    private static int Bowling;
    private static int totalOvers;
    private static boolean legalBallFlag = false;
    private static String matchFormat;
    private static String flagForTeamWinningIndicationOnSecondInnings = "";
    private static int wickets;
    private static int runsScorePerBall;
    static Scanner sc = new Scanner(System.in);

    @Autowired
    ScoreModel score;
    @Autowired
    OverService over;
    @Autowired
    WicketStatusProvider wicketTracker;
    @Autowired
    ScoreBoardDisplay scoreBoardDisplay;
    @Autowired
    TossService tossService;
    @Autowired
    RunsGenerator runsGenerator;
    @Autowired
    IllegalBallTrackerService illegalBallTracker;
    WinningStatusProvider checkWinning = new WinningStatusProvider();

    public GameServiceImpl() {
        gameServiceProvider();
    }

    public void gameServiceProvider() {
        PlayerBuilder playerBuilder = new PlayerBuilder();
        playingTeams = new PlayersTeamService(playerBuilder).generateBothTeams();
        new ScoreModel().setScoreOfBothTeams(new int[2]);
        GameServiceImpl.scoreTeams = new ScoreModel().getScoreOfBothTeams();
        GameServiceImpl.wickets = WicketStatusProvider.getWicketLose();
        GameServiceImpl.Innings = 1;
    }

    public static PlayerModel getBattingPlayer() {
        return ((ArrayList<PlayerModel>) playingTeams.get(Batting)).get(currentBatter);
    }

    public static PlayerModel getBowlingPlayer() {
        return ((ArrayList<PlayerModel>) playingTeams.get(Math.abs(1 - Batting))).get(currentBowler);
    }

    public static int getBatting() {
        return Batting;
    }

    public static void setBatting(int batting) {
        Batting = batting;
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
        GameServiceImpl.totalOvers = totalOvers;
    }

    public void wicketTrackerFunc() {
        wicketTracker.gotWicket();
    }

    public static int[] getScoreTeams() {
        return scoreTeams;
    }

    public void setScoreTeams(int[] scoreTeams) {
        GameServiceImpl.scoreTeams = scoreTeams;
    }

    public static int getCurrentBatter() {
        return currentBatter;
    }

    public static void setCurrentBatter(int currentBatter) {
        getBattingPlayer().setActiveStatus("active");
        GameServiceImpl.currentBatter = currentBatter;
    }

    public static int getCurrentBowler() {
        return currentBowler;
    }

    public static void setCurrentBowler(int currentBowler) {
        getBowlingPlayer().setActiveStatus("active");
        GameServiceImpl.currentBowler = currentBowler;
    }

    public static boolean isLegalBallFlag() {
        return legalBallFlag;
    }

    public static void setLegalBallFlag(boolean legalBallFlag) {
        GameServiceImpl.legalBallFlag = legalBallFlag;
    }

    public static int getRunsScorePerBall() {
        return runsScorePerBall;
    }

    public static void setRunsScorePerBall(int runsScorePerBall) {
        GameServiceImpl.runsScorePerBall = runsScorePerBall;
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
        GameServiceImpl.currentBatter += 1;
    }

    public static List getPlayingTeams() {
        return playingTeams;
    }

    public void addScoreToBatter(int runsScorePerBall) {
        getBattingPlayer().setScore(runsScorePerBall);
        getBattingPlayer().setBallsFaced(1);
    }


    public static void setWickets(int wickets) {
        GameServiceImpl.wickets = wickets;
    }

    public static String getFlagForTeamWinningIndicationOnSecondInning() {
        return flagForTeamWinningIndicationOnSecondInnings;
    }

    public void setActiveStatusForPlayers() {
        getBattingPlayer().setActiveStatus("active");
        getBowlingPlayer().setActiveStatus("active");
    }

    public void matchFormatScheduler() {
        System.out.println("Enter the match format (T20, ODI, TEST)");
        String plannedMatchFormat = sc.next().toUpperCase();
        GameServiceImpl.setTotalOvers(MatchFormatService.oversScheduler(plannedMatchFormat));
    }

    public void startBattingAndBowling() {
        runsScorePerBall = runsGenerator.runsGeneratorByAbility(getBattingPlayer().getBaseAbility());
        if (runsScorePerBall == -1) {
            wicketTrackerFunc();
            GameServiceImpl.setLegalBallFlag(true);
        }
        else if (runsScorePerBall == -2) {
            illegalBallTracker.wideTracker();
            GameServiceImpl.setLegalBallFlag(false);
        }
        else if (runsScorePerBall == -3) {
            illegalBallTracker.noBallTracker();
            GameServiceImpl.setLegalBallFlag(false);
        }
        else {
            score.addScore(Batting, runsScorePerBall);
            addScoreToBatter(runsScorePerBall);
            GameServiceImpl.setLegalBallFlag(true);
        }

        if (legalBallFlag) {
            OverService.BowlingStarts();
        }

        if (checkWinning.checkWinningStatusNumber() != 3) {
            checkWinning.checkWinningStatus();
            scoreBoardDisplay.showFinalScoreBoard();
            System.exit(0);
        }
        else {
            if (legalBallFlag)
                over.IncreaseBallCount();
        }

        if (Innings == 2) {
            if (!checkWinning.checkWinningStatusForSecondInnings().equals("")){
                flagForTeamWinningIndicationOnSecondInnings = "Game Over";
            }
        }
        scoreBoardDisplay.showStatusPerBall();
    }


    public void startGame() {
        tossService.startTossing();
        setActiveStatusForPlayers();
        while (Innings <= 2) {
            if ((WicketStatusProvider.getWicketLose() > 9 || OverService.getOverCount() == totalOvers) && Innings == 1) {
                wicketTracker.startSecondInnings();
            }
            else if ((Innings == 2 && (OverService.getOverCount() == totalOvers)) ||
                    ((GameServiceImpl.flagForTeamWinningIndicationOnSecondInnings).equals("Game Over")) ||
                    (WicketStatusProvider.getWicketLose() >= 9 && WicketStatusProvider.isWicketFlag())){
                scoreBoardDisplay.showScoreOfBothTeams();
                checkWinning.checkWinningStatus();
                break;
            }
            else {
                startBattingAndBowling();
            }
        }
    }
}