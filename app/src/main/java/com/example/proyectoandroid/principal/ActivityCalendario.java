package com.example.proyectoandroid.principal;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CalendarView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.proyectoandroid.R;

public class ActivityCalendario extends Activity {

    private CalendarView c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);
    }
}