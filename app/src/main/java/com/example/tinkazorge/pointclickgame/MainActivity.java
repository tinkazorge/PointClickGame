package com.example.tinkazorge.pointclickgame;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    int code = 0;
    int count_flush = 0;
    boolean feather_visible = false;
    boolean eggs_visible = false;
    boolean chicken_talked = false;
    boolean snake_ate_eggs = false;

    // define widgets
    ImageView flusher;
    ImageView snakeSprite;
    ImageView chickenSprite;
    ImageView chickenSprite_2;
    ImageView chickenSprite_3;
    ImageView leftArrow;
    ImageView rightArrow;
    ImageView fatChickenSprite;
    ImageView feather;
    ImageView eggs;
    TextView flush_text_1;
    TextView flush_text_2;
    TextView flush_text_3;
    TextView flush_text_4;
    TextView fatchicken_text_1;
    TextView fatchicken_text_2;
    TextView fatchicken_text_3;
    TextView snake_text_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find widgets
        RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.rootRL);
        chickenSprite = (ImageView) findViewById(R.id.chicken_1);
        fatChickenSprite = (ImageView) findViewById(R.id.fat_chicken);
        snakeSprite = (ImageView) findViewById(R.id.snake);
        chickenSprite_2 = (ImageView) findViewById(R.id.chicken_2);
        chickenSprite_3 = (ImageView) findViewById(R.id.chicken_3);
        flusher = (ImageView) findViewById(R.id.flush);
        feather = (ImageView) findViewById(R.id.feather_1);
        flush_text_1 = (TextView) findViewById(R.id.flush_text_1);
        flush_text_2 = (TextView) findViewById(R.id.flush_text_2);
        flush_text_3 = (TextView) findViewById(R.id.flush_text_3);
        flush_text_4 = (TextView) findViewById(R.id.flush_text_4);
        fatchicken_text_1 = (TextView) findViewById(R.id.fatchicken_text_1);
        fatchicken_text_2 = (TextView) findViewById(R.id.fatchicken_text_2);
        fatchicken_text_3 = (TextView) findViewById(R.id.fatchicken_text_3);
        snake_text_1 = (TextView) findViewById(R.id.snake_text_1);
        rightArrow = (ImageView) findViewById(R.id.arrowright);
        leftArrow = (ImageView) findViewById(R.id.arrowleft);
        eggs = (ImageView) findViewById(R.id.eggs);

        // make certain widgets invisible
        spriteInvisble(snakeSprite);
        spriteInvisble(chickenSprite_2);
        spriteInvisble(chickenSprite_3);
        spriteInvisble(feather);
        spriteInvisble(eggs);
        flush_text_1.setVisibility(View.INVISIBLE);
        flush_text_2.setVisibility(View.INVISIBLE);
        flush_text_3.setVisibility(View.INVISIBLE);
        flush_text_4.setVisibility(View.INVISIBLE);
        fatchicken_text_1.setVisibility(View.INVISIBLE);
        fatchicken_text_2.setVisibility(View.INVISIBLE);
        fatchicken_text_3.setVisibility(View.INVISIBLE);
        snake_text_1.setVisibility(View.INVISIBLE);

        // when rightarrow is clicked
        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // make the sprite that stands still disappear for a second and reappear
                spriteInvisble(chickenSprite);
                chickenSprite.postDelayed(new Runnable() {
                    public void run() {
                        chickenSprite.setVisibility(View.VISIBLE);
                    }
                }, 800);
                // make the Sprite that walks appear for a second and then disappear
                spriteVisible(chickenSprite_2);
                chickenSprite_2.postDelayed(new Runnable() {
                    public void run() {
                        chickenSprite_2.setVisibility(View.GONE);
                    }
                }, 800);
                // update LeftMargin to make chickensprite move
                spriteRight(chickenSprite);

                // update margins of invisible sprites
                spriteRight(chickenSprite_2);
                spriteRight(chickenSprite_3);
                spriteRight(feather);
            }
        });
        // when leftarrow is clicked
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // make the sprite that stands still disappear for a second and reappear
                spriteInvisble(chickenSprite);
                chickenSprite.postDelayed(new Runnable() {
                    public void run() {
                        chickenSprite.setVisibility(View.VISIBLE);
                    }
                }, 800);

                // make the sprite that walks appear for a second and then disappear
                spriteVisible(chickenSprite_3);
                chickenSprite_3.postDelayed(new Runnable() {
                    public void run() {
                        chickenSprite_3.setVisibility(View.GONE);
                    }
                }, 800);

                // update leftMargin again to make chickensprite move
                spriteLeft(chickenSprite);

                // update margins of invisible sprites
                spriteLeft(chickenSprite_2);
                spriteLeft(chickenSprite_3);
                spriteLeft(feather);
            }
        });

        // if user clicks flush
        flusher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // update count_flush
                count_flush++;

                // the first two times
                outerloop:
                if (count_flush < 3) {
                    // show flush text 1
                    textPopUp(flush_text_1);
                    break outerloop;
                }

                // the 3rd time
                outerloop:
                if (count_flush == 3) {
                    // show flush text 2
                    textPopUp(flush_text_2);
                    break outerloop;
                }
                // the 4th time
                outerloop:
                if (count_flush == 4) {
                    // show flush text 3
                    textPopUp(flush_text_3);
                    break outerloop;
                }

                // the 5th time
                outerloop:
                if (count_flush > 4) {

                    // make snake appear and show flush text 4
                    spriteVisible(snakeSprite);
                    textPopUp(flush_text_4);
                    break outerloop;
                }
            }
        });

        snakeSprite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textPopUp(flush_text_4);
                if (eggs_visible == true) {
                    spriteInvisble(eggs);
                    flush_text_4.setVisibility(View.INVISIBLE);
                    textPopUp(snake_text_1);
                    snake_ate_eggs = true;
                }
            }
        });

        // if fat chicken sprite is clicked
        fatChickenSprite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chicken_talked = true;
                // if there is no feather yet
                if (feather_visible == false) {
                    // show fat chicken text
                   textPopUp(fatchicken_text_1);
                }
                if (feather_visible == true) {
                    // show fat chicken text 2
                    textPopUp(fatchicken_text_2);
                    eggs.postDelayed(new Runnable() {
                        public void run() {
                            eggs.setVisibility(View.VISIBLE);
                            eggs_visible = true;
                        }
                    }, 2000);
                }
                if (snake_ate_eggs == true) {
                    spriteInvisble(eggs);
                    textPopUp(fatchicken_text_3);
                    // start Lockactivity
                    Intent lockactivity = new Intent(MainActivity.this, LockActivity.class);
                    startActivityForResult(lockactivity, 1);
                    // get the result
                    if (true){

                    }
                }
            }
        });


        // if user clicks chicken sprite
        chickenSprite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chicken_talked == true) {
                    // show feather (chicken pulls out his feather)
                    feather.setVisibility(View.VISIBLE);
                    feather_visible = true;
                }
            }
        });
    }

        public void textPopUp(final TextView text){
            text.setVisibility(View.VISIBLE);
            text.postDelayed(new Runnable() {
                public void run() {
                    text.setVisibility(View.GONE);
                }
            }, 2000);
        }
        public void spriteVisible(final ImageView sprite){
            sprite.setVisibility(View.VISIBLE);
        }
        public void spriteInvisble(final ImageView sprite){
            sprite.setVisibility(View.INVISIBLE);
        }
        public void spriteLeft(final ImageView sprite){
            ((ViewGroup.MarginLayoutParams) sprite.getLayoutParams()).leftMargin -= 35;
            sprite.requestLayout();
        }
        public void spriteRight (final ImageView sprite){
            ((ViewGroup.MarginLayoutParams) sprite.getLayoutParams()).leftMargin += 35;
            sprite.requestLayout();
        }

}
