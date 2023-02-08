package com.example.CricketGameFinal.controller;

import com.example.CricketGameFinal.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @Autowired
    GameService cricket;

    @RequestMapping("/")
    public String start() {
        return "Start the game by requesting start game";
    }

    @RequestMapping("/startgame")
    public String index() {
        GameService.setTotalOvers(10);
        cricket.startGame();
        return "The game is currently going on... :)\nGot over you can check the results";
    }

    @RequestMapping("/getFinalScore")
    public String scoreDisplay() {
        return cricket.getFinalScore();
    }

    @RequestMapping("/getFinalScoreBoard")
    public String scoreBoard() {
        return cricket.getScoreBoard();
    }

    @RequestMapping("/getResults")
    public String results() {
        return cricket.getResults();
    }

}

