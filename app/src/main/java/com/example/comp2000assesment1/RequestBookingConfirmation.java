package com.example.comp2000assesment1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RequestBookingConfirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_booking_confirmation);

        Button returnToDashboard = findViewById(R.id.btnRtrnDash);
        returnToDashboard.setOnClickListener(v -> {
            Intent intent = new Intent(
                    RequestBookingConfirmation.this,
                    DashBoardActivity.class
            );
            startActivity(intent);
            finish();
        });
    }
}
