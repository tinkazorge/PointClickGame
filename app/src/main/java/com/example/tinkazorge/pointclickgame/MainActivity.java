package com.example.tinkazorge.pointclickgame;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import javax.xml.datatype.Duration;

public class MainActivity extends AppCompatActivity {
    int dis_ap_time = 300;
    int textpop_time = 4000;
    int count_flush = 0;
    int max_right = 900;
    int max_left = 4;
    int count_mute = 0;
    boolean feather_visible = false;
    boolean eggs_clicked = false;
    boolean chicken_talked = false;
    boolean snake_ate_eggs = false;
    boolean fatchicken_2_visible = false;
    boolean solved = false;
    boolean fat_chicken_2_visible = false;
    boolean toilet_clickable;
    boolean feather_used = false;
    boolean lock_clicked = false;
    boolean mute_clicked = false;

    // define widgets
    ImageView flusher;
    ImageView snakeSprite;
    ImageView chickenSprite;
    ImageView chickenSprite_2;
    ImageView chickenSprite_3;
    ImageView leftArrow;
    ImageView rightArrow;
    ImageView fatChickenSprite;
    ImageView fatChickenSprite_2;
    ImageView fatChickenSprite_lit;
    ImageView feather;
    ImageView eggs;
    ImageView eggs_lit;
    ImageView cage;
    ImageView toilet;
    ImageView scroll;
    ImageView switch_bulb;
    ImageView bulb_on_1;
    ImageView bulb_on_2;
    ImageView bulb_on_3;
    ImageView bulb_off_1;
    ImageView bulb_off_2;
    ImageView bulb_off_3;
    ImageView mute;
    ImageView unmute;
    TextView flush_text_1;
    TextView flush_text_2;
    TextView flush_text_3;
    TextView flush_text_4;
    TextView fatchicken_text_1;
    TextView fatchicken_text_2;
    TextView fatchicken_text_3;
    TextView fatchicken_text_4;
    TextView snake_text_1;
    TextView snake_text_2;
    TextView chicken_text;
    TextView chicken_text_2;
    MediaPlayer bite_sound;
    MediaPlayer chicken_sound;
    MediaPlayer splash_sound;
    MediaPlayer flush_sound;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
        Log.d("hoi", "mute" + mute_clicked);
        // find widgets
        chickenSprite = (ImageView) findViewById(R.id.chicken_1);
        fatChickenSprite = (ImageView) findViewById(R.id.fat_chicken);
        fatChickenSprite_2 = (ImageView) findViewById(R.id.fat_chicken_2);
        fatChickenSprite_lit = (ImageView) findViewById(R.id.fat_chicken_lit);
        snakeSprite = (ImageView) findViewById(R.id.snake);
        chickenSprite_2 = (ImageView) findViewById(R.id.chicken_2);
        chickenSprite_3 = (ImageView) findViewById(R.id.chicken_3);
        flusher = (ImageView) findViewById(R.id.flush);
        feather = (ImageView) findViewById(R.id.feather_1);
        cage = (ImageView) findViewById(R.id.cage_2);
        toilet = (ImageView) findViewById(R.id.toilet);
        scroll = (ImageView) findViewById(R.id.scroll);
        switch_bulb = (ImageView) findViewById(R.id.switch_bulb);
        bulb_on_1 = (ImageView) findViewById(R.id.bulbs_on_1);
        bulb_on_2 = (ImageView) findViewById(R.id.bulbs_on_2);
        bulb_on_3 = (ImageView) findViewById(R.id.bulbs_on_3);
        bulb_off_1 = (ImageView) findViewById(R.id.bulbs_off_1);
        bulb_off_2 = (ImageView) findViewById(R.id.bulbs_off_2);
        bulb_off_3 = (ImageView) findViewById(R.id.bulbs_off_3);
        mute = (ImageView) findViewById(R.id.mute);
        unmute = (ImageView) findViewById(R.id.unmute);
        flush_text_1 = (TextView) findViewById(R.id.flush_text_1);
        flush_text_2 = (TextView) findViewById(R.id.flush_text_2);
        flush_text_3 = (TextView) findViewById(R.id.flush_text_3);
        flush_text_4 = (TextView) findViewById(R.id.flush_text_4);
        fatchicken_text_1 = (TextView) findViewById(R.id.fatchicken_text_1);
        fatchicken_text_2 = (TextView) findViewById(R.id.fatchicken_text_2);
        fatchicken_text_3 = (TextView) findViewById(R.id.fatchicken_text_3);
        fatchicken_text_4 = (TextView) findViewById(R.id.fatchicken_text_4);
        chicken_text = (TextView) findViewById(R.id.chicken_text);
        chicken_text_2 = (TextView) findViewById(R.id.chicken_text_2);
        snake_text_1 = (TextView) findViewById(R.id.snake_text_1);
        snake_text_2 = (TextView) findViewById(R.id.snake_text_2);
        rightArrow = (ImageView) findViewById(R.id.arrowright);
        leftArrow = (ImageView) findViewById(R.id.arrowleft);
        eggs = (ImageView) findViewById(R.id.eggs);
        eggs_lit = (ImageView) findViewById(R.id.eggs_lit);
        flush_sound = MediaPlayer.create(this, R.raw.flush_sound);
        splash_sound = MediaPlayer.create(this, R.raw.splash_sound);
        chicken_sound = MediaPlayer.create(this, R.raw.chicken_sound);
        bite_sound = MediaPlayer.create(this, R.raw.bite_sound);

