package com.example.CricketGameFinal.repository;

import com.example.CricketGameFinal.model.entities.ScoreRecordModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<ScoreRecordModel, Long> {

}