package jjmm.quizgame;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Game extends jjmmActivity {

    int num_of_players = 2;
    //String[] questions, answers, dummy_answers;
    Player[] players;
    Quiz quiz;
    //ArrayList<String[]> QandA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        jjmmFullScreen();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        jjmmSoundManager().initMediaPlayer(this).play("gong");

        setPlayers(num_of_players);
        setPlayerLabels();

        startQuiz();

    }

    //Start the 2 player quiz
    public void startQuiz() {

        quiz = new Quiz(this, players);
        quiz.newQuiz();

    }

    public void setPlayers(int num_of_players) {

        players = new Player[num_of_players];

        for (int x = 0; x < num_of_players; x++) {

            players[x] = new Player();
            players[x].setName("Player " + ( x + 1 ));

        }

    }

    //Get all necessary labels from the xml file
    public void setPlayerLabels() {

        RelativeLayout player;

        for(int x = 0; x < players.length; x++){

            String id = "player_"+( x+1 )+"_quiz";

            player = (RelativeLayout) findViewById(getResources().getIdentifier(id,"id",getPackageName()));

            players[x].setLevelView((TextView) player.findViewById(R.id.level));
            players[x].setScoreView((TextView) player.findViewById(R.id.score));
            players[x].setQuizView((TextView) player.findViewById(R.id.quiz));
            players[x].setAnswerAView((TextView) player.findViewById(R.id.answer_1));
            players[x].setAnswerBView((TextView) player.findViewById(R.id.answer_2));
            players[x].setChoiceAView((TextView) player.findViewById(R.id.choice_1));
            players[x].setChoiceBView((TextView) player.findViewById(R.id.choice_2));
            //players[x].setTimerView((TextView) player.findViewById(R.id.timer));

        }


    }

    //Executes if player chooses option #1 in the quiz
    public void selectChoice(View view) {

        //Button btn_choice = (Button) view;


        for (int x = 0; x < players.length; x++) {

            if (players[x].getChoiceAView() == view) {

                if(quiz.isAnswerCorrect(quiz.getCurrentAnswerA())){

                    players[x].addPoint();
                    jjmmSoundManager().play("correct");

                } else {

                    players[x].deductPoint();
                    jjmmSoundManager().play("wrong");

                }

            } else if (players[x].getChoiceBView() == view) {

                if(quiz.isAnswerCorrect(quiz.getCurrentAnswerB())){

                    players[x].addPoint();
                    jjmmSoundManager().play("correct");

                } else {

                    players[x].deductPoint();
                    jjmmSoundManager().play("wrong");

                }

            }

        }

        quiz.nextQuestion();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        quiz.stopQuizTimer();

    }

    @Override
    protected void onPause() {
        super.onPause();

        quiz.stopQuizTimer();

    }


    @Override
    protected void onResume() {
        super.onResume();

        quiz.startQuizTimer();

    }
}
