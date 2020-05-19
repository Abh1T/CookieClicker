package com.example.cookiedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {
ImageView joe;
Boolean runs;
ImageView poker;
ConstraintLayout constraintLayout;
 TextView textView;
 int value = 0;
static AtomicInteger score;
static boolean runner;
static TextView scorelabel;
Animation rotateAnimation;
ImageView goldendice;
ConstraintLayout.LayoutParams params;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final VideoView videoView = findViewById(R.id.videoView);
        Uri url = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.rolling);
        videoView.setVideoURI(url);
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        runs = true;
        poker = findViewById(R.id.imageView3);
        poker.setVisibility(View.INVISIBLE);
        poker.setClickable(false);
        joe = findViewById(R.id.imageView);
        score = new AtomicInteger(0);
        runner = true;
        scorelabel = findViewById(R.id.score);
        constraintLayout = findViewById(R.id.layoutid);
        textView = new TextView(this);
        textView.setId(View.generateViewId());
        textView.setText("+1");
        goldendice = findViewById(R.id.imageView2);
        goldendice.setVisibility(View.INVISIBLE);
        goldendice.setClickable(false);
        textView.setVisibility(View.INVISIBLE);
        constraintLayout.addView(textView);
        final ScaleAnimation scaleAnimation = new ScaleAnimation(1.1f, 1.5f, 1.1f, 1.5f, Animation.RELATIVE_TO_SELF,.5f,  Animation.RELATIVE_TO_SELF, .5f );
        scaleAnimation.setDuration(250);
        joe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score.getAndAdd(1);
                joe.startAnimation(scaleAnimation);
                textView.setVisibility(View.VISIBLE);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(textView.getId(),ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP);
                constraintSet.connect(textView.getId(),ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM);
                constraintSet.connect(textView.getId(),ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT);
                constraintSet.connect(textView.getId(),ConstraintSet.RIGHT, constraintLayout.getId(), ConstraintSet.RIGHT);
                String vertical = (Math.random() *1) +"f";
                float f = Float.parseFloat(vertical);
                Log.d("RANDO", f+"");
                constraintSet.setVerticalBias(textView.getId(), 0.3f);
                constraintSet.setHorizontalBias(textView.getId(), f);
                constraintSet.applyTo(constraintLayout);
                scorelabel.setText("Score: "+score);
                Animation slider = null;
                slider = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, -10.0f);
                slider.setDuration(300);
                textView.startAnimation(slider);
                textView.setVisibility(View.INVISIBLE);


                if(score.get() > 50){
                    Log.d("POKER", "XHIP");
                    poker.setVisibility(View.VISIBLE);
                    poker.setClickable(true);
                    if(runs){
                        setFadein();
                        runs = false;
                    }
                    poker.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            double gamble = (Math.random()*2);
                            int x = (int)(score.get() * gamble);
                            score.set(x);
                            scorelabel.setText("Score: "+score);
                            if(score.get() < 20){
                                setFadeout();
                                runs = true;
                                poker.setVisibility(View.INVISIBLE);
                                poker.setClickable(false);
                            }
                        }
                    });

                }

                if(score.get()>10) {
                    goldendice.setClickable(true);
                    goldendice.setVisibility(View.VISIBLE);
                }
                    if (score.get() >= 10) {
                        goldendice.setClickable(true);
                        Log.d("CLICKABLE", "click");
                            if(runner)
                            setRotateAnimation();
                            Log.d("RANN", "Y");
                            goldendice.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    value++;
                                    TextView textView = findViewById(R.id.textView);
                                    textView.setText("Items Purchased: "+(value)+" Dice");
                                    Log.d("NEWVAL", ""+value);
                                    ImageView images = new ImageView(MainActivity.this);
                                    images.setId(View.generateViewId());
                                    images.setImageResource(R.drawable.unnamed);
                                    ConstraintLayout.LayoutParams params2 = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                                    images.setLayoutParams(params2);
                                    String vertical = (Math.random() *1) +"f";
                                    float f = Float.parseFloat(vertical);
                                    constraintLayout.addView(images);
                                    ConstraintSet constraintSet = new ConstraintSet();
                                    constraintSet.clone(constraintLayout);
                                    constraintSet.connect(images.getId(),ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP);
                                    constraintSet.connect(images.getId(),ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM);
                                    constraintSet.connect(images.getId(),ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT);
                                    constraintSet.connect(images.getId(),ConstraintSet.RIGHT, constraintLayout.getId(), ConstraintSet.RIGHT);
                                    constraintSet.setVerticalBias(images.getId(), 1f);
                                    constraintSet.setHorizontalBias(images.getId(), f);
                                    constraintSet.applyTo(constraintLayout);
                                    rotateAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate);
                                    images.setAnimation(rotateAnimation);
                                    Log.d("RANN", "Somehting useful bad code dumbness");
                                    score.getAndAdd(-10);
                                    scorelabel.setText("Score: "+score);

                                    Thread myt = new myThread();
                                    myt.start();
                                    if(score.get()< 10){
                                        goldendice.setClickable(false);
                                        goldendice.setVisibility(View.INVISIBLE);
                                    }
                                }
                            });
                        runner = false;
                    }
            }
        });
    }


    public class myThread extends Thread{
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                    Log.d("Thread", "Running");
                    final  int j = (int) ((Math.random() * 9) - 3);
                    score.getAndAdd(j);
                    Log.d("RUNS", "runner" + score);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            scorelabel.setText("Score: " + score);
                            TextView textViewx = findViewById(R.id.textView2);
                            textViewx.setText("Last Roll: "+j);
                            if(score.get()>10) {
                                goldendice.setClickable(true);
                                goldendice.setVisibility(View.VISIBLE);
                            }

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void setRotateAnimation(){
        rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        goldendice.startAnimation(rotateAnimation);
    }
    private void setFadein(){
        Animation fadein = AnimationUtils.loadAnimation(this, R.anim.fadein);
        poker.startAnimation(fadein);
    }
    private void setFadeout(){
        Animation fadeout = AnimationUtils.loadAnimation(this, R.anim.fadeout);
        poker.startAnimation(fadeout);
    }

}
