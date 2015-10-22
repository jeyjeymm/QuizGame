package jjmm.quizgame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Joel Jeremy on 10/21/2015.
 */
class jjmmActivity extends AppCompatActivity {

    private SoundManager soundManager;

    public void jjmmFullScreen() {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }


    public void jjmmSoundManagerInit() {

        soundManager.createInstance();

    }


    public SoundManager jjmmSoundManager(){

        return soundManager.getInstance();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Stop sound player on app destroy
        jjmmSoundManager().stop();
    }
}