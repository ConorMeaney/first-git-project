package com.conormeaney.galileo;

/**
 * Created by CONOR MEANEY K00178122 on 13/04/2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class anim extends Activity {
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    Thread splashTread;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        beginAnim();
    }
    private void beginAnim() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout lay=(LinearLayout) findViewById(R.id.lin_lay);
        lay.clearAnimation();
        lay.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView img = (ImageView) findViewById(R.id.splash);
        img.clearAnimation();
        img.startAnimation(anim);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (waited < 3000) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(com.conormeaney.galileo.anim.this,
                            MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    com.conormeaney.galileo.anim.this.finish();
                } catch (InterruptedException e) {

                } finally {
                    com.conormeaney.galileo.anim.this.finish();
                }

            }
        };
        splashTread.start();

    }

}