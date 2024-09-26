package io.github.aleksandarharalanov.registrationapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    private EditText serialNumberEditText;
    private EditText descriptionEditText;
    private CheckBox statusCheckBox;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_details);

        serialNumberEditText = findViewById(R.id.editTextText2);
        statusCheckBox = findViewById(R.id.checkBox);
        descriptionEditText = findViewById(R.id.editTextText3);
        nextButton = findViewById(R.id.Button1);

        serialNumberEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString().trim();
                boolean isValidSerialNumber = text.length() == 5 && text.startsWith("I") &&
                                              Character.isDigit(text.charAt(1)) &&
                                              text.charAt(1) != '0' &&
                                              Character.isAlphabetic(text.charAt(2)) &&
                                              Character.isAlphabetic(text.charAt(3)) &&
                                              Character.isAlphabetic(text.charAt(4));
                statusCheckBox.setEnabled(isValidSerialNumber);
                nextButton.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        statusCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (statusCheckBox.isChecked()) {
                    descriptionEditText.setEnabled(true);
                    statusCheckBox.setText("Negative");
                    nextButton.setEnabled(false);
                } else {
                    descriptionEditText.setEnabled(false);
                    statusCheckBox.setText("Positive");
                    descriptionEditText.setText("");
                    nextButton.setEnabled(true);
                }
            }
        });

        descriptionEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String description = s.toString().trim();
                boolean isValidDescription = description.length() >= 10 && description.length() <= 255;
                nextButton.setEnabled(isValidDescription);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        nextButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onBackPressed();
                return false;
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                String serialNumber = serialNumberEditText.getText().toString().trim();
                String result = statusCheckBox.getText().toString();
                String defectDescription = descriptionEditText.getText().toString().trim();
                String registrantName = getIntent().getStringExtra("registrantName");
                intent.putExtra("registrantName", registrantName);
                intent.putExtra("serialNumber", serialNumber);
                intent.putExtra("testResult", result);
                intent.putExtra("defectDescription", defectDescription);
                startActivity(intent);
            }
        });
    }
}
