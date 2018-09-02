package fikrims.io.moviecatalogueui.feature.main.home;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import fikrims.io.moviecatalogueui.R;
import fikrims.io.moviecatalogueui.feature.main.NowMovie.NowMovieFragment;
import fikrims.io.moviecatalogueui.feature.main.UpcomingMovie.UpcomingMovieFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    @BindView(R.id.tab)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        viewPager.setAdapter(new tabAdapter(getChildFragmentManager()));
        tabLayout.post(() -> tabLayout.setupWithViewPager(viewPager));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }

    private class tabAdapter extends FragmentPagerAdapter {
        String now_playing = getResources().getString(R.string.now_playing);
        String upcoming = getResources().getString(R.string.upcoming);
        final String tabs[] = {now_playing, upcoming};

        public tabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new NowMovieFragment();
                case 1:
                    return new UpcomingMovieFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return tabs.length; //count Tabs[] size: 2
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }
    }

}
