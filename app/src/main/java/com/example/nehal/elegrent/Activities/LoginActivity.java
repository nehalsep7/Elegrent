package com.example.nehal.elegrent.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nehal.elegrent.Adapters.ViewPagerAdapter;
import com.example.nehal.elegrent.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener {

    private ViewPager loginDeals;
    int images[] = {R.drawable.slider1, R.drawable.slider2, R.drawable.slider3};
    private TextView skipTv;
    private GoogleApiClient mGoogleApiClient;
    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;
    private LoginButton loginButton;
    private List<String> fbPermissions = new ArrayList<String>();
    private CallbackManager callbackManager;
    private ProfileTracker profileTracker;
    private  AccessTokenTracker accessTokenTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        initViews();
        setClickListeners();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        callbackManager = CallbackManager.Factory.create();
        AppEventsLogger.activateApp(this);
        trackFbProfile();
        ViewPagerAdapter adapter = new ViewPagerAdapter(LoginActivity.this, images, getApplicationContext());
        adapter.notifyDataSetChanged();
        loginDeals.setAdapter(adapter);
        loginDeals.setCurrentItem(0);
        loginDeals.setOffscreenPageLimit(1);
        final ImageView dot1 = (ImageView) findViewById(R.id.c1);
        final ImageView dot2 = (ImageView) findViewById(R.id.c2);
        final ImageView dot3 = (ImageView) findViewById(R.id.c3);
        loginDeals.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0){
                    dot1.setBackgroundResource(R.drawable.circle2);
                    dot2.setBackgroundResource(R.drawable.circle);
                    dot3.setBackgroundResource(R.drawable.circle);
                }else if(position == 1){
                    dot1.setBackgroundResource(R.drawable.circle);
                    dot2.setBackgroundResource(R.drawable.circle2);
                    dot3.setBackgroundResource(R.drawable.circle);
                }else if(position == 2){
                    dot1.setBackgroundResource(R.drawable.circle);
                    dot2.setBackgroundResource(R.drawable.circle);
                    dot3.setBackgroundResource(R.drawable.circle2);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                if(loginResult.getAccessToken() != null){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancel() {
                Log.i("Status","cancelled");
            }

            @Override
            public void onError(FacebookException error) {
                Log.i("Status",error.toString());
            }
        });

    }


    private void trackFbProfile() {
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(
                    Profile oldProfile,
                    Profile currentProfile) {
                Log.i("Profile:","Changed");
                //Log.i("Name:",currentProfile.getName().toString());

                // App code
            }
        };
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                Log.i("Token:","Changed");
                // Set the access token using
                // currentAccessToken when it's loaded or set.
            }
        };
        // If the access token is available already assign it.
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        profileTracker.startTracking();
        accessTokenTracker.startTracking();
    }

    private void initViews() {
        loginDeals = (ViewPager)findViewById(R.id.landingVP);
        skipTv = (TextView)findViewById(R.id.skipTv);
        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        fbPermissions.add("email");
        loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.setReadPermissions(fbPermissions);

    }
    private void setClickListeners(){
//        skipTv.setOnClickListener(this);
        findViewById(R.id.sign_in_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.skipTv:
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.sign_in_button:
                signIn();
                break;
        }
    }
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onBackPressed() {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(0);

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            Log.i("Name",acct.getDisplayName());
            if(acct.getPhotoUrl() != null){
                Log.i("photoUrl",acct.getPhotoUrl().toString());
            }
            //
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("UserName",acct.getDisplayName().toString());
            if(acct.getPhotoUrl() != null){
                intent.putExtra("UserPhoto",acct.getPhotoUrl().toString());
            }
            startActivity(intent);
//            mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
//            updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            //updateUI(false);
            Log.i("Result","Unsuccessful");
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }


}
