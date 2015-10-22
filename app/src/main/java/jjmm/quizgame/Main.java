package jjmm.quizgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Main extends jjmmActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        jjmmFullScreen();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init the SoundManager Instance
        jjmmSoundManagerInit();

        jjmmSoundManager().initMediaPlayer(this).play("rickroll").loop();

    }


    public void startGame(View view) {

        Intent intent = new Intent(getApplication(), Game.class);

        jjmmSoundManager().stop();

        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        jjmmSoundManager().initMediaPlayer(this).play("rickroll");

    }

}
