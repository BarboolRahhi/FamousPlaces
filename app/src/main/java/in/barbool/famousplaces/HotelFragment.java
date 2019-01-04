package in.barbool.famousplaces;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import in.barbool.famousplaces.Adapters.StateViewPagerAdapter;
import in.barbool.famousplaces.Models.ViewStateModel;
import in.barbool.famousplaces.ViewHolders.ViewStateHolder;


/**
 * A simple {@link Fragment} subclass.pp:titleEnabled="false"
 */
public class HotelFragment extends Fragment {

    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    ScrollingActivity activity;
    String node = "";



    public HotelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_hotel, container, false);

        activity = (ScrollingActivity) getActivity();
        node = activity.getMyData();


        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        //linearLayoutManager.setReverseLayout(true);
       // linearLayoutManager.setStackFromEnd(true);

        mRecyclerView.setLayoutManager(linearLayoutManager);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference(node).child("Hotels");
       // mRef.keepSynced(true);



        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<ViewStateModel, ViewStateHolder> firebaseRecyclerAdapter =
               new FirebaseRecyclerAdapter<ViewStateModel, ViewStateHolder>(
                       ViewStateModel.class,
                       R.layout.view_state_item,
                       ViewStateHolder.class,
                       mRef
               ) {
                   @Override
                   protected void populateViewHolder(ViewStateHolder viewHolder, final ViewStateModel model, int position) {

                       viewHolder.setDetails(getContext(), model.getImage(), model.getImage1(),
                               model.getImage2(), model.getImage3(), model.getTitle(), model.getDesc(),
                               model.getAddress(), model.getWebsite(), model.getPhoneNumber(), model.getStar(), model.getReviews());


                       final String imageUri = model.getImage();
                       final String title = model.getTitle();
                       viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               Intent intent = new Intent(getContext(),ScrollingViewActivity.class);
                               intent.putExtra("imageUri",imageUri);
                               intent.putExtra("title",title);
                               intent.putExtra("desc",model.getDesc());
                               intent.putExtra("address",model.getAddress());
                               intent.putExtra("website",model.getWebsite());
                               intent.putExtra("phone",model.getPhoneNumber());
                               intent.putExtra("image1",model.getImage1());
                               intent.putExtra("image2",model.getImage2());
                               intent.putExtra("image3",model.getImage3());
                               intent.putExtra("star",model.getStar());
                               intent.putExtra("reviews",model.getReviews());
                               intent.putExtra("node",node);
                               startActivity(intent);
                           }
                       });

                   }
               };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }


}


