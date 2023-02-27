package com.example.CricketGameFinal.repository;

import com.example.CricketGameFinal.model.entities.PlayerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CricketGameRepository extends JpaRepository<PlayerModel, Integer> {

    List<PlayerModel> findByTeamName(String teamName);

    @Query(value = "select * from playerstats where balls_faced = (select min(balls_faced) from playerstats where score = (select max(score) from playerstats)) and score = (select max(score) from playerstats)", nativeQuery = true)
    PlayerModel findMaxScorer();

    @Query(value = "select * from playerstats where balls_bowled = (select min(balls_bowled) from playerstats where wickets_taken = (select max(wickets_taken) from playerstats)) AND wickets_taken = (select max(wickets_taken) from playerstats);", nativeQuery = true)
    PlayerModel findMaxWicketTaker();


}