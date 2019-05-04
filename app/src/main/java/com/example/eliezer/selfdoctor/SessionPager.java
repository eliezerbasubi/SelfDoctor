package com.example.eliezer.selfdoctor;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SessionPager extends FragmentPagerAdapter {
    public SessionPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                    FirstPageFragment firstPageFragment = new FirstPageFragment();
                    return firstPageFragment;
            case 1 :
                    CancerFragment cancer= new CancerFragment();
                    return cancer;
            case 2:
                    CutaneousFragment cutaneous = new CutaneousFragment();
                    return cutaneous;
            case 3 :
                    EndocrineFragment endocrineFragment= new EndocrineFragment();
                    return endocrineFragment;
            case 4 :
                    EyesFragment eyesFragment = new EyesFragment();
                    return eyesFragment;
            case 5 :
                    HumanFragment humanFragment= new HumanFragment();
                    return humanFragment;
            case 6 :
                    InfectiousFragment infectiousFragment = new InfectiousFragment();
                    return infectiousFragment;
            case 7 :
                    IntestinalFragment intestinalFragment= new IntestinalFragment();
                    return intestinalFragment;

                    default:
                        return  null;

        }
    }

    @Override
    public int getCount() {
        return 8;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        super.getPageTitle(position);

        switch (position){
            case 0 :
                return "home";
            case 1 :
                return "Cancer diseases";
            case 2 :
                return "Cutaneous diseases";
            case 3 :
                return "Endocrine diseases";
            case 4 :
                return "Eyes Diseases";
            case 5 :
                return "Human Diseases";
            case 6 :
                return "Infectious diseases";
            case 7 :
                return "Intestinal diseases";
                default:
                    return null;
        }
    }
}
