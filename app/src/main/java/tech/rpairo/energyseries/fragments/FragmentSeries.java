package tech.rpairo.energyseries.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import tech.rpairo.energyseries.R;
import tech.rpairo.energyseries.adapters.AdapterFragmentsSeries;

/**
 * Created by Raul on 24/6/16.
 */
public class FragmentSeries extends Fragment {

    //region Variables
    private AppBarLayout appBarLayout;
    private TabLayout tabs;
    private ViewPager viewPager;
    private FloatingActionsMenu fam;
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
        this.fam = (FloatingActionsMenu) view.findViewById(R.id.fam_series);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_buscar_series);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar();
            }
        });

        fab = (FloatingActionButton) view.findViewById(R.id.fab_asistente_series);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asistente();
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
        adapter.addFragment(new FragmentSeriesEnEmision(), this.getString(R.string.tab_series_en_emision));
        adapter.addFragment(new FragmentSeriesMejorValoradas(), this.getString(R.string.tab_series_mejor_valoradas));

        viewPager.setAdapter(adapter);
    }
    //endregion

    //region FloatActionButton
    private void buscar() {
        this.toogleFAB();
    }

    private void asistente() {
        this.toogleFAB();
    }

    private void toogleFAB() {
        if (this.fam.isExpanded())
            this.fam.collapse();
    }
    //endregion
}