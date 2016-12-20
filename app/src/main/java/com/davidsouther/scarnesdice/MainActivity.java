package com.davidsouther.scarnesdice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView playerScoreText;
    private TextView computerScoreText;
    private TextView turnScoreText;
    private TextView actionText;
    private ImageView dieView;

    private int currentTurn;
    private int playerTotal;
    private int computerTotal;

    private Players whosTurn = Players.PLAYER;
    private int computerTurns = 0;

    private Random random;

    final Handler timerHandler = new Handler();

    public MainActivity() {
        this(new Random());
    }

    public MainActivity(Random random) {
        this.random = random;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dieView = (ImageView) findViewById(R.id.dieView);
        computerScoreText =(TextView) findViewById(R.id.computerScoreText);
        playerScoreText = (TextView) findViewById(R.id.playerScoreText);
        turnScoreText = (TextView) findViewById(R.id.turnScoreText);
        actionText = (TextView) findViewById(R.id.computerAction);

        resetScores();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public void roll(View view) {
        int frame = rollDice();

        if (frame == 1) {
            changePlayers();
            actionText.setText("A 1! Changing players!");
            turnScoreText.setText("");
            resetTurn();
        } else {
            actionText.setText("");
            currentTurn += frame;
            turnScoreText.setText(String.valueOf(currentTurn));
            checkForWin();
        }
    }

    private void computerTurn() {
        if (computerTurns < 3 || currentTurn < (3.5 * computerTurns)) {
            roll(null);
            computerTurns += 1;
        } else {
            hold(null);
        }
    }

    private void computerTurnIn500() {
        timerHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                computerTurn();
                if (whosTurn == Players.COMPUTER) {
                    computerTurnIn500();
                }
            }
        }, 500);
    }

    private void checkForWin() {
        switch (whosTurn) {
            case PLAYER: if (playerTotal + currentTurn > 100) playerWins(); break;
            case COMPUTER: if (computerTotal + currentTurn > 100) computerWins(); break;
        }
    }

    private void playerWins() {
        dieView.setImageResource(R.drawable.player_wins);
    }

    private void computerWins() {
        dieView.setImageResource(R.drawable.computer_wins);
    }

    private void changePlayers() {
        switch (whosTurn) {
            case PLAYER:
                whosTurn = Players.COMPUTER;
                computerTurnIn500();
                break;
            case COMPUTER:
                whosTurn = Players.PLAYER;
                break;
        }
    }

    private void playerScores(int turnTotal) {
        actionText.setText(String.format("Player scores %d", turnTotal));
        playerTotal += turnTotal;
        playerScoreText.setText(String.valueOf(playerTotal));
    }

    private void computerScores(int turnTotal) {
        actionText.setText(String.format("Computer scores %d", turnTotal));
        computerTotal += turnTotal;
        computerScoreText.setText(String.valueOf(computerTotal));
    }

    public void hold(View view) {
        switch (whosTurn) {
            case PLAYER:
                playerScores(currentTurn);
                break;
            case COMPUTER:
                computerScores(currentTurn);
                break;
        }
        resetTurn();
        changePlayers();
    }

    public void reset(View view) {
        resetScores();
        dieView.setImageResource(R.drawable.empty);
        whosTurn = this.random.nextBoolean() ? Players.PLAYER : Players.COMPUTER;
    }

    private int rollDice() {
        // Roll a random between 1 and 6, update the image, and return the value.
        int roll = (random.nextInt() % 6) + 1;
        if (roll < 1) { roll += 6; }
        int die;
        switch (roll) {
            case 1: die = R.drawable.dice1; break;
            case 2: die = R.drawable.dice2; break;
            case 3: die = R.drawable.dice3; break;
            case 4: die = R.drawable.dice4; break;
            case 5: die = R.drawable.dice5; break;
            case 6: die = R.drawable.dice6; break;
            default:
                throw new BadRollException(roll);
        }
        dieView.setImageResource(die);
        return roll;
    }

    private void resetScores() {
        resetTurn();

        playerTotal = 0;
        computerTotal = 0;

        computerScoreText.setText("0");
        playerScoreText.setText("0");
    }

    private void resetTurn() {
        currentTurn = 0;
        computerTurns = 0;
        turnScoreText.setText("");
        dieView.setImageResource(R.drawable.empty);
    }
}

class BadRollException extends RuntimeException {
    public BadRollException(int roll) {
        super(String.format("Tried to roll a six-sided dice and got %d", roll));
    }
}

enum Players {
    PLAYER,
    COMPUTER,
}