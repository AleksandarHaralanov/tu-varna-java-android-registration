package io.github.aleksandarharalanov.registrationapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView serialNumberTextView = findViewById(R.id.serialNumberTextView);
        TextView testResultTextView = findViewById(R.id.testResultTextView);
        TextView descriptionTextView = findViewById(R.id.descriptionTextView);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String registrantName = extras.getString("registrantName");
            String serialNumber = extras.getString("serialNumber");
            String testResult = extras.getString("testResult");
            String defectDescription = extras.getString("defectDescription");
            nameTextView.setText("Registrant Name: " + registrantName);
            serialNumberTextView.setText("Serial Number: " + serialNumber);
            testResultTextView.setText("Test Result: " + testResult);
            descriptionTextView.setText("Defect Description: " + defectDescription);
        }

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
