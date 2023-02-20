package com.example.CricketGameFinal.controller;

import com.example.CricketGameFinal.service.GameServiceImpl;
import com.example.CricketGameFinal.service.repositoriesService.PlayerRepositoryService;
import com.example.CricketGameFinal.service.ResetGameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

    @Autowired
    GameServiceImpl cricket;

    @Autowired
    PlayerRepositoryService playerRepositoryService;

    @Autowired
    ResetGameServiceImpl resetGameService;

    @GetMapping("/startGame")
    public ResponseEntity<String> startGame() {
        cricket.matchFormatScheduler();
        cricket.startGame();
        playerRepositoryService.storePlayerStats(GameServiceImpl.getPlayingTeams());
        playerRepositoryService.buildPlayerStatsTable();
        return new ResponseEntity<>("The game is has started... :)\nGot over you can check the results",
                HttpStatus.OK);
    }

    @GetMapping("/resetGame")
    public ResponseEntity<String> resetGame() {
        resetGameService.resetGame();
        return new ResponseEntity<>("Game has reset:)", HttpStatus.OK);
    }
}