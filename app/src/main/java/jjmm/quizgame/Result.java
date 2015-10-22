package jjmm.quizgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Result extends jjmmActivity {

    int[] scores;

    String[] player_names;

    TextView scoreView;
    TextView winnerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        jjmmFullScreen();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        jjmmSoundManager().initMediaPlayer(this).play("rickroll");

        scores = (int[]) getIntent().getExtras().get("scores");
        player_names = (String[]) getIntent().getExtras().get("player_names");

        scoreView = (TextView) findViewById(R.id.result);
        winnerView = (TextView) findViewById(R.id.winner);

        compareAndDisplayScores(scores);

    }


    public void compareAndDisplayScores(int[] scores) {

        if (scores[0] > scores[1]) {

            winnerView.setText("Winner is " + player_names[0] + "!");

        } else if (scores[0] == scores[1]) {

            winnerView.setText("It's a draw!");

        } else {

            winnerView.setText("Winner is " + player_names[1] + "!");

        }

        scoreView.setText("Player 1: " + scores[0] + " / " + "Player 2: " + scores[1]);
    }


    public void newGame(View view) {

        Intent intent = new Intent(this, Game.class);

        jjmmSoundManager().stop();

        startActivity(intent);
        finish();

    }
}
