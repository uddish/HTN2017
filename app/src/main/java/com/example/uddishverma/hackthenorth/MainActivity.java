package com.example.uddishverma.hackthenorth;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    TextView head1, head2;
    TextView reviewsTitle, priceTitle, crimeTitle;
    DiscreteSeekBar reviewSeekBar, priceSeekBar, crimeSeekBar;
    BottomSheetBehavior bottomSheetBehavior;
    NestedScrollView bottomSheet;
    Button submitBtn;

    Typeface tfBold, tfRegular;

    int reviewScore, crimeScore, priceScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tfBold = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        tfRegular = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        head1 = (TextView) findViewById(R.id.head_1);
        head2 = (TextView) findViewById(R.id.head_2);

        submitBtn = (Button) findViewById(R.id.submit_btn);

        reviewsTitle = (TextView) findViewById(R.id.reviews_title);
        priceTitle = (TextView) findViewById(R.id.price_title);
        crimeTitle = (TextView) findViewById(R.id.crime_title);

        reviewSeekBar = (DiscreteSeekBar) findViewById(R.id.reviews_seek);
        crimeSeekBar = (DiscreteSeekBar) findViewById(R.id.crime_seek);
        priceSeekBar = (DiscreteSeekBar) findViewById(R.id.price_seek);

        head1.setTypeface(tfBold);
        head2.setTypeface(tfRegular);
        reviewsTitle.setTypeface(tfRegular);
        crimeTitle.setTypeface(tfRegular);
        priceTitle.setTypeface(tfRegular);

        bottomSheet = (NestedScrollView) findViewById(R.id.bottomsheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setPeekHeight(0);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reviewScore = reviewSeekBar.getProgress();
                crimeScore = crimeSeekBar.getProgress();
                priceScore = priceSeekBar.getProgress();

                Intent intent = new Intent(getApplicationContext(), HotelsListActivity.class);
                intent.putExtra("review", String.valueOf(reviewScore));
                intent.putExtra("price", String.valueOf(priceScore));
                intent.putExtra("crime", String.valueOf(crimeScore));

                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = MotionEventCompat.getActionMasked(event);

        switch (action) {
            case (MotionEvent.ACTION_UP):
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                return true;
            default:
                return super.onTouchEvent(event);
        }

    }
}
