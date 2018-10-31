package co.edu.unac.iotunac.view;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.view.WindowManager;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

import co.edu.unac.iotunac.R;
/**
 * Created by Brayan Torres on 25/08/2018.
 */
public class Splashscreenapp extends AwesomeSplash {

    @Override
    public void initSplash(ConfigSplash configSplash) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        configSplash.setBackgroundColor(R.color.bg_splash);
        configSplash.setAnimCircularRevealDuration(2000);
        configSplash.setRevealFlagX(Flags.REVEAL_LEFT);
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM);

        configSplash.setLogoSplash(R.drawable.ic_logounac_icon);
        configSplash.setOriginalHeight(200);
        configSplash.setOriginalWidth(200);
        configSplash.setAnimLogoSplashDuration(2000);
        configSplash.setAnimLogoSplashTechnique(Techniques.Bounce);

        configSplash.setTitleSplash(getString(R.string.title));
        configSplash.setTitleFont("fonts/volatire.ttf");
        configSplash.setTitleTextColor(R.color.colortitle);
        configSplash.setTitleTextSize(30f);
        configSplash.setAnimTitleDuration(2000);
        configSplash.setAnimTitleTechnique(Techniques.FlipInX);
    }

    @Override
    public void animationsFinished() {
        startActivity(new Intent(Splashscreenapp.this, Test.class));
        finish();
    }
}