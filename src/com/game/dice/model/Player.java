package com.game.dice.model;

public class Player implements Comparable {
    private int id;
    private int score;
    private boolean skipNext;
    private int penalty;
    private int maxScore;
    private PlayerService playerService;

    public Player(int id, int maxScore, PlayerService playerService) {
        this.id = id;
        this.score = 0;
        this.skipNext = false;
        this.penalty = 0;
        this.maxScore = maxScore;
        this.playerService = playerService;
    }

    public boolean isSkipNext() {
        return skipNext;
    }

    public int getScore() {
        return score;
    }

    public int rollDice() {
        if (this.skipNext) {
            this.skipNext = false;
            return -1;
        }
        int num = this.playerService.diceRoll();
        this.score += num;
        if (num == 1) {
            this.score += num;
            this.penalty +=1;
            if (this.penalty == 2) {
                this.skipNext = true;
                System.out.println("Player-"+this.id+" : Consecutive 1's have occurred.");
                this.penalty = 0;
            }
        } else this.penalty = 0;

        if (this.score >= this.maxScore) return 0;
        return this.score;
    }


    @Override
    public int compareTo(Object o) {
        return this.score - ((Player) o).score;
    }

    @Override
    public String toString() {
        return "Player-"+this.id;
    }
}
