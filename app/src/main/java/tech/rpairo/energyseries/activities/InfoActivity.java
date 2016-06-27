package tech.rpairo.energyseries.activities;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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
import tech.rpairo.energyseries.adapters.AdapterFragmentsSeries;
import tech.rpairo.energyseries.fragments.FragmentInfoActores;
import tech.rpairo.energyseries.fragments.FragmentInfoTemporadas;
import tech.rpairo.energyseries.model.Serie;
import tech.rpairo.energyseries.speaker.Speaker;

/**
 * Created by Raul on 26/6/16.
 */
public class InfoActivity extends AppCompatActivity {

    //region Variables
    private Serie serie;
    public final static String PARCELABLE_SERIE = "SERIE";
    private ImageView backdrop;
    private FloatingActionButton floatingActionButton;
    private ViewPager viewPager;
    private AppBarLayout appBarLayout;
    private TabLayout tabs;
    private Speaker speaker;
    //endregion

    //region Constructores
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        this.floatingActionButton = (FloatingActionButton) findViewById(R.id.fab_serie_info_voz);
        this.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speaker = new Speaker(getBaseContext());
                speaker.habla(serie.getDescripcion());
            }
        });
        this.floatingActionButton.hide();

        this.serie = getIntent().getExtras().getParcelable(PARCELABLE_SERIE);

        this.backdrop = (ImageView) findViewById(R.id.backdrop_serie_info);

        this.tabs = (TabLayout) findViewById(R.id.tabs_info);

        this.viewPager = (ViewPager) findViewById(R.id.viewpager_serie_info);

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

                            Palette.Swatch lightVibrantSwatch = p.getLightVibrantSwatch();
                            Palette.Swatch lightMutedSwatch = p.getLightMutedSwatch();


                            /*ViewPager vp = (ViewPager) findViewById(R.id.htab_viewpager);

                            if (vibrantDarkSwatch != null)
                                vp.setBackgroundColor(vibrantDarkSwatch.getRgb());
                            else if (mutedDarkSwatch != null)
                                vp.setBackgroundColor(mutedDarkSwatch.getRgb());*/

                            if (lightVibrantSwatch != null)
                                tabs.setSelectedTabIndicatorColor(lightVibrantSwatch.getRgb());
                            else if (lightMutedSwatch != null)
                                tabs.setSelectedTabIndicatorColor(lightMutedSwatch.getRgb());

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

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Vuelve visible la AppBar
        this.appBarLayout = (AppBarLayout) findViewById(R.id.appbar_serie_info);

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

                insertarTabs();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        appBarLayout.clearAnimation();
        appBarLayout.startAnimation(animation);
    }
    //endregion

    //region Tabs
    private void insertarTabs() {

        this.tabs.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));

        poblarViewPager();
    }

    private void poblarViewPager() {

        Bundle bundle = new Bundle();
        bundle.putInt(FragmentInfoTemporadas.KEY_BUNDLE, this.serie.getId());

        Fragment fragmentTemporadas = new FragmentInfoTemporadas();
        fragmentTemporadas.setArguments(bundle);

        Fragment fragmentActores = new FragmentInfoActores();
        fragmentActores.setArguments(bundle);

        AdapterFragmentsSeries adapter = new AdapterFragmentsSeries(getSupportFragmentManager());
        adapter.addFragment(fragmentTemporadas, "Temporadas");
        adapter.addFragment(fragmentActores, "Actores");

        this.viewPager.setAdapter(adapter);

        this.tabs.setupWithViewPager(this.viewPager);

        this.tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
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

    @Override
    protected void onStop() {
        super.onStop();
        if (this.speaker != null)
            this.speaker.cerrar();
    }
    //endregion
}