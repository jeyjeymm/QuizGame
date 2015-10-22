package jjmm.quizgame;

import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Joel Jeremy on 10/18/2015.
 */
public class Timer {

    TextView[] timers;
    //TextView timer_1, timer_2, timer_3, timer_4, timer_5, timer_6, timer_7, timer_8, timer_9, timer_10;

    CountDownTimer countDownTimer;

    long duration = 7 * 1000;
    final long INTERVAL = 1 * 1000;
    static final int MAX_SECONDS = 10;

    Context context;

    public Timer(Context context, final Quiz quiz) {

        this.context = context;

        initializeTickBoxes();

        countDownTimer = new CountDownTimer(duration, INTERVAL) {

            public void onTick(long millisUntilFinished) {

                int second = (int) (millisUntilFinished / 1000);


                switch(second){

                    case 1:

                        timers[4].setVisibility(View.INVISIBLE);
                        timers[5].setVisibility(View.INVISIBLE);

                        break;

                    case 2:

                        timers[3].setVisibility(View.INVISIBLE);
                        timers[6].setVisibility(View.INVISIBLE);

                        break;

                    case 3:

                        timers[2].setVisibility(View.INVISIBLE);
                        timers[7].setVisibility(View.INVISIBLE);

                        break;

                    case 4:

                        timers[1].setVisibility(View.INVISIBLE);
                        timers[8].setVisibility(View.INVISIBLE);

                        break;

                    case 5:

                        timers[0].setVisibility(View.INVISIBLE);
                        timers[9].setVisibility(View.INVISIBLE);

                        break;

                }


                /*for(int x = 0; x < players.length; x++){

                    players[x].getTimerView().setText("" + millisUntilFinished / 1000);

                }*/

            }

            @Override
            public void onFinish() {

                quiz.nextQuestion();

            }

        };

    }

    //  Start a countdown timer
    //  @Params (duration, interval)
    //  in milliseconds
    public void start() {

        resetTickBoxes();
        this.countDownTimer.start();

    }

    public void stop() {

        resetTickBoxes();
        this.countDownTimer.cancel();

    }

    public void setDuration(long duration) {

        this.duration = duration;

    }

    public void initializeTickBoxes(){

        timers = new TextView[MAX_SECONDS];

        for (int x = 0; x < timers.length; x++) {

            String id = "timer_" + ( x + 1 );

            timers[x] = (TextView) ((Activity) context).findViewById(context.getResources().getIdentifier(id, "id", context.getPackageName()));

        }

        /*
        timer_1 = (TextView) ((Activity) context).findViewById(R.id.timer_1);
        timer_2 = (TextView) ((Activity) context).findViewById(R.id.timer_2);
        timer_3 = (TextView) ((Activity) context).findViewById(R.id.timer_3);
        timer_4 = (TextView) ((Activity) context).findViewById(R.id.timer_4);
        timer_5 = (TextView) ((Activity) context).findViewById(R.id.timer_5);
        timer_6 = (TextView) ((Activity) context).findViewById(R.id.timer_6);
        timer_7 = (TextView) ((Activity) context).findViewById(R.id.timer_7);
        timer_8 = (TextView) ((Activity) context).findViewById(R.id.timer_8);
        timer_9 = (TextView) ((Activity) context).findViewById(R.id.timer_9);
        timer_10 = (TextView) ((Activity) context).findViewById(R.id.timer_10);
        */

    }


    public void resetTickBoxes(){

        for (int x = 0; x < timers.length; x++) {

            timers[x].setVisibility(View.VISIBLE);

        }

        /*
        timer_1.setVisibility(View.VISIBLE);
        timer_2.setVisibility(View.VISIBLE);
        timer_3.setVisibility(View.VISIBLE);
        timer_4.setVisibility(View.VISIBLE);
        timer_5.setVisibility(View.VISIBLE);
        timer_6.setVisibility(View.VISIBLE);
        timer_7.setVisibility(View.VISIBLE);
        timer_8.setVisibility(View.VISIBLE);
        timer_9.setVisibility(View.VISIBLE);
        timer_10.setVisibility(View.VISIBLE);
        */

    }

}
