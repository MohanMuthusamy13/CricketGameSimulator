package com.example.CricketGameFinal.model.enums;

import lombok.Getter;

@Getter
public enum Toss {
    HEAD (1),
    TAIL (0);

    private final int battingOrderIndicator;
    Toss(int battingOrderIndicator) {
        this.battingOrderIndicator = battingOrderIndicator;
    }
}