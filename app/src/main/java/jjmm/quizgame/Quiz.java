package jjmm.quizgame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Joel Jeremy on 10/19/2015.
 */
public class Quiz {

    Player[] players;
    Timer timer;

    String[] questionsForCurrentQuiz, answersForCurrentQuiz, dummyAnswersForCurrentQuiz;
    ArrayList<String[]> QandA, RandomizedQandA;

    int current_quiz_item = 0;
    String answerA, answerB;

    static final int MAX_LEVELS = 20;
    static final int ITEMS_PER_LEVEL = 2;
    static final int ITEMS_PER_QUIZ = MAX_LEVELS * ITEMS_PER_LEVEL;

    int[] levels;

    Context context;

    public Quiz(Context context, Player[] players) {

        this.context = context;
        this.players = players;

        QandA = getQuestionAndAnswersFromXML();

        //Instantiate the Timer class
        //Pass the players to the timer to let the Timer class have access to the players timer views
        //Pass the quiz class to the timer to let the Timer have the access to the nextQuestion method
        timer = new Timer(this.context, this);

    }

    public void newQuiz() {

        current_quiz_item = 0;
        //checkGameLevel();
        resetPlayerScores();

        RandomizedQandA = generateRandomizeQuestions(QandA);
        questionsForCurrentQuiz = RandomizedQandA.get(0);
        answersForCurrentQuiz = RandomizedQandA.get(1);
        dummyAnswersForCurrentQuiz = RandomizedQandA.get(2);

        displayQuizQuestion(current_quiz_item);
        displayQuizAnswers(current_quiz_item);
        displayLevel(current_quiz_item);

        timer.start();

    }


    public ArrayList getQuestionAndAnswersFromXML(){

        ArrayList<String[]> QandA = new ArrayList<>();
        QandA.add(context.getResources().getStringArray(R.array.questions));
        QandA.add(context.getResources().getStringArray(R.array.answers));
        QandA.add(context.getResources().getStringArray(R.array.dummy_answers));

        return QandA;

    }


    public ArrayList<String[]> generateRandomizeQuestions(ArrayList<String[]> QandA) {

        String[] questions = new String[ITEMS_PER_QUIZ];
        String[] answers = new String[ITEMS_PER_QUIZ];
        String[] dummies = new String[ITEMS_PER_QUIZ];
        levels = new int[ITEMS_PER_QUIZ];

        int possible_items_per_round = (QandA.get(0).length) / MAX_LEVELS;
        int search_start;
        int search_end;
        int range;

        int item_count = 0;


        Random random = new Random();

        for (int level = 1; level <= MAX_LEVELS; level++) {

            if (level == 1) {

                item_count = 0;
                search_start = 0;

            } else {

                item_count = (level - 1) * ITEMS_PER_LEVEL;
                search_start = (level - 1) * possible_items_per_round;
            }

            search_end = search_start + possible_items_per_round;
            range = search_end - search_start;

            for (int x = item_count; x < ITEMS_PER_LEVEL + item_count; x++) {

                int random_number = random.nextInt(range) + search_start;

                questions[x] = QandA.get(0)[random_number];
                answers[x] = QandA.get(1)[random_number];
                dummies[x] = QandA.get(2)[random_number];

                //Store levels in integer array
                levels[x] = level;

            }

        }

        ArrayList<String[]> tempQandA = new ArrayList<>();
        tempQandA.add(questions);
        tempQandA.add(answers);
        tempQandA.add(dummies);

        return tempQandA;

    }


    public void resetPlayerScores() {

        for (int x = 0; x < players.length; x++) {

            players[x].resetScore();

        }

    }


    public void displayQuizQuestion(int quiz_item) {

        for (int x = 0; x < players.length; x++) {

            players[x].getQuizView().setText(questionsForCurrentQuiz[quiz_item]);

        }

    }


    public void displayQuizAnswers(int quiz_item) {

        Random random = new Random();


        if (random.nextInt(2) == 1) {

            answerA = answersForCurrentQuiz[quiz_item];
            answerB = dummyAnswersForCurrentQuiz[quiz_item];

        } else {

            answerA = dummyAnswersForCurrentQuiz[quiz_item];
            answerB = answersForCurrentQuiz[quiz_item];

        }

        for (int x = 0; x < players.length; x++) {

            players[x].getAnswerAView().setText("A. " + answerA);
            players[x].getAnswerBView().setText("B. " + answerB);

        }

    }


    public void displayLevel(int quiz_item){

        for (int x = 0; x < players.length; x++) {

            players[x].getLevelView().setText("Level " + levels[quiz_item]);

        }

    }


    public String getCurrentAnswerA() {

        return this.answerA;

    }


    public String getCurrentAnswerB() {

        return this.answerB;

    }


    public void nextQuestion() {

        current_quiz_item++;

        //displayLevel(levels);

        if (current_quiz_item < ITEMS_PER_QUIZ) {

            displayQuizQuestion(current_quiz_item);
            displayQuizAnswers(current_quiz_item);
            displayLevel(current_quiz_item);

            timer.start();

        } else {

            timer.stop();

            int[] scores = new int[players.length];
            String[] player_names = new String[players.length];

            for (int x = 0; x < players.length; x++) {

                scores[x] = players[x].getScore();
                player_names[x] = players[x].getName();

            }

            //Show results
            Intent intent = new Intent(context, Result.class);

            intent.putExtra("scores", scores);
            intent.putExtra("player_names", player_names);

            context.startActivity(intent);
            ((Activity) context).finish();

        }

    }


    public void startQuizTimer(){

        timer.start();

    }


    public void stopQuizTimer(){

        timer.stop();

    }


    /*public void displayLevel(int[] levels){

        for(int x = 0; x < players.length; x++){

            players[x].getLevelView().setText("Level " + levels[Math.round(current_quiz_item / 2)]);

        }

    }*/


    public boolean isAnswerCorrect(String answer) {

        if (answer == answersForCurrentQuiz[current_quiz_item]) {

            return true;

        } else {

            return false;

        }

    }

}
