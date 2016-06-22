package com.example.tinkazorge.pointclickgame;

import android.media.Image;
import android.media.MediaPlayer;
import android.widget.ImageView;

/**
 * Created by tinkabel on 21-6-2016.
 */
public class AudioHandler {

    public static void checkMute(MediaPlayer sound, Boolean mute) {
        if (!mute) {
            sound.start();
        }
    }

    public static void showMuteButton (Boolean mute_clicked, ImageView sprite_mute, ImageView sprite_unmute) {
        if (!mute_clicked) {
            SpriteHandler.spriteVisible(sprite_mute);
            SpriteHandler.spriteInvisble(sprite_unmute);
        } else {
            SpriteHandler.spriteVisible(sprite_unmute);
            SpriteHandler.spriteInvisble(sprite_mute);
        }
    }
}
