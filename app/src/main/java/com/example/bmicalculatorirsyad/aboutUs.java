package com.example.bmicalculatorirsyad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class aboutUs extends AppCompatActivity {

    TextView youtube, github, email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        youtube = findViewById(R.id.youtubes);
        youtube.setMovementMethod(LinkMovementMethod.getInstance());

        email = findViewById(R.id.mails);
        email.setMovementMethod(LinkMovementMethod.getInstance());

        github = findViewById(R.id.githubs);
        github.setMovementMethod(LinkMovementMethod.getInstance());

    }
}