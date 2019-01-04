package in.barbool.famousplaces;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ViewFlipper;


import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ViewFlipper viewFlipper;
    CardView mStateCard;

    public int[] images = {

            R.drawable.taj,
            R.drawable.back,
            R.drawable.indiagate

    };


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mStateCard = (CardView) view.findViewById(R.id.stateCard);


        mStateCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectState();
            }
        });

        viewFlipper = (ViewFlipper) view.findViewById(R.id.viewflipper);

        for (int image :images){

            flipperImage(image);
        }


        return view;
    }

    private void selectState() {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
        View mView = getLayoutInflater().inflate(R.layout.slecte_state_dialoge, null);

        Button button = (Button) mView.findViewById(R.id.searchResult);
        final Spinner spinner = (Spinner) mView.findViewById(R.id.spinner);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Punjab");
        categories.add("Delhi");
        categories.add("Jammu & kashmir");
        categories.add("Kerala");
        categories.add("Tamil Nadu");
        categories.add("Goa");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);



        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String text = spinner.getSelectedItem().toString();
                Intent intent = new Intent(getContext(),ScrollingActivity.class);
                intent.putExtra("title",text);
                startActivity(intent);
                dialog.cancel();
            }
        });

    }

    public void flipperImage(int image){
        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(getContext(), android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(getContext(), android.R.anim.slide_out_right);




    }

}
