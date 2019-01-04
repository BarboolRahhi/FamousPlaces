package in.barbool.famousplaces.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import in.barbool.famousplaces.Models.ReviewsModel;
import in.barbool.famousplaces.R;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {
    Context context;
    ArrayList<ReviewsModel> reviewsModels;

    public ReviewsAdapter(Context context, ArrayList<ReviewsModel> reviewsModels) {
        this.context = context;
        this.reviewsModels = reviewsModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.reviews_item_layout,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.mUserName.setText(reviewsModels.get(i).getUserName());
        viewHolder.mDate.setText(reviewsModels.get(i).getDate());
        viewHolder.mReview.setText(reviewsModels.get(i).getReview());
        viewHolder.ratingBar.setRating(Float.parseFloat(String.valueOf(reviewsModels.get(i).getStar())));

    }

    @Override
    public int getItemCount() {
        return reviewsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mUserName,mDate,mReview;
        RatingBar ratingBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mUserName = (TextView) itemView.findViewById(R.id.username);
            mReview = (TextView) itemView.findViewById(R.id.review);
            mDate = (TextView) itemView.findViewById(R.id.datefomate);
           ratingBar = (RatingBar) itemView.findViewById(R.id.rateBar);


        }
    }
}
