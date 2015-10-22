package jjmm.quizgame;

import android.widget.TextView;

/**
 * Created by Joel Jeremy on 10/19/2015.
 */
public class Player {

    TextView levelView, scoreView, timerView, quizView, answer1View, answer2View, choice1View, choice2View;
    int score = 0;

    String name;


    public void setName(String name) {

        this.name = name;

    }


    public String getName(){

        return this.name;

    }


    public void setScore(int score) {

        this.score = score;
        displayScore();

    }


    public int getScore() {

        return this.score;

    }


    public void addPoint() {

        this.score++;
        displayScore();

    }


    public void deductPoint() {

        this.score--;
        displayScore();

    }


    public void resetScore() {

        this.score = 0;

    }


    public void displayScore() {

        this.scoreView.setText("Your score is " + this.score);

    }


    public void setLevelView(TextView levelView){

        this.levelView = levelView;

    }


    public TextView getLevelView(){

        return this.levelView;

    }


    public void setScoreView(TextView scoreView) {

        this.scoreView = scoreView;
        displayScore();

    }


    public TextView getScoreView() {

        return this.scoreView;

    }

    /*
    public void setTimerView(TextView timerView) {

        this.timerView = timerView;

    }


    public TextView getTimerView() {

        return this.timerView;

    }
    */


    public void setQuizView(TextView quizView) {

        this.quizView = quizView;

    }


    public TextView getQuizView() {

        return this.quizView;

    }


    public void setAnswerAView(TextView answer1View) {

        this.answer1View = answer1View;

    }


    public TextView getAnswerAView() {

        return this.answer1View;

    }


    public void setAnswerBView(TextView answer2View) {

        this.answer2View = answer2View;

    }


    public TextView getAnswerBView() {

        return this.answer2View;

    }


    public void setChoiceAView(TextView choice1View) {

        this.choice1View = choice1View;

    }


    public TextView getChoiceAView() {

        return this.choice1View;

    }

    public void setChoiceBView(TextView choice2View) {

        this.choice2View = choice2View;

    }

    public TextView getChoiceBView() {

        return this.choice2View;

    }

}
