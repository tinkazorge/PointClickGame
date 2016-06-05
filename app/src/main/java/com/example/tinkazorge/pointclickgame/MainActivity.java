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
    int x = 0;
    int count_flush = 0;
    boolean feather_visible = false;
    boolean eggs_visible = false;
    boolean chicken_talked = false;
    boolean snake_ate_eggs = false;
    //boolean chicken_talked = false;


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
        snakeSprite.setVisibility(View.INVISIBLE);
        chickenSprite_2.setVisibility(View.INVISIBLE);
        chickenSprite_3.setVisibility(View.INVISIBLE);
        feather.setVisibility(View.INVISIBLE);
        eggs.setVisibility(View.INVISIBLE);
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
                chickenSprite.setVisibility(View.INVISIBLE);
                chickenSprite.postDelayed(new Runnable() {
                    public void run() {
                        chickenSprite.setVisibility(View.VISIBLE);
                    }
                }, 800);
                // make the Sprite that walks appear for a second and then disappear
                chickenSprite_2.setVisibility(View.VISIBLE);
                chickenSprite_2.postDelayed(new Runnable() {
                    public void run() {
                        chickenSprite_2.setVisibility(View.GONE);
                    }
                }, 800);
                // update LeftMargin to make chickensprite move
                ((ViewGroup.MarginLayoutParams) chickenSprite.getLayoutParams()).leftMargin += 35;
                chickenSprite.requestLayout();

                // update margins of invisible sprites
                ((ViewGroup.MarginLayoutParams) chickenSprite_2.getLayoutParams()).leftMargin += 35;
                chickenSprite_2.requestLayout();
                ((ViewGroup.MarginLayoutParams) chickenSprite_3.getLayoutParams()).leftMargin += 35;
                chickenSprite_3.requestLayout();
                ((ViewGroup.MarginLayoutParams) feather.getLayoutParams()).leftMargin += 35;
                feather.requestLayout();
            }
        });
        // when leftarrow is clicked
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // make the sprite that stands still disappear for a second and reappear
                chickenSprite.setVisibility(View.INVISIBLE);
                chickenSprite.postDelayed(new Runnable() {
                    public void run() {
                        chickenSprite.setVisibility(View.VISIBLE);
                    }
                }, 800);

                // make the sprite that walks appear for a second and then disappear
                chickenSprite_3.setVisibility(View.VISIBLE);
                chickenSprite_3.postDelayed(new Runnable() {
                    public void run() {
                        chickenSprite_3.setVisibility(View.GONE);
                    }
                }, 800);

                // update leftMargin again to make chickensprite move
                ((ViewGroup.MarginLayoutParams) chickenSprite.getLayoutParams()).leftMargin -= 35;
                chickenSprite.requestLayout();

                // update margins of invisible sprites
                ((ViewGroup.MarginLayoutParams) chickenSprite_2.getLayoutParams()).leftMargin -= 35;
                chickenSprite_2.requestLayout();
                ((ViewGroup.MarginLayoutParams) chickenSprite_3.getLayoutParams()).leftMargin -= 35;
                chickenSprite_3.requestLayout();
                ((ViewGroup.MarginLayoutParams) feather.getLayoutParams()).leftMargin -= 35;
                feather.requestLayout();
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
                    flush_text_1.setVisibility(View.VISIBLE);
                    flush_text_1.postDelayed(new Runnable() {
                        public void run() {
                            flush_text_1.setVisibility(View.GONE);
                        }
                    }, 2000);
                    break outerloop;
                }

                // the 3rd time
                outerloop:
                if (count_flush == 3) {
                    // show flush text 2
                    flush_text_2.setVisibility(View.VISIBLE);
                    flush_text_2.postDelayed(new Runnable() {
                        public void run() {
                            flush_text_2.setVisibility(View.GONE);
                        }
                    }, 2000);
                    break outerloop;
                }
                // the 4th time
                outerloop:
                if (count_flush == 4) {
                    // show flush text 3
                    flush_text_3.setVisibility(View.VISIBLE);
                    flush_text_3.postDelayed(new Runnable() {
                        public void run() {
                            flush_text_3.setVisibility(View.GONE);
                        }
                    }, 2000);
                    break outerloop;
                }

                // the 5th time
                outerloop:
                if (count_flush > 4) {

                    // make snake appear and show flush text 4
                    snakeSprite.setVisibility(View.VISIBLE);
                    flush_text_4.setVisibility(View.VISIBLE);
                    flush_text_4.postDelayed(new Runnable() {
                        public void run() {
                            flush_text_4.setVisibility(View.GONE);
                        }
                    }, 2000);
                    break outerloop;
                }
            }
        });
        snakeSprite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flush_text_4.setVisibility(View.VISIBLE);
                flush_text_4.postDelayed(new Runnable() {
                    public void run() {
                        flush_text_4.setVisibility(View.GONE);
                    }
                }, 2000);
                if (eggs_visible == true){
                    eggs.setVisibility(View.INVISIBLE);
                    flush_text_4.setVisibility(View.INVISIBLE);
                    snake_text_1.setVisibility(View.VISIBLE);
                    snake_text_1.postDelayed(new Runnable() {
                        public void run() {
                            snake_text_1.setVisibility(View.GONE);
                        }
                    }, 2000);
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
                    fatchicken_text_1.setVisibility(View.VISIBLE);
                    fatchicken_text_1.postDelayed(new Runnable() {
                        public void run() {
                            fatchicken_text_1.setVisibility(View.GONE);
                        }
                    }, 2000);
                }
                if (feather_visible == true) {
                    // show fat chicken text 2
                    fatchicken_text_2.setVisibility(View.VISIBLE);
                    fatchicken_text_2.postDelayed(new Runnable() {
                        public void run() {
                            fatchicken_text_2.setVisibility(View.GONE);
                        }
                    }, 2000);
                    eggs.postDelayed(new Runnable() {
                        public void run() {
                            eggs.setVisibility(View.VISIBLE);
                            eggs_visible = true;
                        }
                    }, 2000);
                }
                if (snake_ate_eggs == true){
                    fatchicken_text_3.setVisibility(View.VISIBLE);
                    fatchicken_text_3.postDelayed(new Runnable() {
                        public void run() {
                            fatchicken_text_3.setVisibility(View.GONE);
                        }
                    }, 2000);
                    Intent lockactivity = new Intent(MainActivity.this, LockActivity.class);
                    startActivity(lockactivity);
                    //get the intent that started the activity
//                    Intent mainactivity = getIntent();

                    //retrieve input pushed from previous activity
//                    Bundle code_solved = mainactivity.getExtras();
//                    String solved = (String) code_solved.get("code_solved");
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
}