package in.barbool.famousplaces;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class StatePagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public StatePagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                HotelFragment tab1 = new HotelFragment();
                return tab1;
            case 1:
                HotelFragment tab2 = new HotelFragment();
                return tab2;
            case 2:
                HotelFragment tab3 = new HotelFragment();
                return tab3;

            case 3:
                HomeFragment tab4 = new HomeFragment();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
