package com.davidsouther.scarnesdice;

/**
 * Created by Kat Aiello on 1/18/17.
 */

public class ScarnesDiceGame
{
    private String id;
    private String player1;
    private String player2;

    private int lastRoll;

    private int player1Score;
    private int player2Score;
    private int currentTurn;

    private Players currentPlayer;


    public ScarnesDiceGame() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public int getLastRoll() {
        return lastRoll;
    }

    public void setLastRoll(int lastRoll) {
        this.lastRoll = lastRoll;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public void setPlayer2Score(int player2Score) {
        this.player2Score = player2Score;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    public Players getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Players currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
