package in.barbool.famousplaces;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import in.barbool.famousplaces.Adapters.BookHotelAdapter;
import in.barbool.famousplaces.Adapters.ReviewsAdapter;
import in.barbool.famousplaces.Adapters.StateViewPagerAdapter;
import in.barbool.famousplaces.Models.BookHotelModel;
import in.barbool.famousplaces.Models.ReviewsModel;
import in.barbool.famousplaces.Models.StateViewPagerModel;

public class ScrollingViewActivity extends AppCompatActivity {

    private static final String TAG = ScrollingViewActivity.class.getSimpleName();

    Dialog dialog;
    ImageView imageView, mBackArrow, mGppgleMap;
    TextView textTitle ,mTitleView,mDescView,mAddress,mWebsite,mPhone;
    LinearLayout mPhoneCall;
    NestedScrollView mNestedView;
    FloatingActionButton fab;
    private ViewPager mSlideViewPager;
    private List<StateViewPagerModel> models;
    StateViewPagerAdapter stateViewPagerAdapter;

    RecyclerView mRecyclerView, mRecyclerView1;
    FirebaseDatabase mFirebaseDatabase;
    FirebaseAuth mAuth;
    DatabaseReference mRef,mRef1;
    ArrayList<BookHotelModel> list;
    BookHotelAdapter adapter;
    ArrayList<ReviewsModel> list1;
    ReviewsAdapter adapter1;
    String title1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling_view);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        final String title = getIntent().getStringExtra("title");
        final String desc = getIntent().getStringExtra("desc");
        String imageUri = getIntent().getStringExtra("imageUri");
        String address = getIntent().getStringExtra("address");
        String website = getIntent().getStringExtra("website");
        String phone = getIntent().getStringExtra("phone");
        String node = getIntent().getStringExtra("node");
        String image1 = getIntent().getStringExtra("image1");
        String image2 = getIntent().getStringExtra("image2");
        String image3 = getIntent().getStringExtra("image3");
        String star = getIntent().getStringExtra("star");
        String reviews = getIntent().getStringExtra("reviews");
        dialog = new Dialog(this);

        mNestedView = (NestedScrollView) findViewById(R.id.nestedScroll);
        fab = (FloatingActionButton) findViewById(R.id.floatButton);
        mNestedView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY) {
                    fab.hide();
                } else {
                    fab.show();
                }
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                giveFeedback();
            }
        });

        mBackArrow = (ImageView) findViewById(R.id.backarow);
        mBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mGppgleMap = (ImageView) findViewById(R.id.googleMap);
        mGppgleMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String map = "http://maps.google.co.in/maps?q=" + title;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
                startActivity(intent);
            }
        });

        mPhoneCall = (LinearLayout) findViewById(R.id.phoneCall);
        mPhoneCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+ "01835099905"));
                startActivity(intent);
            }
        });

        mSlideViewPager = (ViewPager) findViewById(R.id.ViewPager);
        models = new ArrayList<>();
        models.add(new StateViewPagerModel(image1));
        models.add(new StateViewPagerModel(image2));
        models.add(new StateViewPagerModel(image3));

        stateViewPagerAdapter = new StateViewPagerAdapter(this, models);
        mSlideViewPager.setAdapter(stateViewPagerAdapter);

        WormDotsIndicator wormDotsIndicator = (WormDotsIndicator) findViewById(R.id.worm_dots_indicator);
        wormDotsIndicator.setViewPager(mSlideViewPager);


        mTitleView = (TextView) findViewById(R.id.mTitle);
        mDescView = (TextView) findViewById(R.id.mDesc);
        mAddress = (TextView) findViewById(R.id.address);
        mWebsite = (TextView) findViewById(R.id.website);
        mPhone = (TextView) findViewById(R.id.phonenumber);
        textTitle = (TextView) findViewById(R.id.textTitle);
        imageView = (ImageView) findViewById(R.id.htab_header);

        mTitleView.setText(title);
        mDescView.setText(desc);
        mAddress.setText(address);
        mWebsite.setText(website);
        mPhone.setText(phone);
        Picasso.get().load(imageUri).into(imageView);
        textTitle.setText(title);


        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.BlackColor));

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(" ");
                    textTitle.setVisibility(View.VISIBLE);
                    mBackArrow.setImageResource(R.drawable.backarrow1);
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    textTitle.setVisibility(View.INVISIBLE);
                    mBackArrow.setImageResource(R.drawable.backarrow);
                    isShow = false;
                }
            }
        });


        //recycleView
        title1 = title.toLowerCase();
        mRecyclerView = findViewById(R.id.recyclerView1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //linearLayoutManager.setReverseLayout(true);
        // linearLayoutManager.setStackFromEnd(true);

        DividerItemDecoration itemDecor = new DividerItemDecoration(this, linearLayoutManager.getOrientation());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(itemDecor);


        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference(node).child("Hotels").child(title1).child("Book"+ title1);

        mRecyclerView1 = findViewById(R.id.recyclerView2);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        mRecyclerView1.setHasFixedSize(true);
        mRecyclerView1.setLayoutManager(linearLayoutManager1);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        mRef1 = firebaseDatabase.getReference("Reviews").child(title1);


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list = new ArrayList<BookHotelModel>();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    BookHotelModel m = ds.getValue(BookHotelModel.class);
                    list.add(m);
                }
                adapter = new BookHotelAdapter(ScrollingViewActivity.this,list);
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ScrollingViewActivity.this, "oppss..Something is worng", Toast.LENGTH_SHORT).show();

            }
        });



        mRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list1 = new ArrayList<ReviewsModel>();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    ReviewsModel m = ds.getValue(ReviewsModel.class);
                    list1.add(m);
                }
                adapter1 = new ReviewsAdapter(ScrollingViewActivity.this,list1);
                mRecyclerView1.setAdapter(adapter1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(ScrollingViewActivity.this, "oppss..Something is worng", Toast.LENGTH_SHORT).show();

            }
        });



    }


    private void giveFeedback() {

        dialog.setContentView(R.layout.reviews_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();

        final RatingBar mRatingBar = (RatingBar) dialog.findViewById(R.id.ratingBar);
        final TextView mRatingScale = (TextView) dialog.findViewById(R.id.tvRatingScale);
        final EditText mFeedback = (EditText) dialog.findViewById(R.id.etFeedBack);
        Button mSendFeedback = (Button) dialog.findViewById(R.id.btnFeedBack);
        ImageView imageView = (ImageView) dialog.findViewById(R.id.profileImg);
        Picasso.get().load(user.getPhotoUrl()).into(imageView);
        final String date = new SimpleDateFormat("EEE,MMM d",Locale.getDefault()).format(new Date());

        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mRatingScale.setText(String.valueOf(v));
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        mRatingScale.setText("Very bad");
                        break;
                    case 2:
                        mRatingScale.setText("Need some improvement");
                        break;
                    case 3:
                        mRatingScale.setText("Good");
                        break;
                    case 4:
                        mRatingScale.setText("Great");
                        break;
                    case 5:
                        mRatingScale.setText("Awesome. I love it");
                        break;
                    default:
                        mRatingScale.setText("");
                }
            }
        });

        mSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFeedback.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill in feedback text box", Toast.LENGTH_LONG).show();
                } else {

                    String userid = user.getUid();
                    String feedback = mFeedback.getText().toString();
                    String star = Float.toString(mRatingBar.getRating());


                    mRef = FirebaseDatabase.getInstance().getReference().child("Reviews").child(title1).child(userid);

                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("userName",user.getDisplayName());
                    hashMap.put("review",feedback);
                    hashMap.put("star",star);
                    hashMap.put("date",date);

                    mRef.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Thank you for sharing your feedback", Toast.LENGTH_SHORT).show();
                                mFeedback.setText("");
                                mRatingBar.setRating(0);
                                dialog.dismiss();
                            }else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "failure", task.getException());
                                Toast.makeText(ScrollingViewActivity.this, "FeedBack is not Submit",
                                        Toast.LENGTH_SHORT).show();

                            }

                        }
                    });


                }
            }
        });

        dialog.show();

    }

}
