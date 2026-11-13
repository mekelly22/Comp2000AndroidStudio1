package com.example.comp2000assesment1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;
import java.util.Locale;

import android.app.AlertDialog;
import android.view.LayoutInflater;


public class RequestBookingActivity extends AppCompatActivity {

    private String selectedDate="";
    private String startTime="";
    private String endTime="";

    private boolean popupShown= false;

    private void showAvailableRoomsPopup(){
        View popupView = getLayoutInflater().inflate(
                R.layout.availiable_rooms, null
        );

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(popupView)
                .create();

        popupView.findViewById(R.id.btnPopupRoom1).setOnClickListener(v -> {
            dialog.dismiss();
            startActivity(new Intent(
                    RequestBookingActivity.this,
                    RequestingNewBookingActivity.class
            ));
        });

        popupView.findViewById(R.id.btnPopupRoom2).setOnClickListener(v -> {
            dialog.dismiss();
            startActivity(new Intent(
                    RequestBookingActivity.this,
                    RequestingNewBookingActivity.class
            ));
        });

        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_requestbooking);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnDate = findViewById(R.id.btnDate);
        btnDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog datePicker = new DatePickerDialog(
                    RequestBookingActivity.this,
                    (view, year, month, day) -> {
                        selectedDate = String.format(
                                Locale.UK,
                                "%02d/%02d/%04d",
                                day,
                                month + 1,
                                year
                        );
                        btnDate.setText(selectedDate);
                        checkBookingDetails();
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)



            );
            datePicker.show();
        });

        Button btnStartTime = findViewById(R.id.btnStartTime);
        btnStartTime.setOnClickListener(v -> showTimePicker(btnStartTime));

        Button btnEndTime = findViewById(R.id.btnEndTime);
        btnEndTime.setOnClickListener(v -> showTimePicker(btnEndTime));
    }

    private void showTimePicker(Button targetButton) {

        Calendar calendar = Calendar.getInstance();

        TimePickerDialog timePicker = new TimePickerDialog(
                RequestBookingActivity.this,
                android.R.style.Theme_Holo_Dialog_NoActionBar,
                (view, hour, minute) -> {

                    String selectedTime =String.format (
                            Locale.UK, "%02d:%02d", hour, minute
                    );

                    targetButton.setText(selectedTime);

                    // Save the selected time
                    if (targetButton.getId() == R.id.btnStartTime) {
                        startTime = selectedTime;
                    } else if (targetButton.getId() == R.id.btnEndTime) {
                        endTime = selectedTime;
                    }

                    checkBookingDetails();
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
        );

        timePicker.show();
    }

    private void checkBookingDetails() {

        if (selectedDate != null &&
                !selectedDate.isEmpty() &&
                startTime != null &&
                !startTime.isEmpty() &&
                endTime != null &&
                !endTime.isEmpty()) {
            popupShown = true;
            showAvailableRoomsPopup();
        }
    }
}
