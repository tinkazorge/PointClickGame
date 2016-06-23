// Tinka Zorge PointClickGame
package nl.mprog.tinkazorge.pointclickgame;

import android.view.View;
import android.widget.TextView;

/**
 * This class handles the functions that are related to the texts in the Activities.
 */
public class TextHandler {

    // makes text pop up and disappear again
    public static void textPopUp(final TextView text_popup, int time_popup){
        text_popup.setVisibility(View.VISIBLE);
        text_popup.postDelayed(new Runnable() {
            public void run() {
                text_popup.setVisibility(View.GONE);
            }
        }, time_popup);
    }

    // makes text visible
    static public void textVisible(final TextView text_visible){
        text_visible.setVisibility(View.VISIBLE);
    }

    // makes text invisible
    static public void textInvisible(final TextView text_invisible){
        text_invisible.setVisibility(View.INVISIBLE);
    }

}
