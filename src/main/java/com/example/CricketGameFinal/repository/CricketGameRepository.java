package com.example.CricketGameFinal.repository;

import com.example.CricketGameFinal.model.entities.Batsman;
import com.example.CricketGameFinal.model.entities.Bowler;
import com.example.CricketGameFinal.model.entities.PlayerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CricketGameRepository extends JpaRepository<PlayerModel, Integer> {

    @Transactional
    @Modifying
    @Query(value = "create table playerstats as (select * from batsman union select * from bowler order by player_id asc)", nativeQuery = true)
    void mergePlayerStatsTableOfBatsManAndBowler();

    @Query(value = "select * from playerstats where team_name = ?1", nativeQuery = true)
    List<Batsman> findByTeamName(String teamName);

    @Query(value = "select * from playerstats where balls_faced = (select min(balls_faced) from playerstats where score = (select max(score) from playerstats)) and score = (select max(score) from playerstats)", nativeQuery = true)
    Batsman findMaxScorer();

    @Query(value = "select * from playerstats where balls_bowled = (select min(balls_bowled) from playerstats where wickets_taken = (select max(wickets_taken) from playerstats)) AND wickets_taken = (select max(wickets_taken) from playerstats);", nativeQuery = true)
    Bowler findMaxWicketTaker();


}