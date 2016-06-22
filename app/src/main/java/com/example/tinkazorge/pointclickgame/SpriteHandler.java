package com.example.tinkazorge.pointclickgame;

import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by tinkabel on 20-6-2016.
 */
public class SpriteHandler {

    // makes a sprite visible
    static public void spriteVisible(final ImageView sprite_visible){
        sprite_visible.setVisibility(View.VISIBLE);
    }

    // makes a sprite invisible
    static public void spriteInvisble(final ImageView sprite_invisible) {
        sprite_invisible.setVisibility(View.INVISIBLE);
    }

    // makes a sprite move to the left
    static public void spriteLeft(final ImageView sprite_left){
        ((ViewGroup.MarginLayoutParams) sprite_left.getLayoutParams()).leftMargin -= 35;
        sprite_left.requestLayout();
    }

    // makes a sprite move to the right
    static public void spriteRight (final ImageView sprite_right){
        ((ViewGroup.MarginLayoutParams) sprite_right.getLayoutParams()).leftMargin += 35;
        sprite_right.requestLayout();
    }
    // make sprite disappear and reappear again
    static public void spriteDisRe(final ImageView sprite_disre, int time_disre) {
        spriteInvisble(sprite_disre);
        sprite_disre.postDelayed(new Runnable() {
            public void run() {
                sprite_disre.setVisibility(View.VISIBLE);
            }
        }, time_disre);
    }
    // make sprite appear, then disappear again
    static public void spriteApDis (final ImageView sprite_apdis, int time_apdis) {
        spriteVisible(sprite_apdis);
        sprite_apdis.postDelayed(new Runnable() {
            public void run() {
                sprite_apdis.setVisibility(View.GONE);
            }
        }, time_apdis);
    }

    // gets the margin of a sprite
    public static int getLeftMargin(final ImageView sprite_margin){
        RelativeLayout.MarginLayoutParams params = (RelativeLayout.LayoutParams) sprite_margin.getLayoutParams();
        sprite_margin.setLayoutParams(params);
        return params.leftMargin;
    }
    // returns true if sprite is in a certain range
    public static boolean getRange (ImageView walking_sprite, int min, int max) {
        int left_margin = getLeftMargin(walking_sprite);
        if ((left_margin > min) && (left_margin < max)) {
            return true;
        }
        return false;
    }
}

