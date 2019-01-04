package in.barbool.famousplaces;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

public class StartActivity extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    private SliderAdapter sliderAdapter;

    Button button;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        button = (Button) findViewById(R.id.btnStart);

        mAuth = FirebaseAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });



        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);

        sliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);

       WormDotsIndicator wormDotsIndicator = (WormDotsIndicator) findViewById(R.id.worm_dots_indicator);

        wormDotsIndicator.setViewPager(mSlideViewPager);

        final Integer[] colors_temp1 = {
                getResources().getColor(R.color.colorHotel),
                getResources().getColor(R.color.colorRestaruant),
                getResources().getColor(R.color.colorPlaces)

        };

        Integer[] colors_temp = {
                getResources().getColor(R.color.colorHotel),
                getResources().getColor(R.color.colorRestaruant),
                getResources().getColor(R.color.colorPlaces)

        };


        colors= colors_temp;

        mSlideViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
             button.setTextColor(colors_temp1[i]);

                if (i < (sliderAdapter.getCount() - 1) && i < (colors.length - 1)) {
                    mSlideViewPager.setBackgroundColor((Integer)argbEvaluator.evaluate(v,colors[i],colors[i + 1]));

                }else {
                    mSlideViewPager.setBackgroundColor(colors[colors.length - 1]);

                }
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
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser != null){
            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
