package com.app.movember.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.movember.R;
import com.app.movember.util.Constants;
import com.app.movember.util.NetworkUtil;
import com.facebook.FacebookSdk;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;

public class ShareActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = ShareActivity.class.getSimpleName();
    private ShareButton mShareButton;
    private Context mContext;
    private FrameLayout mBadgeView;
    private ActionBar mActionBar;
    private TextView mName;
    private TextView mPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() ");
        //setup facebook
        initializeFacebookSdk();
        setContentView(R.layout.activity_share);
        mActionBar = getSupportActionBar();
        if (null != mActionBar) {
            mActionBar.setDisplayShowTitleEnabled(false);
            mActionBar.setHomeButtonEnabled(true);
            Drawable actionBarBg = getResources().getDrawable(R.drawable.action_bar);
            mActionBar.setBackgroundDrawable(actionBarBg);
        }
        mContext = this;
        mShareButton = (ShareButton) findViewById(R.id.share_button);
        mBadgeView = (FrameLayout) findViewById(R.id.badge_layout);
        mShareButton.setOnClickListener(this);
        initView();
    }

    private void initView() {
        mName = (TextView) findViewById(R.id.name);
        mPlace = (TextView) findViewById(R.id.place);
        if (null != getIntent() && null != getIntent().getExtras()) {
            Bundle lData = getIntent().getExtras();
            String lNameValue = lData.getString(Constants.DOCTOR_NAME);
            String lPlacevalue = lData.getString(Constants.DOCTOR_LOCATION);
            mName.setText(getString(R.string.dr_text) + lNameValue);
            mPlace.setText(lPlacevalue);
        }
    }

    /**
     * Method to initialize the facebook sdk
     */
    private void initializeFacebookSdk() {
        FacebookSdk.sdkInitialize(getApplicationContext());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.share_button:
                //get the bitmap from layout
                mBadgeView.setDrawingCacheEnabled(true);
                mBadgeView.buildDrawingCache();
                Bitmap bitmap = mBadgeView.getDrawingCache();
                Log.d(TAG, "onClick :: bitmap :: " + bitmap);
                ShareDialog(bitmap);
                break;
        }
    }

    /**
     * Method to share in the facebook
     *
     * @param aBitmap
     */
    private void ShareDialog(Bitmap aBitmap) {
        Log.d(TAG, "share_button() " + aBitmap);
        if (NetworkUtil.areWeConnectedTonetwork(getApplicationContext())) {
            SharePhoto photo = new SharePhoto.Builder()
                    .setBitmap(aBitmap)
                    .build();
            SharePhotoContent content = new SharePhotoContent.Builder()
                    .addPhoto(photo)
                    .build();
            mShareButton.setShareContent(content);
        } else {
            Toast.makeText(mContext, getString(R.string.network_error_text), Toast.LENGTH_SHORT).show();
        }
    }

}
