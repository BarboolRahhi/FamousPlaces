package in.barbool.famousplaces.ViewHolders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import in.barbool.famousplaces.R;

public class ViewStateHolder extends RecyclerView.ViewHolder {

    View view;

    public ViewStateHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;

    }

    public void setDetails(Context ctx, String Image, String Image1, String Image2, String Image3,
                           String Title, String Desc, String Address, String Website,
                           String PhoneNumber, String Star, String Reviews){

        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView desc = (TextView) view.findViewById(R.id.desc);

        Picasso.get().load(Image).into(imageView);
        title.setText(Title);
        desc.setText(Desc);

    }
}
