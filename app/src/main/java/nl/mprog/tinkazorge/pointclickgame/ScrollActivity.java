//Tinka Zorge PointClickGame
package nl.mprog.tinkazorge.pointclickgame;

    import android.content.pm.ActivityInfo;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;

    public class ScrollActivity extends AppCompatActivity {

    // initialize button
    Button back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // set orientation to landscape
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        // find button
        back_button = (Button)findViewById(R.id.back_button);

        // if backbutton is pressed
        back_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                // go back to main
                finish();
            }
        });
    }
}
