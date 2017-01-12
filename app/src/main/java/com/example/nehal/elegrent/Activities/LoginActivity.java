package com.example.nehal.elegrent.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nehal.elegrent.Adapters.ViewPagerAdapter;
import com.example.nehal.elegrent.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager loginDeals;
    int images[] = {R.drawable.slider1, R.drawable.slider2, R.drawable.slider3};
    private TextView skipTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        setClickListeners();
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

    }

    private void initViews() {
        loginDeals = (ViewPager)findViewById(R.id.landingVP);
        skipTv = (TextView)findViewById(R.id.skipTv);
    }
    private void setClickListeners(){
        skipTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.skipTv:
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
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
}
