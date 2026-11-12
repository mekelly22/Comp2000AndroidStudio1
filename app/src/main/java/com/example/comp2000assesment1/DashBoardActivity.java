package com.example.comp2000assesment1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DashBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Button btnBooking = findViewById(R.id.btnBooking);

        btnBooking.setOnClickListener(v -> {

            Intent intent =
                    new Intent(DashBoardActivity.this,
                            RequestBookingActivity.class);

            startActivity(intent);

        });

        Button btnMyBookings = findViewById(R.id.btnMyBookings);

        btnMyBookings.setOnClickListener(v -> {

            Intent intent =
                    new Intent(DashBoardActivity.this,
                            MyBookingsActivity.class);

            startActivity(intent);

        });

}
}
