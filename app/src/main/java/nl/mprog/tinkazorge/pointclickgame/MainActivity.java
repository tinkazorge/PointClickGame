// Tinka Zorge PointClickGame
package nl.mprog.tinkazorge.pointclickgame;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // initialize variables and widgets
    int dis_ap_time = 300;
    int textpop_time = 4000;
    int count_flush = 0;
    int max_right = 900;
    int max_left = 4;
    boolean feather_visible = false;
    boolean eggs_clicked = false;
    boolean chicken_talked = false;
    boolean snake_ate_eggs = false;
    boolean solved = false;
    boolean toilet_clickable;
    boolean feather_used = false;
    boolean lock_clicked = false;
    boolean mute_clicked = false;

    // find widgets
    ImageView flusher;
    ImageView snakeSprite;
    ImageView chickenSprite;
    ImageView chickenSprite_2;
    ImageView chickenSprite_3;
    ImageView chickenSprite_4;
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

        // set orientation to landscape
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        // find widgets
        chickenSprite = (ImageView) findViewById(R.id.chicken_1);
        chickenSprite_2 = (ImageView) findViewById(R.id.chicken_2);
        chickenSprite_3 = (ImageView) findViewById(R.id.chicken_3);
        chickenSprite_4 = (ImageView) findViewById(R.id.chicken_4);
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

                // get spritemargin
                int left_margin = SpriteHandler.getLeftMargin(chickenSprite);

                // if sprite has not hit the wall
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

                // get margin
                int left_margin = SpriteHandler.getLeftMargin(chickenSprite);

                // if sprite has not hit the wall
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

            // call method snakeClicked
            snakeClicked();
            }
        });

        // if fat chicken sprite is clicked
        fatChickenSprite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            // call method fatchickenClicked
            fatchickenClicked();
            }

        });

        // if user clicks chicken sprite
        chickenSprite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            // call method chickenClicked
            chickenClicked();
            }
        });

        // if user clicks chicken sprite4
        chickenSprite_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            // call method chickenClicked
            chickenClicked();
            }
        });

        // if scroll is clicked
        scroll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                // call method scrollClicked
                scrollClicked();
            }
        });

        // if eggs are clicked
        eggs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // if they're close enough
                if (SpriteHandler.getRange(chickenSprite, 200, 500)){

                    // select eggs
                    eggs_clicked = true;
                    SpriteHandler.spriteInvisble(eggs);
                    SpriteHandler.spriteVisible(eggs_lit);
                }
            }
        });

        // if switch is clicked
        switch_bulb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // if they're close enough
                if (SpriteHandler.getRange(chickenSprite, 300, 590)) {

                    // call method lightBulbs
                    lightBulbs();
                }
            }
        });

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
    }

    // gets called when activity onreult is called
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // if the user types the right answer
        if (resultCode == RESULT_OK) {

            // make fatchicken come out of his cage
            SpriteHandler.spriteVisible(cage);
            SpriteHandler.spriteInvisble(fatChickenSprite);
            SpriteHandler.spriteVisible(fatChickenSprite_2);

            // if user clicks on the now free fatchicken
            fatChickenSprite_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                // if user is close enough
                if (SpriteHandler.getRange(chickenSprite, 100, 400)) {

                    // make his chickensound
                    AudioHandler.checkMute(chicken_sound, mute_clicked);

                    // select fatchickensprite
                    SpriteHandler.spriteInvisble(fatChickenSprite_2);
                    SpriteHandler.spriteVisible(fatChickenSprite_lit);

                    // show his new text
                    defaultTextVisibility();
                    TextHandler.textPopUp(fatchicken_text_4, textpop_time);
                }

                // if snake is clicked after fatchicken has been selected
                snakeSprite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    // if user is close enough
                    if (SpriteHandler.getRange(chickenSprite, 400, 700)) {

                        // make snakes bitesound
                        AudioHandler.checkMute(bite_sound, mute_clicked);

                        // after a bit (sound is a little delayed)
                        snakeSprite.postDelayed(new Runnable() {
                            public void run() {

                            // make selected fatchicken disappear
                            SpriteHandler.spriteInvisble(fatChickenSprite_lit);

                            // show snakes new text
                            defaultTextVisibility();
                            TextHandler.textPopUp(snake_text_2, textpop_time);
                            }
                        }, 1000);

                        // after a bit more time
                        snakeSprite.postDelayed(new Runnable() {
                            public void run() {

                            // make splash sound
                            AudioHandler.checkMute(splash_sound, mute_clicked);

                            // make snake disappear
                            SpriteHandler.spriteInvisble(snakeSprite);
                            }
                        }, 3000);

                        // toilet can now be clicked
                        toilet_clickable = true;
                    }
                    }
                });
                }
            });

            // if toilet is clicked
            toilet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // if user is close enough
                    if (SpriteHandler.getRange(chickenSprite, 400, 900)) {

                        // check if clickable
                        if (toilet_clickable) {

                            // show chickentext
                            defaultTextVisibility();
                            TextHandler.textPopUp(chicken_text, textpop_time);

                            // after a second
                            toilet.postDelayed(new Runnable() {
                                public void run() {

                                    // make chicken disappear
                                    SpriteHandler.spriteInvisble(chickenSprite);

                                    // after a second
                                    toilet.postDelayed(new Runnable() {
                                        public void run() {

                                            // make splash sound
                                            AudioHandler.checkMute(splash_sound, mute_clicked);

                                            // start escapeactivity
                                            Intent escapeactivity = new Intent(MainActivity.this, EscapeActivity.class);

                                            // push boolean mute_clicked
                                            escapeactivity.putExtra("mute_clicked", mute_clicked);
                                            startActivity(escapeactivity);

                                            // make chickensprite comeback in case the backbutton is pressed
                                            chickenSprite.postDelayed(new Runnable() {
                                                public void run() {
                                                    SpriteHandler.spriteVisible(chickenSprite);
                                                }
                                            }, 2000);
                                        }
                                    }, 1000);
                                }
                            }, 2000);
                        }
                    }
                }
            });

        }

        // if the user did not type the right answer, make lock_clicked false so he can go back to lockactivity
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

    // makes certain sprites invisible to begin with
    public void defaultSpriteVisibility(){
        SpriteHandler.spriteInvisble(snakeSprite);
        SpriteHandler.spriteInvisble(chickenSprite_2);
        SpriteHandler.spriteInvisble(chickenSprite_3);
        SpriteHandler.spriteInvisble(chickenSprite_4);
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

    // makes sprite walk to the right
    public void walkAnimationRight(){

        // make the sprite that stands still disappear for a second and reappear
        SpriteHandler.spriteInvisble(chickenSprite);
        SpriteHandler.spriteInvisble(chickenSprite_4);
        SpriteHandler.spriteDisRe(chickenSprite, dis_ap_time);

        // make the Sprite that walks appear for a second and then disappear
        SpriteHandler.spriteVisible(chickenSprite_2);
        SpriteHandler.spriteApDis(chickenSprite_2, dis_ap_time);

        // update LeftMargin to make chickensprite move (by calling class ettings)
        SpriteHandler.spriteRight(chickenSprite);

        // update margins of invisible sprites
        SpriteHandler.spriteRight(chickenSprite_2);
        SpriteHandler.spriteRight(chickenSprite_3);
        SpriteHandler.spriteRight(chickenSprite_4);
        SpriteHandler.spriteRight(feather);
    }

    // makes sprite walk to the left
    public void walkAnimationLeft(){

        // make the sprite that stands still disappear for a second and reappear
        SpriteHandler.spriteInvisble(chickenSprite);
        SpriteHandler.spriteDisRe(chickenSprite_4, dis_ap_time);

        //make the sprite that walks appear for a second and then disappear
        SpriteHandler.spriteVisible(chickenSprite_3);
        SpriteHandler.spriteApDis(chickenSprite_3, dis_ap_time);

        // update leftMargin again to make chickensprite move
        SpriteHandler.spriteLeft(chickenSprite);

        // update margins of invisible sprites
        SpriteHandler.spriteLeft(chickenSprite_2);
        SpriteHandler.spriteLeft(chickenSprite_3);
        SpriteHandler.spriteLeft(chickenSprite_4);
        SpriteHandler.spriteLeft(feather);
    }

    // makes texts and snake appear when flusher is clicked
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

            // play splashsound
            AudioHandler.checkMute(splash_sound, mute_clicked);

            // after a second
            snakeSprite.postDelayed(new Runnable() {
                public void run() {
                    // show snake and flushtext4
                    SpriteHandler.spriteVisible(snakeSprite);
                    defaultTextVisibility();
                    TextHandler.textPopUp(flush_text_4, textpop_time);
                }
            }, 1000);
        }
    }

    // makes snake talk and eat the eggs
    public void snakeClicked(){

        // if the user is close enough and the eggs haven't been eaten
        if (SpriteHandler.getRange(chickenSprite, 420, 600)&&snake_ate_eggs ==  false) {

            // show flushtext4
            defaultTextVisibility();
            TextHandler.textPopUp(flush_text_4, textpop_time);

            // if the eggs have been selected but not eaten yet
            if (eggs_clicked && snake_ate_eggs == false) {

                // make bitesound
                AudioHandler.checkMute(bite_sound, mute_clicked);

                // after a second
                snakeSprite.postDelayed(new Runnable() {
                    public void run() {

                        // make selected eggs invisible
                        SpriteHandler.spriteInvisble(eggs_lit);
                        flush_text_4.setVisibility(View.INVISIBLE);

                        // show snakes new text
                        defaultTextVisibility();
                        TextHandler.textPopUp(snake_text_1, textpop_time);
                    }
                }, 700);
                snake_ate_eggs = true;
            }
        }
    }

    // makes fatchicken give eggs and start lockactivity
    public void fatchickenClicked(){

        // if user is close enough
        if (SpriteHandler.getRange(chickenSprite, 100, 400)) {

            // make chickensound
            AudioHandler.checkMute(chicken_sound, mute_clicked);
            chicken_talked = true;

            // if there is no feather yet
            if (feather_visible == false) {

                // show fat chicken text
                defaultTextVisibility();
                TextHandler.textPopUp(fatchicken_text_1, textpop_time);
            }

            // if there is a feather but the eggs have not been eaten yet
            if ((feather_visible) && (snake_ate_eggs == false)) {

                // show fat chicken text 2
                defaultTextVisibility();
                TextHandler.textPopUp(fatchicken_text_2, textpop_time);

                // after a second
                eggs.postDelayed(new Runnable() {
                    public void run() {

                        // make eggs visible and feather invisible
                        SpriteHandler.spriteVisible(eggs);
                        SpriteHandler.spriteInvisble(feather);
                        feather_used = true;
                    }
                }, 2000);
            }

            // if the  snake already ate the eggs
            if (snake_ate_eggs) {
                SpriteHandler.spriteInvisble(eggs);
                defaultTextVisibility();
                TextHandler.textPopUp(fatchicken_text_3, textpop_time);

                // start Lockactivity (make sure the link can only be clicked once)
                if (lock_clicked == false){
                    fatChickenSprite.postDelayed(new Runnable() {
                        public void run() {
                            Intent lockactivity = new Intent(MainActivity.this, LockActivity.class);

                            // push boolean mute_clicked to lockactivity
                            lockactivity.putExtra("mute_clicked", mute_clicked);
                            startActivityForResult(lockactivity, 1);
                        }
                    }, 3000);
                }

                // set lockclicked to true so the link can't be clicked again
                lock_clicked = true;
            }
        }
    }

    // make chicken pull out feather and show a text
    public void chickenClicked(){

        // if he has talked to fatchicken but not yet used his feather
        if (chicken_talked && feather_used == false) {

            // show feather
            SpriteHandler.spriteVisible(feather);
            feather_visible = true;

            // show chickentext2
            defaultTextVisibility();
            TextHandler.textPopUp(chicken_text_2, textpop_time);
        }
    }

    // starts scrollactivity
    public void scrollClicked(){

        // if user is close enough
        if (SpriteHandler.getRange(chickenSprite, 280, 455)) {

            // start scrollactivity
            Intent scrollactivity = new Intent(MainActivity.this, ScrollActivity.class);
            startActivity(scrollactivity);
        }
    }

    // changes icon and function of mute to unmute
    public void muteClicked(){
        mute_clicked = true;
        SpriteHandler.spriteInvisble(mute);
        SpriteHandler.spriteVisible(unmute);
    }

    // changes icon and function of unmute to mute
    public void unmuteClicked(){
        mute_clicked = false;
        SpriteHandler.spriteInvisble(unmute);
        SpriteHandler.spriteVisible(mute);
    }
}
