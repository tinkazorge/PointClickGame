// Tinka Zorge PointClickGame
package nl.mprog.tinkazorge.pointclickgame;

import android.media.MediaPlayer;
import android.widget.ImageView;

/**
 * This class handles the functions that are related to the audio in the Activities.
 */
public class AudioHandler {

    // checks if mute has been clicked, then plays sound
    public static void checkMute(MediaPlayer sound, Boolean mute) {
        if (!mute) {
            sound.start();
        }
    }

    // shows mute or unmute button
    public static boolean showMuteButton (Boolean mute_clicked, ImageView sprite_mute, ImageView sprite_unmute) {
        if (!mute_clicked) {
            SpriteHandler.spriteVisible(sprite_mute);
            SpriteHandler.spriteInvisble(sprite_unmute);
        } else {
            SpriteHandler.spriteVisible(sprite_unmute);
            SpriteHandler.spriteInvisble(sprite_mute);
        }
        return mute_clicked;
    }
}
