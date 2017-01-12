package com.example.nehal.elegrent.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nehal.elegrent.Activities.LoginActivity;
import com.example.nehal.elegrent.R;

/**
 * Created by nehal on 11/1/17.
 */

public class ViewPagerAdapter extends PagerAdapter {
    int size;
    Activity act;
    View layout;
    TextView pagenumber1, pagenumber2, pagenumber3, pagenumber4, pagenumber5;
    ImageView pageImage;
    Context context;
    String[] t = new String[500];
    LayoutInflater inflater;
    static int w;
    int images[];
    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    public ViewPagerAdapter(LoginActivity mainActivity,int images[], Context context ) {
        this.images = images;
        act = mainActivity;
        this.context = context;
        inflater = (LayoutInflater) act
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layout = inflater.inflate(R.layout.page_item, container, false);

        try {
            View dot1 = (View) layout.findViewById(R.id.c1);
            View dot2 = (View) layout.findViewById(R.id.c2);
            View dot3 = (View) layout.findViewById(R.id.c3);
            TextView start = (TextView) layout.findViewById(R.id.start);
            int pagenumberTxt = position;
            if (position == 0) {
                dot1.setVisibility(View.GONE);
                dot1.setBackgroundResource(R.drawable.circle2);
                dot2.setVisibility(View.GONE);
                dot3.setVisibility(View.GONE);

                ImageView img = (ImageView) layout.findViewById(R.id.imageView1);
                //Picasso.with(context).load("http://res.cloudinary.com/mesh/image/upload/v1483360154/Onboarding1_cn8a7r.png").into(img);
                img.setImageResource(R.drawable.slider1);

            }
            if (position == 1) {
                dot1.setVisibility(View.GONE);
                dot2.setVisibility(View.GONE);
                dot2.setBackgroundResource(R.drawable.circle2);
                dot3.setVisibility(View.GONE);
                ImageView img = (ImageView) layout.findViewById(R.id.imageView1);
                //Picasso.with(context).load("http://res.cloudinary.com/mesh/image/upload/v1483360154/Onboarding2_fxxji1.png").into(img);
                img.setImageResource(R.drawable.slider2);

            }
            if (position == 2) {
                dot1.setVisibility(View.GONE);
                dot2.setVisibility(View.GONE);

                dot3.setVisibility(View.GONE);
                dot3.setBackgroundResource(R.drawable.circle2);

                ImageView img = (ImageView) layout.findViewById(R.id.imageView1);
                //Picasso.with(context).load("http://res.cloudinary.com/mesh/image/upload/v1483360153/Onboarding3_puwhot.png").into(img);
                img.setImageResource(R.drawable.slider3);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //pagenumber1.setText("Now your in Page No  " +pagenumberTxt );


        (container).addView(layout);
        return layout;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

}
