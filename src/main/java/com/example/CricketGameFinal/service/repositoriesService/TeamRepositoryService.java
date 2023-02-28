package com.example.CricketGameFinal.service.repositoriesService;

import com.example.CricketGameFinal.model.entities.Team;
import com.example.CricketGameFinal.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamRepositoryService {

    @Autowired
    TeamRepository teamRepository;

    public void saveTeam(Team team) {
        teamRepository.save(team);
    }

    public void saveTeams(List<Team> teams) {
        for (Object team : teams) {
            saveTeam((Team) team);
        }
    }

}