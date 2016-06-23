// Tinka Zorge PointClickGame
package nl.mprog.tinkazorge.pointclickgame;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LockActivity extends AppCompatActivity {

    // initialize widgets
    EditText code_text;
    TextView explain_text;
    TextView win_text;
    TextView lose_text;
    Button go_button;
    Button back_button;
    MediaPlayer lock_sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set screen to landscape
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_lock);

        // find widgets by id
        code_text = (EditText) findViewById(R.id.code_text);
        explain_text = (TextView) findViewById(R.id.explain_text);
        win_text = (TextView) findViewById(R.id.win_text);
        lose_text = (TextView) findViewById(R.id.lose_text);
        go_button = (Button) findViewById(R.id.go_button);
        back_button = (Button) findViewById(R.id.back_button);
        lock_sound = MediaPlayer.create(this, R.raw.lock_sound);

        // make certain widgets invisible
        defaultTextVisibility();

        // get boolean from main
        Bundle mutes = getIntent().getExtras();
        final Boolean mute_clicked = mutes.getBoolean("mute_clicked");

        // if gobutton is clicked
        go_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goButtonClicked(mute_clicked);
            }
        });

        // if backbutton is pressed, go back to main
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // default visibility of certain text
    public void defaultTextVisibility(){
        TextHandler.textInvisible(win_text);
        TextHandler.textInvisible(lose_text);
    }

    // checks answer when gobutton is clicked and sends a resultCode to main
    public void goButtonClicked(boolean mute){

        // get user input
        String code = code_text.getText().toString();

        // if the user puts 87 for an answer
        if (code.contains("87")) {

            // show win_text
            TextHandler.textInvisible(explain_text);
            TextHandler.textVisible(win_text);
            TextHandler.textInvisible(code_text);

            // hide go and back button
            go_button.setVisibility(View.INVISIBLE);
            back_button.setVisibility(View.INVISIBLE);

            // play lock_sound
            AudioHandler.checkMute(lock_sound, mute);

            // wait a second so win_text can be read
            win_text.postDelayed(new Runnable() {
                public void run() {

                    // define intent
                    Intent mainactivityFromLock = new Intent(LockActivity.this, MainActivity.class);

                    // set result for setAcivityForResult
                    setResult(LockActivity.RESULT_OK, mainactivityFromLock);

                    // go back to main
                    finish();
                }
            }, 2000);

        } else {

            // show lose_text and then explain_text again
            TextHandler.textInvisible(explain_text);
            TextHandler.textPopUp(lose_text, 3000);
            explain_text.postDelayed(new Runnable() {
                public void run() {
                    TextHandler.textVisible(explain_text);
                }
            }, 3100);
        }
    }
}

