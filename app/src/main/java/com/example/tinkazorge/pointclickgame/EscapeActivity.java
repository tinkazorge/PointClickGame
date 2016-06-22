package com.example.tinkazorge.pointclickgame;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EscapeActivity extends AppCompatActivity {
    int textpop_time = 4000;
    int textpop_first = 5000;
    int dis_ap_time = 600;
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
    ImageView pipe4;
    ImageView pipe5;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.escape_activity_layout);
        pipe_text_1 = (TextView) findViewById(R.id.pipe_text_1);
        pipe_text_2 = (TextView) findViewById(R.id.pipe_text_2);
        pipe_text_3 = (TextView) findViewById(R.id.pipe_text_3);
        pipe_text_4 = (TextView) findViewById(R.id.pipe_text_4);
        pipe_text_5 = (TextView) findViewById(R.id.pipe_text_5);
        river = (ImageView) findViewById(R.id.river);
        //pipe1 = (ImageView) findViewById(R.id.pipe1);
        pipe2 = (ImageView) findViewById(R.id.pipe2);
        pipe3 = (ImageView) findViewById(R.id.pipe3);
        pipe1 = (ImageView) findViewById(R.id.pipe1);
        //pipe5 = (ImageView) findViewById(R.id.pipe5);
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

        defaultSpriteVisibility();
        defaultTextVisibility();
        TextHandler.textPopUp(pipe_text_1, textpop_first);

        // get boolean mute_clicked from mainactivity
        Bundle mutes = getIntent().getExtras();
        final Boolean mute_clicked = mutes.getBoolean("mute_clicked");

        AudioHandler.showMuteButton(mute_clicked, mute, unmute);

        chicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bridge_build == false) {
                    AudioHandler.checkMute(chicken_sound, mute_clicked);
                    defaultTextVisibility();
                    TextHandler.textPopUp(pipe_text_1, textpop_time);
                }
            }
        });

        pipe2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                river.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AudioHandler.checkMute(clank_sound, mute_clicked);
                        pipe2.postDelayed(new Runnable() {
                            public void run() {
                                defaultTextVisibility();
                                TextHandler.textPopUp(pipe_text_3, textpop_time);
                                SpriteHandler.spriteInvisble(pipe2);
                                SpriteHandler.spriteVisible(bridge_pipe2);
                                first_part = true;
                            }
                        }, 500);
                    }
                });
            }
        });

        pipe1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (first_part) {
                    river.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AudioHandler.checkMute(clank_sound, mute_clicked);
                            pipe1.postDelayed(new Runnable() {
                                public void run() {
                                    defaultTextVisibility();
                                    TextHandler.textPopUp(pipe_text_4, textpop_time);
                                    SpriteHandler.spriteInvisble(pipe1);
                                    SpriteHandler.spriteVisible(bridge_pipe1);
                                    second_part = true;
                                }
                            }, 500);
                        }
                    });
                }
            }
        });

        pipe3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (first_part && second_part) {
                    river.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AudioHandler.checkMute(clank_sound, mute_clicked);
                            pipe3.postDelayed(new Runnable() {
                                public void run() {
                                    defaultTextVisibility();
                                    TextHandler.textPopUp(pipe_text_5, textpop_time);
                                    bridge_build = true;
                                    SpriteHandler.spriteInvisble(pipe3);
                                    SpriteHandler.spriteVisible(bridge_pipe3);
                                }
                            }, 500);
                        }
                    });
                }
            }

        });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                SpriteHandler.spriteVisible(chicken);
            }
        });

        // when rightarrow is clicked
        rightarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int left_margin = SpriteHandler.getLeftMargin(chicken);
                if (left_margin < 610) {
                    // make the sprite that stands still disappear for a second and reappear
                    SpriteHandler.spriteInvisble(chicken);
                    SpriteHandler.spriteDisRe(chicken, dis_ap_time);
                    // make the Sprite that walks appear for a second and then disappear
                    SpriteHandler.spriteVisible(chicken2);
                    SpriteHandler.spriteApDis(chicken2, dis_ap_time);
                    // update LeftMargin to make chickensprite move
                    SpriteHandler.spriteRight(chicken);
                    // update margins of invisible sprites
                    SpriteHandler.spriteRight(chicken2);
                    SpriteHandler.spriteRight(chicken3);
                }
            }
        });

        // when leftarrow is clicked
        leftarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bridge_build){
                    int left_margin = SpriteHandler.getLeftMargin(chicken);
                    if (left_margin > 155) {
                        // make the sprite that stands still disappear for a second and reappear
                        SpriteHandler.spriteInvisble(chicken);
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
                else{
                    int left_margin = SpriteHandler.getLeftMargin(chicken);
                    if (left_margin > 500) {
                        // make the sprite that stands still disappear for a second and reappear
                        SpriteHandler.spriteInvisble(chicken);
                        SpriteHandler.spriteDisRe(chicken, dis_ap_time);
                        // make the Sprite that walks appear for a second and then disappear
                        SpriteHandler.spriteVisible(chicken3);
                        SpriteHandler.spriteApDis(chicken3, dis_ap_time);
                        // update LeftMargin to make chickensprite move
                        SpriteHandler.spriteLeft(chicken);
                        // update margins of invisible sprites
                        SpriteHandler.spriteLeft(chicken2);
                        SpriteHandler.spriteLeft(chicken3);
                    }
                }
            }
            });
        doorknob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SpriteHandler.getRange(chicken, 160, 400)){
                    AudioHandler.checkMute(lock_sound, mute_clicked);
                    pipe_text_1.postDelayed(new Runnable() {
                        public void run() {
                            Intent winactivity = new Intent(EscapeActivity.this, WinActivity.class);
                            winactivity.putExtra("mute_clicked", mute_clicked);
                            startActivity(winactivity);
                        }
                    }, 1000);

                }
            }
        });
    }

    public void defaultTextVisibility(){
        pipe_text_1.setVisibility(View.INVISIBLE);
        pipe_text_2.setVisibility(View.INVISIBLE);
        pipe_text_3.setVisibility(View.INVISIBLE);
        pipe_text_4.setVisibility(View.INVISIBLE);
        pipe_text_5.setVisibility(View.INVISIBLE);
    }
    public void defaultSpriteVisibility(){
        SpriteHandler.spriteInvisble(bridge_pipe1);
        SpriteHandler.spriteInvisble(bridge_pipe2);
        SpriteHandler.spriteInvisble(bridge_pipe3);
        SpriteHandler.spriteInvisble(chicken2);
        SpriteHandler.spriteInvisble(chicken3);
        SpriteHandler.spriteInvisble(chicken4);
    }
}
