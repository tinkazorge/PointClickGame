package com.example.tinkazorge.pointclickgame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LockActivity extends AppCompatActivity {
    EditText code_text;
    TextView explain_text;
    TextView win_text;
    TextView lose_text;
    Button go_button;
    boolean solved = false;

    @Override
    //make sure the user can click anywhere in the screen to lose the focus on the EditText
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);

        code_text = (EditText) findViewById(R.id.code_text);
        explain_text = (TextView) findViewById(R.id.explain_text);
        win_text = (TextView) findViewById(R.id.win_text);
        lose_text = (TextView) findViewById(R.id.lose_text);
        go_button = (Button) findViewById(R.id.go_button);

        win_text.setVisibility(View.INVISIBLE);
        lose_text.setVisibility(View.INVISIBLE);


        go_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
        // get user input
        String code = code_text.getText().toString();

        if (code.equals("87")){
            explain_text.setVisibility(View.INVISIBLE);
            win_text.setVisibility(View.VISIBLE);
            go_button.setVisibility(View.INVISIBLE);
            code_text.setVisibility(View.INVISIBLE);
            win_text.postDelayed(new Runnable() {
                public void run() {
            win_text.setVisibility(View.GONE);
                }
            }, 2000);
            // define next activity
            solved = true;
            Intent mainactivity_from_lock = new Intent(LockActivity.this, MainActivity.class);
            // give true if puzzle is solved
            mainactivity_from_lock.putExtra("code", true);

            // go back to main
            finish();

            }
            else{
                explain_text.setVisibility(View.INVISIBLE);
                lose_text.setVisibility(View.VISIBLE);
                lose_text.postDelayed(new Runnable() {
                public void run() {
                    lose_text.setVisibility(View.GONE);
                    }
                }, 2000);
            }
        }
    });
   }
}

