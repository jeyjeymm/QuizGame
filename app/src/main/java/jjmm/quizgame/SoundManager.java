package jjmm.quizgame;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

/**
 * Created by Joel Jeremy on 10/21/2015.
 */
public class SoundManager {

    private static SoundManager instance;
    private MediaPlayer player;
    private Context context;

    private boolean is_playing;
    private int current_position;

    public static void createInstance() {

        if (instance == null) {

            instance = new SoundManager();

        }

    }

    public synchronized static SoundManager getInstance() {

        return instance;

    }

    public SoundManager initMediaPlayer(Context context) {

        stop();

        this.context = context;
        this.player = new MediaPlayer();

        return this;

    }

    public SoundManager play(String file_name) {

        int id = context.getResources().getIdentifier(file_name, "raw", context.getPackageName());

        stop();

        player = MediaPlayer.create(context, id);

        startSongWhenReady();

        return this;

    }

    public SoundManager loop() {

        player.setLooping(true);

        return this;

    }


    public boolean isPlaying() {

        return this.is_playing;

    }


    public void startSongWhenReady() {

        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

                mp.start();
                is_playing = true;

            }
        });


        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {

                mp.reset();
                mp.release();
                mp = null;

                is_playing = false;

            }

        });

    }


    public void pause() {

        if (isPlaying()) {

            player.pause();
            current_position = player.getCurrentPosition();

        }

    }


    public void resume() {

        if (!isPlaying()) {

            player.seekTo(current_position);
            player.start();

        }

    }


    public void stop() {

        if (player != null) {

            if (isPlaying()) {

                player.stop();

            }

                player.reset();
                player.release();
                player = null;


        }

        is_playing = false;

    }

}
