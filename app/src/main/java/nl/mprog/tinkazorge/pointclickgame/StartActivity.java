// Tinka Zorge PointClickGame
package nl.mprog.tinkazorge.pointclickgame;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {
    // define widgets
    Button startButton;
    // create context
    Context context_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set orientation to landscape
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_start);

        // find widgets
        Button startbutton = (Button) findViewById(R.id.startbutton);

        // get context
        context_start = StartActivity.this;

        // when the adddbutton is clicked
        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //define next activity
                Intent mainactivity = new Intent(StartActivity.this, MainActivity.class);

                //start next activity
                startActivity(mainactivity);
            }
        });
    }
}
