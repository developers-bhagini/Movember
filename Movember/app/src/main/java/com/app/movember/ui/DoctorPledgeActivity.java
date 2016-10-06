package com.app.movember.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.app.movember.R;

public class DoctorPledgeActivity extends AppCompatActivity implements View.OnClickListener {

    private ActionBar actionBar;
    private ImageButton yesButton;
    private ImageButton noButton;
    private Bundle mBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_pledge);
        actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setHomeButtonEnabled(true);
            Drawable actionBarBg = getResources().getDrawable(R.drawable.action_bar);
            actionBar.setBackgroundDrawable(actionBarBg);
        }
        yesButton = (ImageButton) findViewById(R.id.yes_button);
        noButton = (ImageButton) findViewById(R.id.no_button);
        yesButton.setOnClickListener(this);
        noButton.setOnClickListener(this);
        if (null != getIntent()) {
            mBundle = this.getIntent().getExtras();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.yes_button:
                Intent intent = new Intent(getApplicationContext(), ShareActivity.class);
                intent.putExtras(mBundle);
                startActivity(intent);
                break;
            case R.id.no_button:
                finishActivityIfRequired();
                break;
        }
    }

    private void finishActivityIfRequired() {
        if (!isFinishing()) {
            finish();
        }
    }
}
