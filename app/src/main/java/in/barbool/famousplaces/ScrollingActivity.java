package in.barbool.famousplaces;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;

public class ScrollingActivity extends AppCompatActivity  {

    private String myString = "";

    public int[] slide_images = {

            R.drawable.hotel,
            R.drawable.burger,
            R.drawable.park,
            R.drawable.goldentemple

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String title = getIntent().getStringExtra("title");
        getSupportActionBar().setTitle(title);

        myString = title;

        final ImageView imageView = (ImageView) findViewById(R.id.htab_header);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.htab_tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Hotel"));
        tabLayout.addTab(tabLayout.newTab().setText("Restaurant"));
        tabLayout.addTab(tabLayout.newTab().setText("Park"));
        tabLayout.addTab(tabLayout.newTab().setText("Temple"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.htab_viewpager);
        final StatePagerAdapter adapter = new StatePagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                imageView.setBackgroundResource(slide_images[i]);
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_scrolling, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public String getMyData(){
        return myString;
    }
}
