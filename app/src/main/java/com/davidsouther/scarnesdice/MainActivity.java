package com.davidsouther.scarnesdice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView playerScoreText;
    private TextView computerScoreText;
    private TextView frameScoreText;
    private TextView computerActionText;
    private TextView turnScoreText;
    private ImageView dieView;

    private int frame;
    private int playerTotal;
    private int computerTotal;

    private Random random;

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
        playerScoreText = (TextView) findViewById(R.id.playerScoreText);
        frameScoreText = (TextView) findViewById(R.id.turnScoreText);

        resetScores();
    }

    public void roll(View view) {
        frame = rollDice();
        playerTotal += frame;

        playerScoreText.setText(String.valueOf(playerTotal));
        frameScoreText.setText(String.valueOf(frame));
    }

    public void hold(View view) {

    }

    public void reset(View view) {
        resetScores();
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
        frame = 0;
        playerTotal = 0;
        computerTotal = 0;

        frameScoreText.setText("");
    }
}

class BadRollException extends RuntimeException {
    public BadRollException(int roll) {
        super(String.format("Tried to roll a six-sided dice and got %d", roll));
    }
}