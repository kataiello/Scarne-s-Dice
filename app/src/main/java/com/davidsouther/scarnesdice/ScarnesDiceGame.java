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

    private MultiPlayers currentPlayer;


    public ScarnesDiceGame() {

    }

    public ScarnesDiceGame(String player1, String player2, MultiPlayers currentPlayer) {
        id = String.format("%s_%s", sanitizeEmail(player1), sanitizeEmail(player2));

        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = currentPlayer;

        lastRoll = currentTurn = player1Score = player2Score = 0;
    }

    public static String sanitizeEmail(String email) {
        return email.replace('.', '-')
                .replace('#', '-')
                .replace('$', '-')
                .replace('[', '-')
                .replace(']', '-');

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

    public MultiPlayers getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(MultiPlayers currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}

enum MultiPlayers {
    PLAYER1,
    PLAYER2
}