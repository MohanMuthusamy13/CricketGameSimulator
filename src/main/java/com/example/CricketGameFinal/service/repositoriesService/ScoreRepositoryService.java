package com.example.CricketGameFinal.service.repositoriesService;

import com.example.CricketGameFinal.model.entities.ScoreRecordModel;
import com.example.CricketGameFinal.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreRepositoryService {

    @Autowired
    ScoreRepository scoreRepository;

    public void saveScoreRecords(List scoreRecords) {
        for (Object scorePerBallTracker : scoreRecords) {
            savescoreRecord((ScoreRecordModel) scorePerBallTracker);
        }
    }

    public void savescoreRecord(ScoreRecordModel scoreRecord) {
        scoreRepository.save(scoreRecord);
    }
}