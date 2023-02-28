package com.example.CricketGameFinal.controller;

import com.example.CricketGameFinal.model.entities.Player;
import com.example.CricketGameFinal.service.repositoriesService.PlayerRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stats")
public class MatchStatisticsController {

    @Autowired
    PlayerRepositoryService playerRepositoryService;

    @GetMapping("/showMaximumScorePlayer")
    public ResponseEntity<Player> findMaximumScorer() {
        return new ResponseEntity<>(playerRepositoryService.findMaxScorer(), HttpStatus.OK);
    }

    @GetMapping("/showMaxWicketTaker")
    public ResponseEntity<Player> maxWicketTaker() {
        return new ResponseEntity<>(playerRepositoryService.findMaxWicketTaker(), HttpStatus.OK);
    }

    @GetMapping("/showTeamPlayers/{teamName}")
    public ResponseEntity<List<Player>> findTeamPlayers(@PathVariable String teamName) {
        return new ResponseEntity<>(playerRepositoryService.findTeamPlayers(teamName), HttpStatus.OK);
    }

}