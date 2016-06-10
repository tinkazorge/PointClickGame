package com.example.tinkazorge.pointclickgame;

import android.content.Intent;
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
    int dis_ap_time = 800;
    int move_space = 35;
    int textpop_time = 3000;
    int count_flush = 0;
    boolean feather_visible = false;
    boolean eggs_visible = false;
    boolean chicken_talked = false;
    boolean snake_ate_eggs = false;
    boolean fatchicken_2_visible = false;
    boolean solved = false;
    boolean fat_chicken_2_visible = false;



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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find widgets
        RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.rootRL);
        chickenSprite = (ImageView) findViewById(R.id.chicken_1);
        fatChickenSprite = (ImageView) findViewById(R.id.fat_chicken);
        fatChickenSprite_2 = (ImageView) findViewById(R.id.fat_chicken_2);
        snakeSprite = (ImageView) findViewById(R.id.snake);
        chickenSprite_2 = (ImageView) findViewById(R.id.chicken_2);
        chickenSprite_3 = (ImageView) findViewById(R.id.chicken_3);
        flusher = (ImageView) findViewById(R.id.flush);
        feather = (ImageView) findViewById(R.id.feather_1);
        cage = (ImageView) findViewById(R.id.cage_2);
        flush_text_1 = (TextView) findViewById(R.id.flush_text_1);
        flush_text_2 = (TextView) findViewById(R.id.flush_text_2);
        flush_text_3 = (TextView) findViewById(R.id.flush_text_3);
        flush_text_4 = (TextView) findViewById(R.id.flush_text_4);
        fatchicken_text_1 = (TextView) findViewById(R.id.fatchicken_text_1);
        fatchicken_text_2 = (TextView) findViewById(R.id.fatchicken_text_2);
        fatchicken_text_3 = (TextView) findViewById(R.id.fatchicken_text_3);
        fatchicken_text_4 = (TextView) findViewById(R.id.fatchicken_text_4);
        snake_text_1 = (TextView) findViewById(R.id.snake_text_1);
        snake_text_2 = (TextView) findViewById(R.id.snake_text_2);
        rightArrow = (ImageView) findViewById(R.id.arrowright);
        leftArrow = (ImageView) findViewById(R.id.arrowleft);
        eggs = (ImageView) findViewById(R.id.eggs);

        // make certain widgets invisible
        spriteInvisble(snakeSprite);
        spriteInvisble(chickenSprite_2);
        spriteInvisble(chickenSprite_3);
        spriteInvisble(feather);
        spriteInvisble(eggs);
        spriteInvisble(fatChickenSprite_2);
        spriteInvisble(cage);
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

        // when rightarrow is clicked
        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // make the sprite that stands still disappear for a second and reappear
                spriteInvisble(chickenSprite);
                spriteDisRe(chickenSprite);
                // make the Sprite that walks appear for a second and then disappear
                spriteVisible(chickenSprite_2);
                spriteApDis(chickenSprite_2);
//                // update LeftMargin to make chickensprite move
                spriteRight(chickenSprite);
