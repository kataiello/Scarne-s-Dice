package com.davidsouther.scarnesdice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView playerScoreText;
    private TextView computerScoreText;
    private TextView turnScoreText;
    private TextView actionText;
    private ImageView dieView;

    //individual to each phone
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private String mUserEmail;
    private PlayerState state;

    private DatabaseReference mFirebaseDatabase;

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

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser== null) {
            startActivity(new Intent(this, SignInActivity.class));
            finish();
            return;
        } else {
            mUserEmail = mFirebaseUser.getEmail();
            configureDatabase();
        }

        setContentView(R.layout.activity_main);

        dieView = (ImageView) findViewById(R.id.dieView);
        computerScoreText =(TextView) findViewById(R.id.computerScoreText);
        playerScoreText = (TextView) findViewById(R.id.playerScoreText);
        turnScoreText = (TextView) findViewById(R.id.turnScoreText);
        actionText = (TextView) findViewById(R.id.computerAction);

        actionText.setText(String.format("%d", 5));

//        resetScores();
    }

    private void configureDatabase() {
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();

        mFirebaseDatabase.child("players").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                PlayerState newState = dataSnapshot.getValue(PlayerState.class);
                if(newState.getEmail() != state.getEmail()
                        && newState.getStatus() == PlayerStatus.READY
                        && state.getStatus() == PlayerStatus.READY) {
                    state.setStatus(PlayerStatus.IN_GAME);
                    newState.setStatus(PlayerStatus.IN_GAME);
                    mFirebaseDatabase.child("players").child(state.getId()).setValue(state);
                    mFirebaseDatabase.child("players").child(newState.getId()).setValue(newState);

                    startGame(newState);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        state = new PlayerState();
        mFirebaseDatabase.child("players").push().setValue(state);
    }

    private void startGame(PlayerState newState) {
        
    }

    public void roll(View view) {
//        int frame = rollDice();
//
//        if (frame == 1) {
//            changePlayers();
//            actionText.setText(R.string.change_players);
//            turnScoreText.setText("");
//            resetTurn();
//        } else {
//            actionText.setText("");
//            currentTurn += frame;
//            turnScoreText.setText(String.valueOf(currentTurn));
//            checkForWin();
//        }
    }
//
//    private void computerTurn() {
//        if (computerTurns < 3 || currentTurn < (3.5 * computerTurns)) {
//            roll(null);
//            computerTurns += 1;
//        } else {
//            hold(null);
//        }
//    }

//    final Handler timerHandler = new Handler();
//    private void computerTurnIn500() {
//        timerHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                computerTurn();
//                if (whosTurn == Players.COMPUTER) {
//                    computerTurnIn500();
//                }
//            }
//        }, 500);
//    }

//    private void checkForWin() {
//        switch (whosTurn) {
//            case PLAYER: if (playerTotal + currentTurn > 25) playerWins(); break;
//            case COMPUTER: if (computerTotal + currentTurn > 100) computerWins(); break;
//        }
//    }
//
    public static final String USER_SCORE = "com.davidsouther.scarne.USER_SCORE";
//    private void playerWins() {
//        Intent intent = new Intent(this, WinActivity.class);
//        intent.putExtra(USER_SCORE, String.valueOf(playerTotal + currentTurn));
//        startActivity(intent);
//        resetScores();
//    }
//
//    private void computerWins() {
//        startActivity(new Intent(this, LoseActivity.class));
//        resetScores();
//    }

//    private void changePlayers() {
//        switch (whosTurn) {
//            case PLAYER:
//                whosTurn = Players.COMPUTER;
//                computerTurnIn500();
//                break;
//            case COMPUTER:
//                whosTurn = Players.PLAYER;
//                break;
//        }
//    }

//    private void playerScores(int turnTotal) {
//        actionText.setText(String.format(
//                getString(R.string.player_scores), turnTotal));
//        playerTotal += turnTotal;
//        playerScoreText.setText(String.valueOf(playerTotal));
//    }
//
//    private void computerScores(int turnTotal) {
//        actionText.setText(String.format("Computer scores %d", turnTotal));
//        computerTotal += turnTotal;
//        computerScoreText.setText(String.valueOf(computerTotal));
//    }

    public void hold(View view) {
//        switch (whosTurn) {
//            case PLAYER:
//                playerScores(currentTurn);
//                break;
//            case COMPUTER:
//                computerScores(currentTurn);
//                break;
//        }
//        resetTurn();
//        changePlayers();
    }

//    public void reset(View view) {
//        resetScores();
//        dieView.setImageResource(R.drawable.empty);
//        dieView.setContentDescription("Empty die face");
//        whosTurn = this.random.nextBoolean() ? Players.PLAYER : Players.COMPUTER;
//    }
//
//    private int rollDice() {
//        // Roll a random between 1 and 6, update the image, and return the value.
//        int roll = (random.nextInt() % 6) + 1;
//        if (roll < 1) { roll += 6; }
//        int die;
//        switch (roll) {
//            case 1: die = R.drawable.dice1; break;
//            case 2: die = R.drawable.dice2; break;
//            case 3: die = R.drawable.dice3; break;
//            case 4: die = R.drawable.dice4; break;
//            case 5: die = R.drawable.dice5; break;
//            case 6: die = R.drawable.dice6; break;
//            default:
//                throw new BadRollException(roll);
//        }
//        dieView.setImageResource(die);
//        dieView.setContentDescription(String.format(getString(R.string.die_face), die));
//        return roll;
//    }
//
//    private void resetScores() {
//        resetTurn();
//
//        playerTotal = 0;
//        computerTotal = 0;
//
//        computerScoreText.setText("0");
//        playerScoreText.setText("0");
//    }
//
//    private void resetTurn() {
//        currentTurn = 0;
//        computerTurns = 0;
//        turnScoreText.setText("");
//        dieView.setImageResource(R.drawable.empty);
//    }
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