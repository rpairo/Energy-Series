package tech.rpairo.energyseries.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raul on 24/6/16.
 */
public class AdapterFragmentsSeries extends FragmentStatePagerAdapter {

    //region Variables
    private final List<Fragment> fragmentos = new ArrayList<>();
    private final List<String> titulosFragmentos = new ArrayList<>();
    //endregion

    //region Constructores
    public AdapterFragmentsSeries(FragmentManager fm) {
        super(fm);
    }
    //endregion

    //region Funciones del Adapter
    @Override
    public Fragment getItem(int position) {
        return this.fragmentos.get(position);
    }

    @Override
    public int getCount() {
        return this.fragmentos.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.titulosFragmentos.get(position);
    }
    //endregion

    //region Funciones auxiliares
    public void addFragment(Fragment fragment, String title) {
        this.fragmentos.add(fragment);
        this.titulosFragmentos.add(title);
    }
    //endregion
}