package com.test.admin.conurbations.activitys;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.ImageView;

import com.test.admin.conurbations.R;
import com.test.admin.conurbations.annotations.FindView;
import com.umeng.analytics.MobclickAgent;

import java.util.Random;


/**
 * Created by ZQiong on 2017/1/21.
 */

public class SplashActivity extends BaseActivity {

    private static final int ANIMATION_DURATION = 2000;
    private static final float SCALE_END = 1.13F;
    private static final int[] SPLASH_ARRAY = {
            R.mipmap.splash0,
            R.mipmap.splash1,
            R.mipmap.splash2,
            R.mipmap.splash3,
            R.mipmap.splash4,

            R.mipmap.splash6
    };
    @FindView
    ImageView mLogoImageView;

    @Override
    protected void initData(Bundle bundle) {
        Random r = new Random(SystemClock.elapsedRealtime());
        mLogoImageView.setImageResource(SPLASH_ARRAY[r.nextInt(SPLASH_ARRAY.length)]);
        animateImage();
    }

    @Override
    protected void initPresenter() {
    }

    private void animateImage() {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(mLogoImageView, "scaleX", 1f, SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(mLogoImageView, "scaleY", 1f, SCALE_END);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(ANIMATION_DURATION).play(animatorX).with(animatorY);
        set.start();

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                startActivityAndFinishWithOutObservable(MainActivity.class);
            }
        });
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    public void detachView() {

    }
}
