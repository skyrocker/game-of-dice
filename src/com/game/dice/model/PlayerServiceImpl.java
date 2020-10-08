package com.game.dice.model;

import java.util.Random;

public class PlayerServiceImpl implements PlayerService {
    public int diceRoll() {
        Random rand = new Random();
        return rand.nextInt(6)+1;
    }
}
