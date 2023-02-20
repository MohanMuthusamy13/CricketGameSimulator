package com.example.CricketGameFinal.service.repositoriesService;

import com.example.CricketGameFinal.model.entities.Batsman;
import com.example.CricketGameFinal.model.entities.Bowler;
import com.example.CricketGameFinal.model.entities.PlayerModel;
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
            saveFunction((PlayerModel) playerModel);
        }
    }

    public void storePlayerStats(List teams) {
        saveHelper((List) teams.get(0));
        saveHelper((List) teams.get(1));
    }

    public void saveFunction(PlayerModel playerModel) {
        cricketGameRepository.save(playerModel);
    }

    public List<Batsman> findTeamPlayers(String teamName) {
        return cricketGameRepository.findByTeamName(teamName);
    }

    public Batsman findMaxScorer() {
        return cricketGameRepository.findMaxScorer();
    }

    public Bowler findMaxWicketTaker() {
        return cricketGameRepository.findMaxWicketTaker();
    }

    public void buildPlayerStatsTable() {
        cricketGameRepository.mergePlayerStatsTableOfBatsManAndBowler();
    }

}