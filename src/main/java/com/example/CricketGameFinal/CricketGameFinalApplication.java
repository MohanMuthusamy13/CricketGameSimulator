package com.example.CricketGameFinal;

import com.example.CricketGameFinal.model.entities.Bowler;
import com.example.CricketGameFinal.model.entities.PlayerBuilder;
import com.example.CricketGameFinal.repository.CricketGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CricketGameFinalApplication {
	@Autowired
	private CricketGameRepository cricketGameRepository;

	@Autowired
	PlayerBuilder playerBuilder;

	public static void main(String[] args) {
		SpringApplication.run(CricketGameFinalApplication.class, args);
	}
}
