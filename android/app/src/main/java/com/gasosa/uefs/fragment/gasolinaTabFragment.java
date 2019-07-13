package com.gasosa.uefs.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gasosa.uefs.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class gasolinaTabFragment extends Fragment {
    private  String escolha;
    public gasolinaTabFragment(String t) {
        // Required empty public constructor
        escolha=t;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_gasolina_tab, container, false);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpagerGaso);
        setupViewPager(viewPager);
        // Set Tabs inside Toolbar
        TabLayout tabs = (TabLayout) view.findViewById(R.id.result_tabsGaso);
        tabs.setupWithViewPager(viewPager);

        return view;
    }// TODO: Rename method, update argument and hook method into UI event
    private void setupViewPager(ViewPager viewPager) {


        Adapter adapter = new Adapter(getChildFragmentManager());
        adapter.addFragment(new gasoFragment(escolha), "Comum");
        adapter.addFragment(new GasolinaFragment(escolha), "Aditivado");

        viewPager.setAdapter(adapter);



    }
    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }



}
