package com.example.nehal.elegrent.Activities;

import android.content.Intent;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nehal.elegrent.Utils.AppUtils;
import com.example.nehal.elegrent.R;

import butterknife.ButterKnife;

public class Splash extends AppCompatActivity {

    private TextView splashLogoTv;
    private TextView getBuzzTv;
    private LinearLayout splashText;
    public static final int STARTUP_DELAY = 300;
    public static final int ANIM_ITEM_DURATION = 1000;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initViews();
        ButterKnife.bind(this);
        animate();
    }
    private void initViews() {
        splashLogoTv = (TextView)findViewById(R.id.splashLogo);
        getBuzzTv = (TextView)findViewById(R.id.getBuzz);
        splashText = (LinearLayout)findViewById(R.id.textLayout);

    }

    private void animate() {
        ViewPropertyAnimatorCompat viewAnimator;
        ViewCompat.animate(splashLogoTv)
                .translationY(-300)
                .setStartDelay(STARTUP_DELAY + 100)
                .setDuration(ANIM_ITEM_DURATION).setInterpolator(
                new BounceInterpolator()).start();

        viewAnimator = ViewCompat.animate(splashText).setListener(new ViewPropertyAnimatorListener() {
            @Override
            public void onAnimationStart(View view) {
                splashText.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(View view) {
                if(AppUtils.isOnline(getApplicationContext())){
                    Intent intent = new Intent(Splash.this, LoginActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Splash.this, " No Internet Connected", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onAnimationCancel(View view) {

            }
        })
                .translationY(350).alpha(1)
                .setStartDelay((STARTUP_DELAY ) + 700)
                .setDuration(1000 );
        viewAnimator.setInterpolator(new DecelerateInterpolator(1.2f)).start();
    }
}
