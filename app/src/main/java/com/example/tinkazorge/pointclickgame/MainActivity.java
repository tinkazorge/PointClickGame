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
    int move_space = 35;
    int textpop_time = 3000;
    int count_flush = 0;
    int max_right = 900;
    int max_left = 4;
    boolean feather_visible = false;
    boolean eggs_visible = false;
    boolean chicken_talked = false;
    boolean snake_ate_eggs = false;
    boolean fatchicken_2_visible = false;
    boolean solved = false;
    boolean fat_chicken_2_visible = false;
    boolean toilet_clickable;
    boolean feather_used = false;
    boolean lock_clicked = false;

    // define widgets
    ImageView flusher;
    ImageView snakeSprite;
    ImageView snakesprite_2;
    ImageView snakesprite_3;
    ImageView chickenSprite;
    ImageView chickenSprite_2;
    ImageView chickenSprite_3;
    ImageView leftArrow;
    ImageView rightArrow;
    ImageView fatChickenSprite;
    ImageView fatChickenSprite_2;
    ImageView feather;
    ImageView eggs;
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
    MediaPlayer bite_sound;
    MediaPlayer chicken_sound;
    MediaPlayer clank_sound;
    MediaPlayer splash_sound;
    MediaPlayer flush_sound;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        // find widgets
        chickenSprite = (ImageView) findViewById(R.id.chicken_1);
        fatChickenSprite = (ImageView) findViewById(R.id.fat_chicken);
        fatChickenSprite_2 = (ImageView) findViewById(R.id.fat_chicken_2);
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
        flush_text_1 = (TextView) findViewById(R.id.flush_text_1);
        flush_text_2 = (TextView) findViewById(R.id.flush_text_2);
        flush_text_3 = (TextView) findViewById(R.id.flush_text_3);
        flush_text_4 = (TextView) findViewById(R.id.flush_text_4);
        fatchicken_text_1 = (TextView) findViewById(R.id.fatchicken_text_1);
        fatchicken_text_2 = (TextView) findViewById(R.id.fatchicken_text_2);
        fatchicken_text_3 = (TextView) findViewById(R.id.fatchicken_text_3);
        fatchicken_text_4 = (TextView) findViewById(R.id.fatchicken_text_4);
        chicken_text = (TextView) findViewById(R.id.chicken_text);
        snake_text_1 = (TextView) findViewById(R.id.snake_text_1);
        snake_text_2 = (TextView) findViewById(R.id.snake_text_2);
        rightArrow = (ImageView) findViewById(R.id.arrowright);
        leftArrow = (ImageView) findViewById(R.id.arrowleft);
        eggs = (ImageView) findViewById(R.id.eggs);
        bite_sound = MediaPlayer.create(this, R.raw.bite_sound);
        bite_sound = MediaPlayer.create(this, R.raw.bite_sound);
        bite_sound = MediaPlayer.create(this, R.raw.bite_sound);
        bite_sound = MediaPlayer.create(this, R.raw.bite_sound);
        bite_sound = MediaPlayer.create(this, R.raw.bite_sound);

        // make certain widgets invisible
        spriteInvisble(snakeSprite);
        spriteInvisble(chickenSprite_2);
        spriteInvisble(chickenSprite_3);
        spriteInvisble(feather);
        spriteInvisble(eggs);
        spriteInvisble(fatChickenSprite_2);
        spriteInvisble(cage);
        spriteInvisble(bulb_on_1);
        spriteInvisble(bulb_on_2);
        spriteInvisble(bulb_on_3);

        // make all texts invisible
        defaultTextVisibility();

        // when rightarrow is clicked
        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int left_margin = getLeftMargin(chickenSprite);
                if (left_margin < max_right) {
                    // make the sprite that stands still disappear for a second and reappear
                    spriteInvisble(chickenSprite);
                    spriteDisRe(chickenSprite, dis_ap_time);
                    // make the Sprite that walks appear for a second and then disappear
                    spriteVisible(chickenSprite_2);
                    spriteApDis(chickenSprite_2, dis_ap_time);
                    // update LeftMargin to make chickensprite move
                    spriteRight(chickenSprite);
                    // update margins of invisible sprites
                    spriteRight(chickenSprite_2);
                    spriteRight(chickenSprite_3);
                    spriteRight(feather);
                }
            }
        });

        // when leftarrow is clicked
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int left_margin = getLeftMargin(chickenSprite);
                if (left_margin > max_left) {
                    // make the sprite that stands still disappear for a second and reappear
                    spriteInvisble(chickenSprite);
                    spriteDisRe(chickenSprite, dis_ap_time);

                    //make the sprite that walks appear for a second and then disappear
                    spriteVisible(chickenSprite_3);
                    spriteApDis(chickenSprite_3, dis_ap_time);

                    // update leftMargin again to make chickensprite move
                    spriteLeft(chickenSprite);

                    // update margins of invisible sprites
                    spriteLeft(chickenSprite_2);
                    spriteLeft(chickenSprite_3);
                    spriteLeft(feather);
                }
            }
        });

        // if user clicks flush
        flusher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getRange(flusher, 420, 750)) {
                    // update count_flush
                    count_flush++;
                    // the first two times
                    if (count_flush < 3) {
                        // show flush text 1
                        defaultTextVisibility();
                        textPopUp(flush_text_1);
                    }
                    // the 3rd time
                    if (count_flush == 3) {
                        defaultTextVisibility();
                        // show flush text 2
                        textPopUp(flush_text_2);

                    }
                    // the 4th time
                    if (count_flush == 4) {
                        // show flush text 3
                        defaultTextVisibility();
                        textPopUp(flush_text_3);
                    }
                    // the 5th time
                    if (count_flush > 4 && solved == false) {
                        // make snake appear and show flush text 4
                        spriteVisible(snakeSprite);
                        defaultTextVisibility();
                        textPopUp(flush_text_4);
                    }
                }
            }
        });

        // if snakesprite is clicked
        snakeSprite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getRange(snakeSprite, 420, 600)) {
                    defaultTextVisibility();
                    textPopUp(flush_text_4);
                    if (eggs_visible && snake_ate_eggs == false) {
                        bite_sound.start();
                        snakeSprite.postDelayed(new Runnable() {
                            public void run() {
                            spriteInvisble(eggs);
                            flush_text_4.setVisibility(View.INVISIBLE);
                            defaultTextVisibility();
                            textPopUp(snake_text_1);
                            }
                        }, 700);
                        snake_ate_eggs = true;
                    }
                }
            }
        });

        // if fat chicken sprite is clicked
        fatChickenSprite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getRange(fatChickenSprite, 100, 400)) {
                    chicken_talked = true;
                    // if there is no feather yet
                    if (feather_visible == false) {
                        // show fat chicken text
                        defaultTextVisibility();
                        textPopUp(fatchicken_text_1);
                    }

                    if ((feather_visible) && (snake_ate_eggs == false)) {
                        // show fat chicken text 2
                        defaultTextVisibility();
                        textPopUp(fatchicken_text_2);
                        eggs.postDelayed(new Runnable() {
                            public void run() {
                                spriteVisible(eggs);
                                eggs_visible = true;
                                spriteInvisble(feather);
                                feather_used = true;
                            }
                        }, 2000);
                    }

                    // if the sprite is clicked and the snake already ate the eggs
                    if (snake_ate_eggs) {
                        spriteInvisble(eggs);
                        defaultTextVisibility();
                        textPopUp(fatchicken_text_3);
                        // start Lockactivity
                        if (lock_clicked == false){
                        fatChickenSprite.postDelayed(new Runnable() {
                            public void run() {
                                Intent lockactivity = new Intent(MainActivity.this, LockActivity.class);
                                startActivityForResult(lockactivity, 1);
                            }
                        }, 3000);}
                        lock_clicked = true;
                    }
                }
            }

        });

        // if user clicks chicken sprite
        chickenSprite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chicken_talked && feather_used == false) {
                    // show feather (chicken pulls out his feather)
                    feather.setVisibility(View.VISIBLE);
                    feather_visible = true;
                }
            }
        });

        // if user clicks scroll
        scroll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (getRange(scroll, 280, 455)) {
                    Intent scrollactivity = new Intent(MainActivity.this, ScrollActivity.class);
                    startActivity(scrollactivity);
                }
            }
        });

        // if user clicks switch
        switch_bulb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getRange(switch_bulb, 400, 460)) {{
                        lightbulbs();
                    }
                }
            }
        });
    }
    // make text pop up and disappear again
    public void textPopUp(final TextView text){
            text.setVisibility(View.VISIBLE);
            text.postDelayed(new Runnable() {
                public void run() {
                    text.setVisibility(View.GONE);
                }
            }, textpop_time);
        }

    // makes a sprite visible
    public void spriteVisible(final ImageView sprite){
        sprite.setVisibility(View.VISIBLE);
    }

    // makes a sprite invisible
    public void spriteInvisble(final ImageView sprite) {
        sprite.setVisibility(View.INVISIBLE);
    }

    // makes a sprite move to the left
    public void spriteLeft(final ImageView sprite){
        ((ViewGroup.MarginLayoutParams) sprite.getLayoutParams()).leftMargin -= move_space;
        sprite.requestLayout();
    }

    // makes a sprite move to the right
    public void spriteRight (final ImageView sprite){
        ((ViewGroup.MarginLayoutParams) sprite.getLayoutParams()).leftMargin += move_space;
        sprite.requestLayout();
    }
    // make sprite disappear and reappear again
    public void spriteDisRe(final ImageView sprite, int time) {
        spriteInvisble(sprite);
        sprite.postDelayed(new Runnable() {
            public void run() {
                sprite.setVisibility(View.VISIBLE);
            }
        }, time);
    }
    // make sprite appear, then disappear again
    public void spriteApDis (final ImageView sprite, int time) {
        spriteVisible(sprite);
        sprite.postDelayed(new Runnable() {
            public void run() {
                sprite.setVisibility(View.GONE);
            }
        }, time);
    }

    // makes a text pop up long and disappear after awhile
    public void textPopUpLong (final TextView text){
        text.setVisibility(View.VISIBLE);
        text.postDelayed(new Runnable() {
            public void run() {
                text.setVisibility(View.GONE);
            }
        }, 4000);
    }

    // gets called when activity onreult is called
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_OK) {
            solved = true;
            spriteVisible(cage);
            spriteInvisble(fatChickenSprite);
            spriteVisible(fatChickenSprite_2);

            // if user clicks fat chicken sprite 2
            fatChickenSprite_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getRange(fatChickenSprite_2, 100,400)) {
                        fat_chicken_2_visible = true;
                        defaultTextVisibility();
                        textPopUp(fatchicken_text_4);
                    }

                    snakeSprite.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (getRange(snakeSprite, 420, 600)) {
                                if (solved) {
                                    bite_sound.start();
                                    snakeSprite.postDelayed(new Runnable() {
                                        public void run() {
                                            spriteInvisble(fatChickenSprite_2);
                                            defaultTextVisibility();
                                            textPopUp(snake_text_2);
                                        }
                                    }, 1000);

                                    snakeSprite.postDelayed(new Runnable() {
                                        public void run() {
                                            spriteInvisble(snakeSprite);
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
                        if (getRange(toilet, 400, 600)){
                        if (toilet_clickable) {
                            textPopUp(chicken_text);
                            toilet.postDelayed(new Runnable() {
                                public void run() {
                                    Intent pipeactivity = new Intent(MainActivity.this, PipeActivity.class);
                                    startActivity(pipeactivity);
                                }
                            }, 2000);
                        }
                    }
                }
            });
        }
        else {  lock_clicked = false;}
    }

    // gets the margin of a sprite
    public int getLeftMargin(final ImageView sprite){
        RelativeLayout.MarginLayoutParams params = (RelativeLayout.LayoutParams) sprite.getLayoutParams();
        sprite.setLayoutParams(params);
        Log.e("hoi", "margin" + params.leftMargin);
        return params.leftMargin;
    }
    // returns true if sprite is in a certain range
    public boolean getRange (ImageView sprite, int min, int max){
        int left_margin = getLeftMargin(chickenSprite);
        if ((left_margin > min) && (left_margin < max)){
            return true;
        }
        return false;
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
    }

    // makes lightbulbs turn on and off in a certain pattern
    public void lightbulbs(){
        //spriteInvisble(bulb_off_1);
        spriteDisRe(bulb_off_2, dis_ap_time);
        spriteApDis(bulb_on_2, dis_ap_time);
        bulb_on_1.postDelayed(new Runnable() {
            public void run() {
                spriteDisRe(bulb_off_1, dis_ap_time);
                spriteApDis(bulb_on_1, dis_ap_time);
            }
        }, 2000);
        bulb_on_3.postDelayed(new Runnable() {
            public void run() {
                spriteDisRe(bulb_off_3, dis_ap_time);
                spriteApDis(bulb_on_3, dis_ap_time);
            }
        }, 4000);
    }
}
