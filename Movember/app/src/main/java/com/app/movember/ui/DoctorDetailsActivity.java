package com.app.movember.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.app.movember.R;
import com.app.movember.util.Constants;

public class DoctorDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ActionBar actionBar;
    private EditText doctorNameText;
    private EditText doctorSpecialityText;
    private EditText doctorLocationText;
    private ImageButton submitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setHomeButtonEnabled(true);
            Drawable actionBarBg = getResources().getDrawable(R.drawable.action_bar);
            actionBar.setBackgroundDrawable(actionBarBg);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setIcon(R.drawable.action_bar_icon);
        }
        doctorNameText = (EditText) findViewById(R.id.doctor_name);
        doctorSpecialityText = (EditText) findViewById(R.id.speciality);
        doctorLocationText = (EditText) findViewById(R.id.location);
        submitButton = (ImageButton) findViewById(R.id.image_button);
        submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_button:
                String doctorName = doctorNameText.getText().toString();
                if (TextUtils.isEmpty(doctorName)) {
                    doctorNameText.setError("Name cannot be blank");
                    return;
                }
                String doctorSpeciality = doctorSpecialityText.getText().toString();
                if (TextUtils.isEmpty(doctorSpeciality)) {
                    doctorSpecialityText.setError("Speciality cannot be blank");
                    return;
                }
                String doctorLocation = doctorLocationText.getText().toString();
                if (TextUtils.isEmpty(doctorLocation)) {
                    doctorLocationText.setError("Location cannot be blank");
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString(Constants.DOCTOR_NAME, doctorName);
                bundle.putString(Constants.DOCTOR_SPECIALITY, doctorSpeciality);
                bundle.putString(Constants.DOCTOR_LOCATION, doctorLocation);
                Intent intent = new Intent(getApplicationContext(), DoctorPledgeActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }
}
