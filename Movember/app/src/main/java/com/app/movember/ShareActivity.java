package com.app.movember;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.facebook.FacebookSdk;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;

public class ShareActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = ShareActivity.class.getSimpleName();
    private ShareButton mShareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() ");
        //setup facebook
        initializeFacebookSdk();
        setContentView(R.layout.activity_share);
        mShareButton = (ShareButton) findViewById(R.id.share_button);
        mShareButton.setOnClickListener(this);

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
                Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.armband);
                Log.d(TAG, "share_button() " + image);
                SharePhoto photo = new SharePhoto.Builder()
                        .setBitmap(image)
                        .build();
                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(photo)
                        .build();
                mShareButton.setShareContent(content);
                break;
        }
    }
}
