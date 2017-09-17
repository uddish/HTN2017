package com.example.uddishverma.hackthenorth;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    RelativeLayout currency, hotel;
    TextView currencyText, hotelText;

    Typeface tfRegular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        currency = (RelativeLayout) findViewById(R.id.currency);
        hotel = (RelativeLayout) findViewById(R.id.hotel);

        currencyText = (TextView) findViewById(R.id.currency_title);
        hotelText = (TextView) findViewById(R.id.hotel_title);

        tfRegular = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        currencyText.setTypeface(tfRegular);
        hotelText.setTypeface(tfRegular);

        currency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CurrencyActivity.class));
            }
        });

        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}