//
//                // update margins of invisible sprites
                spriteRight(chickenSprite_2);
                spriteRight(chickenSprite_3);
                spriteRight(feather);
            }
        });

        // when leftarrow is clicked
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            int left_margin = getMargin(chickenSprite);
                if (left_margin> 4) {
                    // make the sprite that stands still disappear for a second and reappear
                    spriteInvisble(chickenSprite);
                    spriteDisRe(chickenSprite);

                    //make the sprite that walks appear for a second and then disappear
                    spriteVisible(chickenSprite_3);
                    spriteApDis(chickenSprite_3);

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
                // update count_flush
                count_flush++;
                // the first two times
                if (count_flush < 3) {
                    // show flush text 1
                    textPopUp(flush_text_1);
                }
                // the 3rd time
                if (count_flush == 3) {
                    // show flush text 2
                    textPopUp(flush_text_2);

                }
                // the 4th time
                if (count_flush == 4) {
                    // show flush text 3
                    textPopUp(flush_text_3);
                }
                // the 5th time
                if (count_flush > 4) {
                    // make snake appear and show flush text 4
                    spriteVisible(snakeSprite);
                    textPopUp(flush_text_4);
                }
            }
        });

        snakeSprite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textPopUp(flush_text_4);
                if (eggs_visible) {
                    spriteInvisble(eggs);
                    flush_text_4.setVisibility(View.INVISIBLE);
                    textPopUp(snake_text_1);
                    snake_ate_eggs = true;
                }
                if (fatchicken_2_visible){
                    spriteInvisble(fatChickenSprite_2);
                    textPopUp(snake_text_2);
                    snakeSprite.postDelayed(new Runnable() {
                        public void run() {
                            spriteInvisble(snakeSprite);
                        }
                    }, 2000);
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
                if ((feather_visible == true) && (snake_ate_eggs == false)) {
                    // show fat chicken text 2
                    textPopUp(fatchicken_text_2);
                    eggs.postDelayed(new Runnable() {
                        public void run() {
                            spriteVisible(eggs);
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
                    Bundle extras = getIntent().getExtras();
                    }
                }
        });

        // if user clicks fat chicken sprite 2
        fatChickenSprite_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //feather.requestLayout();
                //fatChickenSprite_2.requestLayout();
                //((ViewGroup.MarginLayoutParams) fatChickenSprite_2.getLayoutParams()).leftMargin = (((ViewGroup.MarginLayoutParams) feather.getLayoutParams()).leftMargin);
                fat_chicken_2_visible = true;
                textPopUp(fatchicken_text_4);
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
//        try {
//            Thread.sleep(1000);
            text.setVisibility(View.VISIBLE);
            text.postDelayed(new Runnable() {
                public void run() {
                    text.setVisibility(View.GONE);
                }
            }, textpop_time);
        }
//            catch (Exception e) {
//                Log.e("hoi","hoi");
//            }
//        }


    public void spriteVisible(final ImageView sprite){
        sprite.setVisibility(View.VISIBLE);
    }
    public void spriteInvisble(final ImageView sprite) {
        sprite.setVisibility(View.INVISIBLE);
    }
    public void spriteLeft(final ImageView sprite){
        ((ViewGroup.MarginLayoutParams) sprite.getLayoutParams()).leftMargin -= move_space;
        sprite.requestLayout();
    }
    public void spriteRight (final ImageView sprite){
        ((ViewGroup.MarginLayoutParams) sprite.getLayoutParams()).leftMargin += move_space;
        sprite.requestLayout();
    }
    // make sprite disappear and reappear again
    public void spriteDisRe(final ImageView sprite) {
        spriteInvisble(sprite);
        sprite.postDelayed(new Runnable() {
            public void run() {
                sprite.setVisibility(View.VISIBLE);
            }
        }, dis_ap_time);
    }
    // make sprite appear, then disappear again
    public void spriteApDis (final ImageView sprite) {
        spriteVisible(sprite);
        sprite.postDelayed(new Runnable() {
            public void run() {
                sprite.setVisibility(View.GONE);
            }
        }, dis_ap_time);
    }

    public void textPopUpLong (final TextView text){
        text.setVisibility(View.VISIBLE);
        text.postDelayed(new Runnable() {
            public void run() {
                text.setVisibility(View.GONE);
            }
        }, 4000);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_OK) {
            solved = true;
            spriteVisible(fatChickenSprite_2);
            spriteVisible(cage);
            spriteInvisble(fatChickenSprite);
        }
    }
    public int getMargin(final ImageView sprite){
        RelativeLayout.MarginLayoutParams params = (RelativeLayout.LayoutParams) chickenSprite.getLayoutParams();
        Log.e("hoi", "hoi");
        chickenSprite.setLayoutParams(params);
        return params.rightMargin;
    }
}
