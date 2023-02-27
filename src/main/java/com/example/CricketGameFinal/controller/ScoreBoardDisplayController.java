package com.example.CricketGameFinal.controller;

import com.example.CricketGameFinal.view.ScoreBoardDisplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/display")
public class ScoreBoardDisplayController {

    @Autowired
    ScoreBoardDisplay scoreBoardDisplay;

    @GetMapping("/getFinalScore")
    public ResponseEntity<String> scoreDisplay() {
        return new ResponseEntity<>(scoreBoardDisplay.showScoreOfBothTeams(), HttpStatus.OK);
    }

    @GetMapping("/getFinalScoreBoard")
    public ResponseEntity<String> scoreBoard() {
        return new ResponseEntity<>(scoreBoardDisplay.getScoreBoard(), HttpStatus.OK);
    }

    @GetMapping("/getResults")
    public ResponseEntity<String> results() {
        return new ResponseEntity<>(scoreBoardDisplay.getResults(), HttpStatus.OK);
    }
}