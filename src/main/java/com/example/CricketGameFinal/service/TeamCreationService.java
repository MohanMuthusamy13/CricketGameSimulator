package com.example.CricketGameFinal.service;

import com.example.CricketGameFinal.model.entities.Team;
import org.springframework.stereotype.Service;

@Service
public class TeamCreationService {

    public Team build(String teamName) {
        return Team.builder().teamName(teamName).matchesPlayed(1).build();
    }

}