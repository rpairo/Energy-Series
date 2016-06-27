package tech.rpairo.energyseries.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

import tech.rpairo.energyseries.R;
import tech.rpairo.energyseries.activities.SearchActivity;
import tech.rpairo.energyseries.adapters.AdapterFragmentsSeries;

/**
 * Created by Raul on 24/6/16.
 */
public class FragmentSeries extends Fragment {

    //region Variables
    private AppBarLayout appBarLayout;
    private TabLayout tabs;
    private ViewPager viewPager;
    private FloatingActionButton fab;
    //endregion

    //region Constructores
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_series, container, false);

        if (savedInstanceState == null)
            this.insertarTabs(container);

        this.viewPager = (ViewPager) view.findViewById(R.id.pager_series);
        this.poblarViewPager(this.viewPager);
        this.tabs.setupWithViewPager(this.viewPager);

        // Habilita el modo scrollable para el TabLayout
        this.tabs.setTabMode(TabLayout.MODE_SCROLLABLE);

        //Floating action menu & buttons
        this.fab = (FloatingActionButton) view.findViewById(R.id.fab_buscar_series);
        this.fab.setScaleX(0);
        this.fab.setScaleY(0);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            final Interpolator interpolador = AnimationUtils.loadInterpolator(getContext(),
                    android.R.interpolator.fast_out_slow_in);

            fab.animate().setInterpolator(interpolador);
        }

        fab.animate()
                .scaleX(1)
                .scaleY(1)
                .setDuration(600)
                .setStartDelay(1000);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar();
            }
        });

        return view;
    }
    //endregion

    //region Destructores
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.appBarLayout.removeView(this.tabs);
    }
    //endregion

    //region Tabs

    private void insertarTabs(ViewGroup container) {
        View padre = (View) container.getParent().getParent();
        this.appBarLayout = (AppBarLayout) padre.findViewById(R.id.appbar_main);
        this.tabs = new TabLayout(this.getActivity());
        this.tabs.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
        this.appBarLayout.addView(this.tabs);
    }

    private void poblarViewPager(ViewPager viewPager) {
        AdapterFragmentsSeries adapter = new AdapterFragmentsSeries(this.getFragmentManager());
        adapter.addFragment(new FragmentSeriesPopulares(), this.getString(R.string.tab_series_populares));
        adapter.addFragment(new FragmentSeriesToday(), this.getString(R.string.tab_series_today));
        adapter.addFragment(new FragmentSeriesEnEmision(), this.getString(R.string.tab_series_en_emision));
        adapter.addFragment(new FragmentSeriesMejorValoradas(), this.getString(R.string.tab_series_mejor_valoradas));

        viewPager.setAdapter(adapter);
    }
    //endregion

    //region FloatActionButton
    private void buscar() {

        startActivity(new Intent(getContext(), SearchActivity.class));
    }
    //endregion
}