        // make certain widgets invisible
        defaultSpriteVisibility();
        // make all texts invisible
        defaultTextVisibility();

        // when rightarrow is clicked
        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int left_margin = SpriteHandler.getLeftMargin(chickenSprite);
                if (left_margin < max_right) {
                    // call animation to make chicken walk
                    walkAnimationRight();
                }
            }
        });

        // when leftarrow is clicked
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int left_margin = SpriteHandler.getLeftMargin(chickenSprite);
                if (left_margin > max_left) {
                    // call animation to make chicken walk
                    walkAnimationLeft();
                }
            }
        });

        // if user clicks flush
        flusher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if chicken is in the right range
                if (SpriteHandler.getRange(chickenSprite, 420, 750)) {
                    // call flusherClicked
                    flusherClicked();
                }
            }
        });

        // if snakesprite is clicked
        snakeSprite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snakeClicked();
            }
        });

        // if fat chicken sprite is clicked
        fatChickenSprite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fatchickenClicked();
            }

        });

        // if user clicks chicken sprite
        chickenSprite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chickenClicked();
            }
        });

        // if user clicks scroll
        scroll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                scrollClicked();
            }
        });

        // if user clicks switch
        eggs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SpriteHandler.getRange(chickenSprite, 200, 500)) {{
                        eggs_clicked = true;
                        SpriteHandler.spriteInvisble(eggs);
                        SpriteHandler.spriteVisible(eggs_lit);
                    }
                }
            }
        });

        switch_bulb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SpriteHandler.getRange(chickenSprite, 300, 520)) {{
                    lightBulbs();
                }
            }
            }
        });
        // if mute is clicked
        mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                muteClicked();
            }
        });

        unmute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unmuteClicked();
            }
        });
    }

    // gets called when activity onreult is called
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_OK) {
            solved = true;
            SpriteHandler.spriteVisible(cage);
            SpriteHandler.spriteInvisble(fatChickenSprite);
            SpriteHandler.spriteVisible(fatChickenSprite_2);

            // if user clicks fat chicken sprite 2
            fatChickenSprite_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AudioHandler.checkMute(chicken_sound, mute_clicked);
                    if (SpriteHandler.getRange(chickenSprite, 100, 400)) {
                        fat_chicken_2_visible = true;
                        SpriteHandler.spriteInvisble(fatChickenSprite_2);
                        SpriteHandler.spriteVisible(fatChickenSprite_lit);
                        defaultTextVisibility();
                        TextHandler.textPopUp(fatchicken_text_4, textpop_time);
                    }

                    snakeSprite.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (SpriteHandler.getRange(chickenSprite, 420, 600)) {
                                if (solved) {
                                    AudioHandler.checkMute(bite_sound, mute_clicked);
                                    snakeSprite.postDelayed(new Runnable() {
                                        public void run() {
                                            SpriteHandler.spriteInvisble(fatChickenSprite_lit);
                                            defaultTextVisibility();
                                            TextHandler.textPopUp(snake_text_2, textpop_time);
                                        }
                                    }, 1000);
                                    snakeSprite.postDelayed(new Runnable() {
                                        public void run() {
                                            AudioHandler.checkMute(splash_sound, mute_clicked);
                                            SpriteHandler.spriteInvisble(snakeSprite);
                                        }
                                    }, 3000);
                                    toilet_clickable = true;
                                }
                            }
                        }
                    });
                }
            });
                toilet.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick (View v){
                        if (SpriteHandler.getRange(chickenSprite, 400, 600)){
                        if (toilet_clickable) {
                            TextHandler.textPopUp(chicken_text, textpop_time);
                            toilet.postDelayed(new Runnable() {
                                public void run() {
                                    SpriteHandler.spriteInvisble(chickenSprite);
                                    toilet.postDelayed(new Runnable() {
                                        public void run() {
                                            AudioHandler.checkMute(splash_sound, mute_clicked);
                                            // push boolean mute_clicked
                                            Intent escapeactivity = new Intent(MainActivity.this, EscapeActivity.class);
                                            escapeactivity.putExtra("mute_clicked", mute_clicked);
                                            startActivity(escapeactivity);
                                        }
                                    }, 1000);
                                }
                            }, 2000);
                        }
                    }
                }
            });
        }
        else {  lock_clicked = false;}
    }

    // makes all the textviews invisible by default
    public void defaultTextVisibility(){
        flush_text_1.setVisibility(View.INVISIBLE);
        flush_text_2.setVisibility(View.INVISIBLE);
        flush_text_3.setVisibility(View.INVISIBLE);
        flush_text_4.setVisibility(View.INVISIBLE);
        fatchicken_text_1.setVisibility(View.INVISIBLE);
        fatchicken_text_2.setVisibility(View.INVISIBLE);
        fatchicken_text_3.setVisibility(View.INVISIBLE);
        fatchicken_text_4.setVisibility(View.INVISIBLE);
        snake_text_1.setVisibility(View.INVISIBLE);
        snake_text_2.setVisibility(View.INVISIBLE);
        chicken_text.setVisibility(View.INVISIBLE);
        chicken_text_2.setVisibility(View.INVISIBLE);
    }

    public void defaultSpriteVisibility(){
        SpriteHandler.spriteInvisble(snakeSprite);
        SpriteHandler.spriteInvisble(chickenSprite_2);
        SpriteHandler.spriteInvisble(chickenSprite_3);
        SpriteHandler.spriteInvisble(feather);
        SpriteHandler.spriteInvisble(eggs);
        SpriteHandler.spriteInvisble(eggs_lit);
        SpriteHandler.spriteInvisble(fatChickenSprite_2);
        SpriteHandler.spriteInvisble(fatChickenSprite_lit);
        SpriteHandler.spriteInvisble(cage);
        SpriteHandler.spriteInvisble(bulb_on_1);
        SpriteHandler.spriteInvisble(bulb_on_2);
        SpriteHandler.spriteInvisble(bulb_on_3);
        SpriteHandler.spriteInvisble(unmute);
    }

    // makes lightbulbs turn on and off in a certain pattern
    public void lightBulbs(){
        //spriteInvisble(bulb_off_1);
        SpriteHandler.spriteDisRe(bulb_off_2, dis_ap_time);
        SpriteHandler.spriteApDis(bulb_on_2, dis_ap_time);
        bulb_on_1.postDelayed(new Runnable() {
            public void run() {
                SpriteHandler.spriteDisRe(bulb_off_1, dis_ap_time);
                SpriteHandler.spriteApDis(bulb_on_1, dis_ap_time);
            }
        }, 2000);
        bulb_on_3.postDelayed(new Runnable() {
            public void run() {
                SpriteHandler.spriteDisRe(bulb_off_3, dis_ap_time);
                SpriteHandler.spriteApDis(bulb_on_3, dis_ap_time);
            }
        }, 4000);
    }
    public void walkAnimationRight(){
        // make the sprite that stands still disappear for a second and reappear
        SpriteHandler.spriteInvisble(chickenSprite);
        SpriteHandler.spriteDisRe(chickenSprite, dis_ap_time);
        // make the Sprite that walks appear for a second and then disappear
        SpriteHandler.spriteVisible(chickenSprite_2);
        SpriteHandler.spriteApDis(chickenSprite_2, dis_ap_time);
        // update LeftMargin to make chickensprite move (by calling class ettings)
        SpriteHandler.spriteRight(chickenSprite);
        // update margins of invisible sprites
        SpriteHandler.spriteRight(chickenSprite_2);
        SpriteHandler.spriteRight(chickenSprite_3);
        SpriteHandler.spriteRight(feather);
    }
    public void walkAnimationLeft(){
        // make the sprite that stands still disappear for a second and reappear
        SpriteHandler.spriteInvisble(chickenSprite);
        SpriteHandler.spriteDisRe(chickenSprite, dis_ap_time);

        //make the sprite that walks appear for a second and then disappear
        SpriteHandler.spriteVisible(chickenSprite_3);
        SpriteHandler.spriteApDis(chickenSprite_3, dis_ap_time);

        // update leftMargin again to make chickensprite move
        SpriteHandler.spriteLeft(chickenSprite);

        // update margins of invisible sprites
        SpriteHandler.spriteLeft(chickenSprite_2);
        SpriteHandler.spriteLeft(chickenSprite_3);
        SpriteHandler.spriteLeft(feather);
    }
    public void flusherClicked(){
        // update count_flush
        count_flush++;
        // the first two times
        if (count_flush < 3) {
            // show flush text 1
            defaultTextVisibility();
            TextHandler.textPopUp(flush_text_1, textpop_time);
        }
        // the 3rd time
        if (count_flush == 3) {
            defaultTextVisibility();
            // show flush text 2
            TextHandler.textPopUp(flush_text_2, textpop_time);

        }
        // the 4th time
        if (count_flush == 4) {
            // show flush text 3
            defaultTextVisibility();
            TextHandler.textPopUp(flush_text_3, textpop_time);
        }
        // the 5th time
        if (count_flush > 4 && solved == false) {
            // make snake appear and show flush text 4
            AudioHandler.checkMute(splash_sound, mute_clicked);
            snakeSprite.postDelayed(new Runnable() {
                public void run() {
                    SpriteHandler.spriteVisible(snakeSprite);
                    defaultTextVisibility();
                    TextHandler.textPopUp(flush_text_4, textpop_time);
                }
            }, 1000);
        }
    }
    public void snakeClicked(){
        if (SpriteHandler.getRange(chickenSprite, 420, 600)&&snake_ate_eggs ==  false) {
            defaultTextVisibility();
            TextHandler.textPopUp(flush_text_4, textpop_time);
            if (eggs_clicked && snake_ate_eggs == false) {
                AudioHandler.checkMute(bite_sound, mute_clicked);
                snakeSprite.postDelayed(new Runnable() {
                    public void run() {
                        SpriteHandler.spriteInvisble(eggs_lit);
                        flush_text_4.setVisibility(View.INVISIBLE);
                        defaultTextVisibility();
                        TextHandler.textPopUp(snake_text_1, textpop_time);
                    }
                }, 700);
                snake_ate_eggs = true;
            }
        }
    }
    public void fatchickenClicked(){
        AudioHandler.checkMute(chicken_sound, mute_clicked);
        if (SpriteHandler.getRange(chickenSprite, 100, 400)) {
            chicken_talked = true;
            // if there is no feather yet
            if (feather_visible == false) {
                // show fat chicken text
                defaultTextVisibility();
                TextHandler.textPopUp(fatchicken_text_1, textpop_time);
            }

            if ((feather_visible) && (snake_ate_eggs == false)) {
                // show fat chicken text 2
                defaultTextVisibility();
                TextHandler.textPopUp(fatchicken_text_2, textpop_time);
                eggs.postDelayed(new Runnable() {
                    public void run() {
                        SpriteHandler.spriteVisible(eggs);
                        SpriteHandler.spriteInvisble(feather);
                        feather_used = true;
                    }
                }, 2000);
            }

            // if the sprite is clicked and the snake already ate the eggs
            if (snake_ate_eggs) {
                SpriteHandler.spriteInvisble(eggs);
                defaultTextVisibility();
                TextHandler.textPopUp(fatchicken_text_3, textpop_time);
                // start Lockactivity
                if (lock_clicked == false){
                    fatChickenSprite.postDelayed(new Runnable() {
                        public void run() {
                            Intent lockactivity = new Intent(MainActivity.this, LockActivity.class);
                            lockactivity.putExtra("mute_clicked", mute_clicked);
                            startActivityForResult(lockactivity, 1);
                        }
                    }, 3000);}
                lock_clicked = true;
            }
        }
    }
    public void chickenClicked(){
        if (chicken_talked && feather_used == false) {
            // show feather (chicken pulls out his feather)
            SpriteHandler.spriteVisible(feather);
            feather_visible = true;
            TextHandler.textPopUp(chicken_text_2, textpop_time);
        }
    }
    public void scrollClicked(){
        if (SpriteHandler.getRange(chickenSprite, 280, 455)) {
            Intent scrollactivity = new Intent(MainActivity.this, ScrollActivity.class);
            startActivity(scrollactivity);
        }
    }
    public void muteClicked(){
        mute_clicked = true;
        SpriteHandler.spriteInvisble(mute);
        SpriteHandler.spriteVisible(unmute);
    }
    public void unmuteClicked(){
        mute_clicked = false;
        SpriteHandler.spriteInvisble(unmute);
        SpriteHandler.spriteVisible(mute);
    }
}
