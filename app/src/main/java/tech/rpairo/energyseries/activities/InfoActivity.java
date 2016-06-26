package tech.rpairo.energyseries.activities;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import tech.rpairo.energyseries.R;
import tech.rpairo.energyseries.model.Serie;

/**
 * Created by Raul on 26/6/16.
 */
public class InfoActivity extends AppCompatActivity {

    //region Variables
    private Serie serie;
    public final static String PARCELABLE_SERIE = "SERIE";
    private ImageView backdrop;
    private FloatingActionButton floatingActionButton;
    //endregion

    //region Constructores
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        this.floatingActionButton = (FloatingActionButton) findViewById(R.id.fab_serie_info_voz);
        this.floatingActionButton.hide();

        this.serie = getIntent().getExtras().getParcelable(PARCELABLE_SERIE);

        this.backdrop = (ImageView) findViewById(R.id.backdrop_serie_info);

        this.cargarBackdrop();
    }
    //endregion

    //region Glide
    private void cargarBackdrop() {

        Glide.with(this).load(this.serie.getFanArt()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {

                backdrop.setImageBitmap(resource);

                Palette.from(resource).generate(new Palette.PaletteAsyncListener() {

                    public void onGenerated(Palette p) {

                        if (p != null) {

                            Palette.Swatch vibrantSwatch = p.getVibrantSwatch();
                            Palette.Swatch vibrantDarkSwatch = p.getDarkVibrantSwatch();
                            Palette.Swatch mutedSwatch = p.getMutedSwatch();
                            Palette.Swatch mutedDarkSwatch = p.getDarkMutedSwatch();

                            /*ViewPager vp = (ViewPager) findViewById(R.id.htab_viewpager);

                            if (vibrantDarkSwatch != null)
                                vp.setBackgroundColor(vibrantDarkSwatch.getRgb());
                            else if (mutedDarkSwatch != null)
                                vp.setBackgroundColor(mutedDarkSwatch.getRgb());*/

                            if (vibrantSwatch != null)
                                floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(vibrantSwatch.getRgb()));
                            else if (mutedSwatch != null)
                                floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(mutedSwatch.getRgb()));
                        }

                        setToolbar();

                        Snackbar.make(findViewById(R.id.coordinator_serie_info), serie.getTitle(), Snackbar.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
    //endregion

    //region Toolbar
    private void setToolbar() {

        // Añade la Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_serie_info);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {

            // Habilita el home button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            // Elimina texto del toolbar
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        // Vuelve visible la AppBar
        final AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar_serie_info);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        animation.reset();

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                appBarLayout.setVisibility(View.VISIBLE);
                floatingActionButton.show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        appBarLayout.clearAnimation();
        appBarLayout.startAnimation(animation);
    }
    //endregion

    //region Eventos del Activity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //endregion
}