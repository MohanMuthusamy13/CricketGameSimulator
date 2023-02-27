package com.example.CricketGameFinal.service;

import com.example.CricketGameFinal.model.entities.*;
import com.example.CricketGameFinal.service.repositoriesService.PlayerRepositoryService;
import com.example.CricketGameFinal.service.repositoriesService.ScoreRepositoryService;
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
    private static List playingTeamsPlayers;
    private static List playingTeams;
    private static List scoreRecords = new ArrayList<>();
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
    WicketStatusProvider wicketTracker;
    @Autowired
    ScoreBoardDisplay scoreBoardDisplay;
    @Autowired
    RunsGenerator runsGenerator;
    @Autowired
    PlayersTeamService playersTeamService;
    WinningStatusProvider checkWinning = new WinningStatusProvider();

    @Autowired
    PlayerRepositoryService playerRepositoryService;
    @Autowired
    ScoreRepositoryService scoreRepositoryService;

    @Autowired
    ScoreBuilder scoreBuilder;

    public GameServiceImpl() {
        gameServiceProvider();
    }

    public void gameServiceProvider() {
        PlayerBuilder playerBuilder = new PlayerBuilder();
        playingTeamsPlayers = new PlayersTeamService(playerBuilder).generateBothTeams();
        new ScoreModel().setScoreOfBothTeams(new int[2]);
        GameServiceImpl.scoreTeams = new ScoreModel().getScoreOfBothTeams();
        GameServiceImpl.wickets = WicketStatusProvider.getWicketLose();
        GameServiceImpl.Innings = 1;
    }

    public static PlayerModel getBattingPlayer() {
        return ((ArrayList<PlayerModel>) playingTeamsPlayers.get(Batting)).get(currentBatter);
    }

    public static PlayerModel getBowlingPlayer() {
        return ((ArrayList<PlayerModel>) playingTeamsPlayers.get(Math.abs(1 - Batting))).get(currentBowler);
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

    public static List getScoreRecords() {
        return scoreRecords;
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

    public static List getPlayingTeamsPlayers() {
        return playingTeamsPlayers;
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
            IllegalBallTrackerService.wideTracker();
            GameServiceImpl.setLegalBallFlag(false);
        }
        else if (runsScorePerBall == -3) {
            IllegalBallTrackerService.noBallTracker();
            GameServiceImpl.setLegalBallFlag(false);
        }
        else {
            ScoreModel.addScore(Batting, runsScorePerBall);
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
                OverService.IncreaseBallCount();
        }

        if (Innings == 2) {
            if (!checkWinning.checkWinningStatusForSecondInnings().equals("")){
                flagForTeamWinningIndicationOnSecondInnings = "Game Over";
            }
        }
        scoreBoardDisplay.showStatusPerBall();

        String overCount = String.valueOf(OverService.getOverCount()) + "." + OverService.getBallsCount();
        boolean isWicket = (runsScorePerBall == -1);

        ScoreRecordModel scorePerBallTracker =
                scoreBuilder
                        .setOverCount(overCount)
                        .setRunsScored(scoreBoardDisplay.runsForDisplayProvider(runsScorePerBall))
                        .setInnings(Innings)
                        .setTotalWicketsDown(WicketStatusProvider.getWicketLose())
                        .setCurrentBatsman(GameServiceImpl.getBattingPlayer())
                        .setCurrentBowler(GameServiceImpl.getBowlingPlayer())
                        .createScoreTrackerRecord();
        scoreRecords.add(scorePerBallTracker);
    }


    public void startGame() {
        playerRepositoryService.storePlayerStats(playingTeamsPlayers);
        TossService.startTossing();
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