package com.example.tinkazorge.pointclickgame;

import android.view.View;
import android.widget.TextView;

/**
 * Created by tinkabel on 21-6-2016.
 */
public class TextHandler {
    // make text pop up and disappear again
    public static void textPopUp(final TextView text_popup, int time_popup){
        text_popup.setVisibility(View.VISIBLE);
        text_popup.postDelayed(new Runnable() {
            public void run() {
                text_popup.setVisibility(View.GONE);
            }
        }, time_popup);
    }

    static public void textVisible(final TextView text_visible){
        text_visible.setVisibility(View.VISIBLE);
    }
    static public void textInvisible(final TextView text_invisible){
        text_invisible.setVisibility(View.INVISIBLE);
    }

}
