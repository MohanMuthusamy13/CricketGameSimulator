package com.example.CricketGameFinal.controller;

import com.example.CricketGameFinal.service.GameServiceImpl;
import com.example.CricketGameFinal.service.repositoriesService.PlayerRepositoryService;
import com.example.CricketGameFinal.service.ResetGameServiceImpl;
import com.example.CricketGameFinal.service.repositoriesService.ScoreRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Scanner;

@Controller
public class GameController {

    @Autowired
    GameServiceImpl cricket;

    @Autowired
    PlayerRepositoryService playerRepositoryService;

    @Autowired
    ScoreRepositoryService scoreRepositoryService;

    @Autowired
    ResetGameServiceImpl resetGameService;

    @GetMapping("/startGame")
    public ResponseEntity<String> startGame() {
        cricket.matchFormatScheduler();
        cricket.startGame();
        playerRepositoryService.storePlayerStats(GameServiceImpl.getPlayingTeamsPlayers());
        scoreRepositoryService.saveScoreRecords(GameServiceImpl.getScoreRecords());
        return new ResponseEntity<>("The game has started... :)\nGot over you can check the results",
                HttpStatus.OK);
    }

    @GetMapping("/resetGame")
    public ResponseEntity<String> resetGame() {
        resetGameService.resetGame();
        return new ResponseEntity<>("Game has reset:)", HttpStatus.OK);
    }
}