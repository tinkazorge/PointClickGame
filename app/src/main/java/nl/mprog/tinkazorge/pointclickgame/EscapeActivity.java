// Tinka Zorge PointClickGame
package nl.mprog.tinkazorge.pointclickgame;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EscapeActivity extends AppCompatActivity {

    // intitialize widgets and variables
    int textpop_time = 4000;
    int textpop_first = 5000;
    int dis_ap_time = 300;
    TextView pipe_text_1;
    TextView pipe_text_2;
    TextView pipe_text_3;
    TextView pipe_text_4;
    TextView pipe_text_5;
    ImageView chicken;
    ImageView chicken2;
    ImageView chicken3;
    ImageView chicken4;
    ImageView river;
    ImageView pipe1;
    ImageView pipe2;
    ImageView pipe3;
    ImageView pipe1_lit;
    ImageView pipe2_lit;
    ImageView pipe3_lit;
    ImageView bridge_pipe1;
    ImageView bridge_pipe2;
    ImageView bridge_pipe3;
    ImageView leftarrow;
    ImageView rightarrow;
    ImageView doorknob;
    ImageView mute;
    ImageView unmute;
    Button back_button;
    MediaPlayer lock_sound;
    MediaPlayer clank_sound;
    MediaPlayer chicken_sound;
    boolean first_part = false;
    boolean second_part = false;
    boolean bridge_build = false;
    boolean mute_clicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set orientation to landscape
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.escape_activity_layout);

        // find widgets
        pipe_text_1 = (TextView) findViewById(R.id.pipe_text_1);
        pipe_text_2 = (TextView) findViewById(R.id.pipe_text_2);
        pipe_text_3 = (TextView) findViewById(R.id.pipe_text_3);
        pipe_text_4 = (TextView) findViewById(R.id.pipe_text_4);
        pipe_text_5 = (TextView) findViewById(R.id.pipe_text_5);
        river = (ImageView) findViewById(R.id.river);
        pipe2 = (ImageView) findViewById(R.id.pipe2);
        pipe3 = (ImageView) findViewById(R.id.pipe3);
        pipe1 = (ImageView) findViewById(R.id.pipe1);
        pipe2_lit = (ImageView) findViewById(R.id.pipe2_lit);
        pipe3_lit = (ImageView) findViewById(R.id.pipe3_lit);
        pipe1_lit = (ImageView) findViewById(R.id.pipe1_lit);
        bridge_pipe1 = (ImageView) findViewById(R.id.bridgepipe1);
        bridge_pipe2 = (ImageView) findViewById(R.id.bridgepipe2);
        bridge_pipe3 = (ImageView) findViewById(R.id.bridgepipe3);
        chicken = (ImageView) findViewById(R.id.chicken1);
        chicken2 = (ImageView) findViewById(R.id.chicken2);
        chicken3 = (ImageView) findViewById(R.id.chicken3);
        chicken4 = (ImageView) findViewById(R.id.chicken4);
        leftarrow = (ImageView) findViewById(R.id.arrowleft);
        rightarrow = (ImageView) findViewById(R.id.arrowright);
        doorknob = (ImageView) findViewById(R.id.doorknob);
        mute = (ImageView) findViewById(R.id.mute);
        unmute = (ImageView) findViewById(R.id.unmute);
        back_button = (Button) findViewById(R.id.back_button);
        lock_sound = MediaPlayer.create(this, R.raw.lock_sound);
        clank_sound = MediaPlayer.create(this, R.raw.clank_sound);
        chicken_sound = MediaPlayer.create(this, R.raw.chicken_sound);

        // make texts and certain sprites invisible
        defaultSpriteVisibility();
        defaultTextVisibility();

        // make text pop up when activity opens
        TextHandler.textPopUp(pipe_text_1, textpop_first);

        // get boolean mute_clicked from mainactivity
        Bundle mutes = getIntent().getExtras();
        mute_clicked = mutes.getBoolean("mute_clicked");

        // show right button (mute or unmute)
        Boolean mute_clicked_2 = AudioHandler.showMuteButton(mute_clicked, mute, unmute);

        // if mute is clicked
        mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // call method muteClicked
                muteClicked();
            }
        });

        // if unmute is clicked
        unmute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // call method unmuteClicked
                unmuteClicked();
            }
        });

        // if chicken is clicked
        chicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // call method chickenClicked
                chickenClicked();
            }
        });

        // if pipe2 is clicked
        pipe2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pipe2Clicked();
            }
        });

        // if pipe1 is clicked
        pipe1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pipe1Clicked();
            }
        });

        // if pipe3 is clicked
        pipe3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pipe3Clicked();
            }
        });

        // if backbutton is clicked
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // go back to main
                finish();
            }
        });

        // when rightarrow is clicked
        rightarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rightArrow();
            }
        });

        // when leftarrow is clicked
        leftarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftArrow();
            }
        });

        // if doorknob is clicked
        doorknob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doorknobClicked();
            }
        });
    }

    // make text invisible
    public void defaultTextVisibility(){
        TextHandler.textInvisible(pipe_text_1);
        TextHandler.textInvisible(pipe_text_2);
        TextHandler.textInvisible(pipe_text_3);
        TextHandler.textInvisible(pipe_text_4);
        TextHandler.textInvisible(pipe_text_5);
    }

    // make certain sprites invisible
    public void defaultSpriteVisibility(){
        SpriteHandler.spriteInvisble(bridge_pipe1);
        SpriteHandler.spriteInvisble(bridge_pipe2);
        SpriteHandler.spriteInvisble(bridge_pipe3);
        SpriteHandler.spriteInvisble(chicken2);
        SpriteHandler.spriteInvisble(chicken3);
        SpriteHandler.spriteInvisble(chicken4);
        SpriteHandler.spriteInvisble(pipe1_lit);
        SpriteHandler.spriteInvisble(pipe2_lit);
        SpriteHandler.spriteInvisble(pipe3_lit);
    }

    // shows unmute buton and mutes sound
    public void muteClicked(){
        mute_clicked = true;
        SpriteHandler.spriteVisible(unmute);
        SpriteHandler.spriteInvisble(mute);
    }

    // shows mutebutton and unmutes sounds
    public void unmuteClicked(){
        mute_clicked = false;
        SpriteHandler.spriteInvisble(unmute);
        SpriteHandler.spriteVisible(mute);
    }

    // shows text when the chicken is clicked
    public void chickenClicked(){

        // if the bridge has not been build
        if (bridge_build == false) {

            // show pipetext1
            defaultTextVisibility();
            TextHandler.textPopUp(pipe_text_1, textpop_time);
        }
    }

    // lets user select the pipe and build a bridge if it's selected first
    public void pipe2Clicked(){

        // show user pipe has been selected
        SpriteHandler.spriteDisRe(pipe2, 1000);
        SpriteHandler.spriteApDis(pipe2_lit, 1000);

        // if the river is then clicked
        river.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // play clanksound
                AudioHandler.checkMute(clank_sound, mute_clicked);

                // after a second
                pipe2.postDelayed(new Runnable() {
                    public void run() {

                        // show pipetext3
                        defaultTextVisibility();
                        TextHandler.textPopUp(pipe_text_3, textpop_time);

                        // build first part of bridge
                        SpriteHandler.spriteInvisble(pipe2_lit);
                        SpriteHandler.spriteInvisble(pipe2);
                        SpriteHandler.spriteVisible(bridge_pipe2);

                        // user got the first part
                        first_part = true;
                    }
                }, 500);
            }
        });
        SpriteHandler.spriteDisRe(pipe2, 1000);
        SpriteHandler.spriteApDis(pipe2_lit, 1000);
    }

    // lets user select the pipe and build a bridge if it's selected second
    public void pipe1Clicked(){

        // show user pipe has been selected
        SpriteHandler.spriteDisRe(pipe1, 1000);
        SpriteHandler.spriteApDis(pipe1_lit, 1000);

        // if the user got the first part
        if (first_part) {
            // and then clicks the river
            river.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // play clanksound
                    AudioHandler.checkMute(clank_sound, mute_clicked);

                    // after a second
                    bridge_pipe1.postDelayed(new Runnable() {
                        public void run() {

                            // show pipetext4
                            defaultTextVisibility();
                            TextHandler.textPopUp(pipe_text_4, textpop_time);

                            // build second part of bridge
                            SpriteHandler.spriteInvisble(pipe1_lit);
                            SpriteHandler.spriteInvisble(pipe1);
                            SpriteHandler.spriteVisible(bridge_pipe1);

                            // user got second part
                            second_part = true;
                        }
                    }, 500);
                }
            });
        }
        SpriteHandler.spriteDisRe(pipe1, 1000);
        SpriteHandler.spriteApDis(pipe1_lit, 1000);
    }

    // lets user select the pipe and build a bridge if it's selected last
    public void pipe3Clicked(){

        // show user pipe has been selected
        SpriteHandler.spriteDisRe(pipe3, 1000);
        SpriteHandler.spriteApDis(pipe3_lit, 1000);

        // if user got first and second part
        if (first_part && second_part) {

            // if user then clicks river
            river.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                // play clank sound
                AudioHandler.checkMute(clank_sound, mute_clicked);

                // after a second
                bridge_pipe3.postDelayed(new Runnable() {
                    public void run() {

                        // show pipetext5
                        defaultTextVisibility();
                        TextHandler.textPopUp(pipe_text_5, textpop_time);

                        // bridge has been built
                        bridge_build = true;

                        // build bridge
                        SpriteHandler.spriteInvisble(pipe3_lit);
                        SpriteHandler.spriteInvisble(pipe3);
                        SpriteHandler.spriteVisible(bridge_pipe3);
                    }
                }, 500);
                }
            });
        }
        SpriteHandler.spriteDisRe(pipe3, 1000);
        SpriteHandler.spriteApDis(pipe3_lit, 1000);
    }

    // lets the sprite move to the right until the wall is hit
    public void rightArrow(){

        // get margin of the sprite
        int left_margin = SpriteHandler.getLeftMargin(chicken);

        // if he hasn't hit the wall yet
        if (left_margin < 610) {

            // make the sprite that stands still disappear for a second and reappear
            SpriteHandler.spriteInvisble(chicken);
            SpriteHandler.spriteDisRe(chicken4, dis_ap_time);

            // make the Sprite that walks appear for a second and then disappear
            SpriteHandler.spriteVisible(chicken2);
            SpriteHandler.spriteApDis(chicken2, dis_ap_time);

            // update LeftMargin to make chickensprite move
            SpriteHandler.spriteRight(chicken);

            // update margins of invisible sprites
            SpriteHandler.spriteRight(chicken2);
            SpriteHandler.spriteRight(chicken3);
            SpriteHandler.spriteRight(chicken4);
        }
    }

    // lets the sprite move to the left until the wall is hit
    public void leftArrow(){

        // if the bridge has been build
        if (bridge_build){

            // get margin of sprite
            int left_margin = SpriteHandler.getLeftMargin(chicken);

            // as long as the wall has not been hit
            if (left_margin > 155) {

                // make the sprite that stands still disappear for a second and reappear
                SpriteHandler.spriteInvisble(chicken4);
                SpriteHandler.spriteDisRe(chicken, dis_ap_time);

                // make the Sprite that walks appear for a second and then disappear
                SpriteHandler.spriteVisible(chicken3);
                SpriteHandler.spriteApDis(chicken3, dis_ap_time);

                // update LeftMargin to make chickensprite move
                SpriteHandler.spriteLeft(chicken);

                // update margins of invisible sprites
                SpriteHandler.spriteLeft(chicken2);
                SpriteHandler.spriteLeft(chicken3);
                SpriteHandler.spriteLeft(chicken4);
            }
        }

        // if bridge has not been build
        else{

            // get margin
            int left_margin = SpriteHandler.getLeftMargin(chicken);

            // as long as the sprite has not hit the water
            if (left_margin > 500) {

                // make the sprite that stands still disappear for a second and reappear
                SpriteHandler.spriteInvisble(chicken4);
                SpriteHandler.spriteDisRe(chicken, dis_ap_time);

                // make the Sprite that walks appear for a second and then disappear
                SpriteHandler.spriteVisible(chicken3);
                SpriteHandler.spriteApDis(chicken3, dis_ap_time);

                // update LeftMargin to make chickensprite move
                SpriteHandler.spriteLeft(chicken);

                // update margins of invisible sprites
                SpriteHandler.spriteLeft(chicken2);
                SpriteHandler.spriteLeft(chicken3);
                SpriteHandler.spriteLeft(chicken4);
            }
        }
    }

    // lets user go on to next activity
    public void doorknobClicked(){

        // if user is close enough
        if (SpriteHandler.getRange(chicken, 80, 400)){

            // play locksound
            AudioHandler.checkMute(lock_sound, mute_clicked);

            // after a second
            pipe_text_1.postDelayed(new Runnable() {
                public void run() {

                    // start new activity
                    Intent winactivity = new Intent(EscapeActivity.this, WinActivity.class);

                    // push boolean muteclicked
                    winactivity.putExtra("mute_clicked", mute_clicked);
                    startActivity(winactivity);
                }
            }, 1000);

        }
    }
}
