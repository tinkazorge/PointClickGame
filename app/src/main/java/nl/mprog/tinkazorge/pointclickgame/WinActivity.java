// Tinka Zorge PointClickGame
package nl.mprog.tinkazorge.pointclickgame;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WinActivity extends AppCompatActivity {
    // initialize widgets
    Button again_button;
    MediaPlayer wand_sound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // set screen to landscape
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        // find widgets by id
        again_button = (Button) findViewById(R.id.again_button);
        wand_sound = MediaPlayer.create(this, R.raw.wand_sound);

        // get boolean mute_clicked from last activity
        Bundle mutes = getIntent().getExtras();
        final Boolean mute_clicked = mutes.getBoolean("mute_clicked");

        // play wandsound
        AudioHandler.checkMute(wand_sound, mute_clicked);

        // if againbutton is clicked
        again_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // restart main
                Intent mainactivity_from_win = new Intent (WinActivity.this, MainActivity.class);
                startActivity(mainactivity_from_win);
            }
        });
    }
}
