package com.example.CricketGameFinal.service.repositoriesService;


import com.example.CricketGameFinal.model.entities.Player;
import com.example.CricketGameFinal.repository.CricketGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerRepositoryService {

    @Autowired
    private CricketGameRepository cricketGameRepository;

    public void saveHelper(List team) {
        for (Object playerModel : team) {
            saveFunction((Player) playerModel);
        }
    }

    public void storePlayerStats(List teams) {
        saveHelper((List) teams.get(0));
        saveHelper((List) teams.get(1));
    }

    public void saveFunction(Player playerModel) {
        cricketGameRepository.save(playerModel);
    }

    public List<Player> findTeamPlayers(String teamName) {
        return cricketGameRepository.findByTeamName(teamName);
    }

    public Player findMaxScorer() {
        return cricketGameRepository.findMaxScorer();
    }

    public Player findMaxWicketTaker() {
        return cricketGameRepository.findMaxWicketTaker();
    }

}